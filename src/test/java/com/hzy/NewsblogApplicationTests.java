package com.hzy;

import com.hzy.mapper.*;
import com.hzy.pojo.*;
import com.hzy.utils.JedisUtil;
import com.hzy.utils.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
class NewsblogApplicationTests {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private ReadNoticeMapper readNoticeMapper;
    @Autowired
    private RemindMapper remindMapper;

    volatile int goods = 100;
    String secKillGoods = "secKillGoods:1";
    final ReentrantLock lock = new ReentrantLock();
    final Jedis jedis = new Jedis("39.106.231.3", 6379);
    @Test
    void contextLoads() {

        jedis.set("goods","100");
        for (int i = 0; i < 105; i++) {
            int temp = i;
            new Thread(()->{
                getSecKillGoods(temp);
            }).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (jedis.exists(secKillGoods)) {

            Set<String> setValues = jedis.smembers(secKillGoods);
            System.out.println(setValues.size());

        }
        jedis.expire(secKillGoods,1);

    }
    public void getSecKillGoods(int userId) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (goods > 0) {
                if (jedis.sismember(secKillGoods, String.valueOf(userId))) {
                    System.out.println("您已购买成功");
                } else {
                    jedis.sadd(secKillGoods, String.valueOf(userId));
                    goods --;
                }
            } else {
                System.out.println("商品已卖完");
            }
        } finally {
            lock.unlock();
        }

    }
}
