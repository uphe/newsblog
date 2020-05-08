package com.hzy.service;

import com.hzy.utils.JedisUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class LikeService {

    public Long like(int userId,int blogId) {
        Jedis jedis = JedisUtil.getJedis();
        String likeKey = JedisUtil.getLikeKey(blogId);
        if (jedis.sismember(likeKey,String.valueOf(userId))) {
            jedis.srem(likeKey,String.valueOf(userId));
        } else {
            jedis.sadd(likeKey,String.valueOf(userId));
        }
        long num = jedis.scard(likeKey);
        JedisUtil.release(jedis);
        return num;
    }
}
