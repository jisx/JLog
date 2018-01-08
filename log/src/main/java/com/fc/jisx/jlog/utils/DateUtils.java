package com.fc.jisx.jlog.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jsx on 2016/4/11.
 */
@SuppressLint("SimpleDateFormat")
public class DateUtils {

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    /**
     * 获取当前时间
     *
     * @return yyyy-MM-dd HH:mm
     */
    public static String getCurrentDate() {
        return getCurrentDate(DateType.N_YMdHm);
    }

    /**
     * 获取当前时间
     *
     * @param dateType 时间的类型
     * @return 时间字符串
     */
    public static String getCurrentDate(DateType dateType) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateType.getType());
        return dateFormat.format(now);
    }

    /**
     * 日期转换成字符串
     *
     * @param date 日期
     * @return 时间字符串
     */
    public static String dateToStr(Date date) {
        return dateToStr(date, DateType.N_YMdHm);
    }

    /**
     * 日期转换成字符串
     *
     * @param date     日期
     * @param dateType 格式化
     * @return 时间字符串
     */
    public static String dateToStr(Date date, DateType dateType) {
        SimpleDateFormat format = new SimpleDateFormat(dateType.getType());
        return format.format(date);
    }

    /**
     * 日期字符串转Data类型
     *
     * @param str 时间字符串
     * @return 日期
     */
    public static Date strToDate(String str) {
        return strToDate(str, DateType.N_YMdHm);
    }

    /**
     * 日期字符串转Data类型
     *
     * @param str      时间字符串
     * @param dateType 类型
     * @return Date or null
     */
    public static Date strToDate(String str, DateType dateType) {
        SimpleDateFormat format = new SimpleDateFormat(dateType.getType());
        Date date;
        try {
            date = format.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 指定时间 在当前日期的 几分钟前或者几天前
     *
     * @param date 时间
     * @return str 几分钟/几个小时前/几天前
     */
    public static String beforeTime(String date) {
        long delta = new Date().getTime() - strToDate(date, DateType.N_YMdHms).getTime();

        if (delta <= 10000) {
            return "1秒前";
        }

        String sb = "";
        long[] itimes = new long[]{365 * 24 * 60 * 60 * 1000L, 30 * 24 * 60 * 60 * 1000L, 24 * 60 * 60 * 1000L, 60 * 60 * 1000L, 60 * 1000, 1000L};
        String[] sunits = new String[]{"年前", "月前", "天前", "小时前", "分钟前", "秒前"};

        for (int i = 0, len = itimes.length; i < len; i++) {
            long itemp = itimes[i];
            if (delta < itemp) {
                continue;
            }
            long temp2 = delta / itemp;
            if (temp2 > 0) {
                sb = temp2 + sunits[i];
                break;
            }
        }
        return sb;
    }


    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

    public static boolean isSameMonth(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);

        return isSameMonth;
    }

    public static boolean isSameYear(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

        return isSameYear;
    }

    /**
     * 获取指定日期是星期几
     *
     * @param date 日期
     * @return 星期几
     */
    public static int getWeek(Date date) {
        String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return week_index;
    }

    /**
     * 时间转化为显示字符串
     *
     * @param timeStamp 单位为秒
     * @return 时间字符串
     */
    public static String getTimeStr(long timeStamp) {
        if (timeStamp == 0) return "";
        Calendar inputTime = Calendar.getInstance();
        inputTime.setTimeInMillis(timeStamp * 1000);
        Date currenTimeZone = inputTime.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        if (calendar.before(inputTime)) {
            //今天23:59在输入时间之前，解决一些时间误差，把当天时间显示到这里
            SimpleDateFormat sdf = new SimpleDateFormat(DateType.C_YMd.getType());
            return sdf.format(currenTimeZone);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (calendar.before(inputTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat(DateType.N_Hm.getType());
            return sdf.format(currenTimeZone);
        }
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        if (calendar.before(inputTime)) {
            return "昨天";
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            if (calendar.before(inputTime)) {
                SimpleDateFormat sdf = new SimpleDateFormat(DateType.C_Md.getType());
                return sdf.format(currenTimeZone);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat(DateType.C_YMd.getType());
                return sdf.format(currenTimeZone);

            }

        }

    }

    public static Date getYesterDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1 日期
     * @param date2 日期
     * @return 时间间隔
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return Math.abs(days);
    }

    /**
     * 比较日期
     *
     * @param date1 日期
     * @param date2 日期
     * @return date1 大于 date2  返回1
     */
    public static int compareDate(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            return 1;
        } else if (date1.getTime() < date2.getTime()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 获取日期的0点
     *
     * @param date 日期
     * @return 日期
     */
    public static Date getStartDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取日期的23点59分59秒
     *
     * @param date 日期
     * @return 日期
     */
    public static Date getEndDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 得到本月的第一天
     *
     * @return 日期
     */
    public static Date getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMinimum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();
    }

    /**
     * 获取几天前的日期
     *
     * @param day 天数
     * @return 日期
     */
    public static Date getDayBefore(int day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -day);
        return cal.getTime();
    }

}
