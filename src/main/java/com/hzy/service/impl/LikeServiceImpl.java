package com.hzy.service.impl;

import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.LikeRecordMapper;
import com.hzy.mapper.RemindMapper;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.LikeRecord;
import com.hzy.pojo.Remind;
import com.hzy.pojo.User;
import com.hzy.service.LikeService;
import com.hzy.utils.StringUtils;
import com.hzy.vo.BaseResult;
import com.hzy.vo.BlogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
@Slf4j
public class LikeServiceImpl implements LikeService {
    @Autowired
    private RemindMapper remindMapper;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LikeRecordMapper likeRecordMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 通过redis实现点赞和取消点赞功能，每篇文章，都有一个key（用到时创建），用来存放点赞的用户
     * 当用户点赞时，会把该用户放到该key中，即记录每个用户只能点赞一次，同时可以用于取消点赞功能
     * 还有一个集合是changeKey，用来存放改变的文章，用于定时任务持久化点赞数量（定时将Redis中点赞总数持久化到MySQL）
     *
     * @param blogId
     * @param session
     * @return
     */
    public BaseResult like(int blogId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        BlogVO blogVO = new BlogVO();

        // 通过RedisTemplate获取操作Set集合的对象
        SetOperations setOperations = redisTemplate.opsForSet();
        // 获取changeKey，该key用来存放哪些文章被改变了
        String changeKey = StringUtils.getChangeKey();
        // 通过一个set集合，来进行统计哪些文章发生了改变，用于定时任务，如果不在集合中，就添加进去
        if (!setOperations.isMember(changeKey, blogId)) {
            setOperations.add(StringUtils.getChangeKey(), blogId);
        }
        // 这是构建博客的likeKey，即区分不同博客的点赞（每篇博客都有自己的key）
        String likeKey = StringUtils.getLikeKey(blogId);

        // 如果是第二次点击，那么就认为是取消点赞
        if (setOperations.isMember(likeKey, String.valueOf(userId))) {
            log.info("执行了取消点赞操作");
            setOperations.remove(likeKey, String.valueOf(userId));
            blogVO.setIsLike(0);
            // 取消点赞，删除该记录
            LikeRecord likeRecord = likeRecordMapper.isLike(userId, blogId);
            likeRecordMapper.deleteLikeRecordById(likeRecord.getLikeRecordId());

            // 这里是标记消息已读（因为点赞已取消）
            remindMapper.updateRemindByFromIdAndBlogIdAndRemindType(userId, blogId, 0);
        } else {
            log.info("执行了点赞操作");
            // 点赞，把该用户放到该文章的点赞集合中
            setOperations.add(likeKey, String.valueOf(userId));
            // 添加一条点赞记录
            likeRecordMapper.addLikeRecord(new LikeRecord(userId, blogId));
            blogVO.setIsLike(1);
            Remind remind = new Remind();
            remind.setBlogId(blogId);
            remind.setState(0);
            remind.setCreateDate(new Date());
            remind.setFromId(userId);
            remind.setRemindType(0);
            remind.setToId(blogMapper.selectBlogById(blogId).getUserId());
            remind.setRemindContent(userMapper.selectUserById(userId).getUsername() +
                    "点赞了" + blogMapper.selectBlogById(blogId).getTitle());

            remindMapper.addRemind(remind);
        }

        blogVO.setLikeCount(Math.toIntExact(setOperations.size(likeKey)));
        return BaseResult.ok(blogVO);
    }
}
