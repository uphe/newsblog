package com.hzy;

import com.hzy.mapper.*;
import com.hzy.pojo.*;
import com.hzy.utils.JedisUtil;
import com.hzy.utils.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
class NewsblogApplicationTests {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    void contextLoads() {
        System.out.println(blogMapper.selectLikeCountSumByUserId(1));
    }
}
