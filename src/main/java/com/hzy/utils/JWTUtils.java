package com.hzy.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hzy.pojo.User;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用JWT，把用户信息保存到浏览器harder的token里
 * 后端如果想要获取用户信息，只需要获取harder里的token即可
 */
public class JWTUtils {
    private static String SIGN = "TOKEN!@FE123";
    private static final int LOGIN_TIME = 14;
//    private static final int LOGIN_TIME = 10;

    /**
     * 传入payload信息，生成token，默认保存14天
     * @param map
     * @return
     */
    public static String getToken(Map<String, String> map) {


        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v) -> {
            builder.withClaim(k,v);
        });

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, LOGIN_TIME);
//        instance.add(Calendar.SECOND, LOGIN_TIME);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * 传入user信息，生成token，默认保存14天
     * @param map
     * @return
     */
    public static String getToken(User user) {

        // 将用户的信息封装到token中
        Map<String, String> payload = new HashMap<>();
        payload.put("userId",String.valueOf(user.getUserId()));
        payload.put("username",user.getUsername());
        payload.put("headUrl",user.getHeadUrl());
        payload.put("userType",String.valueOf(user.getUserType()));

        return JWTUtils.getToken(payload);
    }

    /**
     * 生成token，默认保存14天
     * @return
     */
    public static String getToken() {

        JWTCreator.Builder builder = JWT.create();

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, LOGIN_TIME);
//        instance.add(Calendar.SECOND, LOGIN_TIME);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * 验证token
     * @param token
     */
    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    /**
     * 获取token中的信息
     * @param token
     * @return
     */
    public static DecodedJWT getToken(String token) {
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

}
