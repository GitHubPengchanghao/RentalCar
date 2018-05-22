package com.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具包，有时间类型修改为与sql一致的样式
 */
public class Tools {
    /**
     * 可以方便地修改日期格式
     * @return
     */
    public static String Date() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(now);
        return date;
    }

    public static int ment(String beginTime, String endTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int days = 0;
        try {
            Date d1 = df.parse(beginTime);
            Date d2 = df.parse(endTime);
            long diff = d2.getTime() - d1.getTime();
            days = (int) (diff / (1000 * 60 * 60 * 24) + 1);
            System.out.println(days);
        } catch (ParseException e) {
            System.out.println("格式不正确");
        }
        return days;
    }
}
