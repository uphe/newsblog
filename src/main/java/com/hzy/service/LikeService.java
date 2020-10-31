package com.hzy.service;

import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.LikeRecordMapper;
import com.hzy.mapper.RemindMapper;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.LikeRecord;
import com.hzy.pojo.Remind;
import com.hzy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.sql.Struct;
import java.util.Date;

@Service
public class LikeService {
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
     * 通过redis实现点赞和取消点赞功能
     * @param userId
     * @param blogId
     * @return
     */
    public Long like(int userId,int blogId) {
        SetOperations setOperations = redisTemplate.opsForSet();
        String changeKey = StringUtils.getChangeKey();

        // 通过一个set集合，来进行统计哪些文章发生了改变，用于定时任务，如果不在集合中，就添加进去
        if (! setOperations.isMember(changeKey,blogId)) {
            setOperations.add(StringUtils.getChangeKey(),blogId);
        }
        // 这是构建博客的likeKey，即区分不同博客的点赞
        String likeKey = StringUtils.getLikeKey(blogId);

        // 如果是第二次点击，那么就认为是取消点赞
        if (setOperations.isMember(likeKey,String.valueOf(userId))) {
            setOperations.remove(likeKey,String.valueOf(userId));
            // 取消点赞，把点赞记录设置为0
            LikeRecord likeRecord = likeRecordMapper.isLike(userId, blogId);
            likeRecordMapper.updateLikeRecord(likeRecord.getLikeRecordId(),0);
            // 这里是标记消息已读（因为点赞已取消）
            remindMapper.updateRemindByFromIdAndBlogIdAndRemindType(userId,blogId,0);
        } else {
            setOperations.add(likeKey,String.valueOf(userId));
            // 处理点赞记录
            likeRecordMapper.addLikeRecord(new LikeRecord(userId,blogId));

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

        return setOperations.size(likeKey);
    }
}
