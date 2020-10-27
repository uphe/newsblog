package com.hzy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final long TIME = 1000 * 60 * 60 * 24 * 14;

    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static Date afterSevenDay() {
        Date date = new Date();
        date.setTime(new Date().getTime() + TIME);
        return date;
    }
}
