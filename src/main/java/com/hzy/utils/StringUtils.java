package com.hzy.utils;

public class StringUtils {
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
}
