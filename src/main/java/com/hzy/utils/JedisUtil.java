package com.hzy.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
    private static JedisPool jedisPool;
    private static String SPLIT = ":";
    private static String LIKE = "LIKE";

    static {
        // 获得连接池配置对象，设置配置项
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大连接数
        config.setMaxTotal(20);
        // 最大空闲连接数
        config.setMaxIdle(10);
        // 最小空闲链接数
        config.setMinIdle(2);
        // 获得连接池
        jedisPool = new JedisPool(config, "39.106.231.3", 6379);
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static String getLikeKey(int blogId) {
        return LIKE + SPLIT + String.valueOf(blogId);
    }
    public static void release(Jedis jedis){
        if(null != jedis){
            jedis.close();
        }
    }
}
