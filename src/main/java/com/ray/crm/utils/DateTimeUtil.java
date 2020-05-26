package com.ray.crm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 日期格式修改
 * @Author: Ray Li
 * @E-mail: ray.lir@outlook.com
 * @Date: 2020/5/26 19:34
 */
public class DateTimeUtil {

    private DateTimeUtil(){}

    public static String getSysTime(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();
        String dateStr = sdf.format(date);

        return dateStr;

    }
    public static String getSysTimeForUpload(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        Date date = new Date();
        String dateStr = sdf.format(date);

        return dateStr;

    }
}
