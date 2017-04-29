package com.hwyhard.www.fushihui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hwyhard on 17/4/29.
 * 用与日期转换的工具类
 */

public class DateUtil {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    //将Date转换成为String
    public static String DateToString(Date date){
        return simpleDateFormat.format(date);
    }
    //将String转换成为Date
    public static Date StringToDate(String string) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
