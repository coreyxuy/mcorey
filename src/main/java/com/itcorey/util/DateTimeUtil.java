package com.itcorey.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by ：Corey
 * 20:17 2018/12/25
 * 时间转换工具类
 */
public class DateTimeUtil {

    //joda-time
    //str-->date
    //date-->str
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";


    /**
     * 时间转为字符串
     *
     * @param dateTimeStr
     * @param formatStr
     * @return
     */
    public static Date strToDate(String dateTimeStr, String formatStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }


    /**
     * 时间转为字符串
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static String dateToStr(Date date, String formatStr) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime();
        return dateTime.toString(formatStr);
    }

    //重载
    public static Date strToDate(String dateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime();
        return dateTime.toString(STANDARD_FORMAT);
    }


    public static void main(String[] args) {

    }

}
