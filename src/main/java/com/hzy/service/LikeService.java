package com.hzy.service;

import com.hzy.utils.JedisKeyUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class LikeService {

    public Long like(int userId,int blogId) {
        Jedis jedis = new Jedis("39.106.231.3",6379);
        String likeKey = JedisKeyUtil.getLikeKey(blogId);
        if (jedis.sismember(likeKey,String.valueOf(userId))) {
            jedis.srem(likeKey,String.valueOf(userId));
        } else {
            jedis.sadd(likeKey,String.valueOf(userId));
        }
        return jedis.scard(likeKey);
    }
}
