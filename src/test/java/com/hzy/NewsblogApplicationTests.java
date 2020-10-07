package com.hzy;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.LabelMapper;
import com.hzy.pojo.Blog;
import com.hzy.utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class NewsblogApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private LabelMapper labelMapper;

    @Test
    void contextLoads() {
        Map<String,String> map = new HashMap<>();
        map.put("userId","1");
        map.put("username","root");
        String token = JWTUtils.getToken(map);
        System.out.println(token);
    }

    @Test
    void myTest() {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("FFIEUHIH")).build();

        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDI5MDA5MzAsInVzZXJJZCI6IjEiLCJ1c2VybmFtZSI6InJvb3QifQ.8dGTiJE0zqqZ7ARHCrqbNGcQURuGdNNilcsF6Dj7vyw");
        System.out.println(verify.getClaim("userId").asInt());
        System.out.println(verify.getClaim("username").asString());
    }

    @Test
    void blogTest() {
        System.out.println(labelMapper.selectLabelByUserId(1));
    }
}
