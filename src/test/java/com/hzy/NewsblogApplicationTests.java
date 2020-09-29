package com.hzy;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Calendar;

@SpringBootTest
class NewsblogApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_WEEK,1);
        String token = JWT.create()
                .withClaim("userId","1")
                .withClaim("username","root")// payload
                .withExpiresAt(instance.getTime())// 指定令牌过期时间
                .sign(Algorithm.HMAC256("FFIEUHIH"));// 签名
        System.out.println(token);
    }

    @Test
    void myTest() {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("FFIEUHIH")).build();

        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDE0MzQyNDQsInVzZXJJZCI6IjEiLCJ1c2VybmFtZSI6InJvb3QifQ.kxn2NOUJmNPAVRijF7K0jKBSuBHgZwhN_gesiPZfKUc");
        System.out.println(verify.getClaim("userId").asString());
        System.out.println(verify.getClaim("username").asString());
    }
}
