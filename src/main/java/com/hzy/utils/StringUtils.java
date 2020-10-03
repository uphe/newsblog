package com.hzy.utils;

public class StringUtils {

    private static String SPLIT = ":";
    private static String LIKE = "LIKE";

    public static boolean isEmpty(String str) {
        if (str == null || str.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        if (str != null && !str.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static String getLikeKey(int blogId) {
        return LIKE + SPLIT + String.valueOf(blogId);
    }
}
