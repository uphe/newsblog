package com.hzy.utils;

public class StringUtils {

    private static String SPLIT = ":";
    private static String LIKE = "LIKE";
    private static String READ = "READ";
    private static String CHANGE = "CHANGE";
    private static String TODAY_COMMEND = "TODAY_COMMEND";

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

    public static String getReadKey(int blogId) {
        return READ + SPLIT + String.valueOf(blogId);
    }

    public static String getChangeKey() {
        return CHANGE;
    }

    public static String getTodayCommend() {
        return TODAY_COMMEND;
    }
}
