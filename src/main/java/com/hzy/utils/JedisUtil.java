package com.hzy.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class JedisUtil {
    private static JedisPool jedisPool;
    private static String SPLIT = ":";
    private static String LIKE = "LIKE";

    static {
//        // 获得连接池配置对象，设置配置项
//        JedisPoolConfig config = new JedisPoolConfig();
//        // 最大连接数
//        config.setMaxTotal(20);
//        // 最大空闲连接数
//        config.setMaxIdle(10);
//        // 最小空闲链接数
//        config.setMinIdle(2);
//        // 获得连接池
//        jedisPool = new JedisPool(config, "39.106.231.3", 6379);
        // 不写配置项，用默认的（最大连接数默认是8，最大空闲数是8，最小空闲数是0，等待时间是-1，也就是一直等）
        jedisPool = new JedisPool("39.106.231.3", 6379);
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static void release(Jedis jedis){
        if(null != jedis){
            jedis.close();
        }
    }

    public static String getLikeKey(int blogId) {
        return LIKE + SPLIT + String.valueOf(blogId);
    }

    /**
     * 发布消息
     * @param channel
     * @param message
     */
    public static void publishMsg(String channel, String message) {
        Jedis jedis = getJedis();
        try {
            jedis.publish(channel, message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    public static JedisPubSub jedisPubSub = new JedisPubSub() {
        @Override
        public void onMessage(String channel, String message) {
            System.out.println("收到了" + channel + "频道发来消息：" +  message);
            System.out.println(channel + ":" + message);
        }

        @Override
        public void onSubscribe(String channel, int subscribedChannels) {
            System.out.println("订阅了" + channel + "频道");
            System.out.println(channel + ":" + subscribedChannels);
        }

        @Override
        public void onUnsubscribe(String channel, int subscribedChannels) {
            System.out.println("取消订阅了" + channel + "频道");
            System.out.println(channel + ":" + subscribedChannels);
        }
    };

    /**
     * 接收消息。
     * 在main方法调用后，会一直执行下去。当有发布对应消息时，就会在jedisPubSub中接收到！
     * @param channels
     */
    public static void subscribeMsg(String channels) {
        // Jedis jedis = getJedis();
        Jedis jedis = new Jedis ("39.106.231.3", 6379);
        new Thread(()->{
            try {
                jedis.subscribe(jedisPubSub, channels);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                // jedis.close();
            }
        }).start();
    }
}
