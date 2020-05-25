package com.example.imxbkslibrary.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class IMDateUtils {
    private static SimpleDateFormat sf;
    private static SimpleDateFormat sdf;

    /**
     * 获取系统时间 格式为："yyyy/MM/dd "
     **/
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /**
     * 获取系统时间 格式为："yyyy "
     **/
    public static String getCurrentYear() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy");
        return sf.format(d);
    }

    /**
     * 获取系统时间 格式为："MM"
     **/
    public static String getCurrentMonth() {
        Date d = new Date();
        sf = new SimpleDateFormat("MM");
        return sf.format(d);
    }

    /**
     * 获取系统时间 格式为："dd"
     **/
    public static String getCurrentDay() {
        Date d = new Date();
        sf = new SimpleDateFormat("dd");
        return sf.format(d);
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentTime() {
        long d = new Date().getTime() / 1000;
        return d;
    }

    /**
     * 时间戳转换成字符窜
     */
    public static String getDateToString(long time) {
//        Date d = new Date(time * 1000);
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sf.format(d);
    }

    /**
     * 时间戳转换成字符窜
     */
    public static String getDateToStringYYYY_MM_DD(long time) {
//        Date d = new Date(time * 1000);
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }


    /**
     * 时间戳中获取年
     */
    public static String getYearFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("yyyy");
        return sf.format(d);
    }


    public static int getIntYearFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("yyyy");
        return Integer.parseInt(sf.format(d));
    }

    /**
     * 时间戳中获取月
     */
    public static String getMonthFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("MM");
        return sf.format(d);
    }

    public static int getIntMonthFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("MM");
        return Integer.parseInt(sf.format(d));
    }

    /**
     * 时间戳中获取日
     */
    public static String getDayFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("dd");
        return sf.format(d);
    }

    public static int getIntDayFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("dd");
        return Integer.parseInt(sf.format(d));
    }

    /**
     * 将字符串转为时间戳
     */
    public static long getStringToDate(String time) {
        sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 将字符串转为时间戳
     *
     * @param dateString String dateString = "2017-06-20 10:30:30" 对应的格式：String pattern = "yyyy-MM-dd HH:mm:ss";
     * @param pattern    String pattern = "yyyy-MM-dd HH:mm:ss";
     * @return
     */
    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }


    /**
     * 时间戳转日期
     *
     * @param dateString 时间戳
     * @param format     String pattern = "yyyy-MM-dd HH:mm:ss";
     * @return
     */
    public static String getTimeToDate(long dateString, String format) {
        dateString = dateString / 1000;
        if (format == null || format.isEmpty()) {
            format = "yyyy/MM/dd HH:mm";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(dateString + "000")));
    }

    public static String getDateStringFormat(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "年" + month + "月" + day + "日";
    }


}
