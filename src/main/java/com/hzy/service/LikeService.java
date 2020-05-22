package com.hzy.service;

import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.RemindMapper;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.Remind;
import com.hzy.pojo.User;
import com.hzy.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Date;

@Service
public class LikeService {
    @Autowired
    private RemindMapper remindMapper;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;

    public Long like(int userId,int blogId) {
        Jedis jedis = JedisUtil.getJedis();
        String likeKey = JedisUtil.getLikeKey(blogId);
        if (jedis.sismember(likeKey,String.valueOf(userId))) {
            jedis.srem(likeKey,String.valueOf(userId));
            remindMapper.updateRemindByFromIdAndBlogIdAndRemindType(userId,blogId,0);
        } else {
            jedis.sadd(likeKey,String.valueOf(userId));

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
        long num = jedis.scard(likeKey);
        JedisUtil.release(jedis);
        return num;
    }
}
