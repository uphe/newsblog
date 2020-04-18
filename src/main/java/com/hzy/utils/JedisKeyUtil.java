package com.hzy.utils;

public class JedisKeyUtil {
    private static String SPLIT = ":";
    private static String LIKE = "LIKE";

    public static String getLikeKey(int blogId) {
        return LIKE + SPLIT + String.valueOf(blogId);
    }
}
