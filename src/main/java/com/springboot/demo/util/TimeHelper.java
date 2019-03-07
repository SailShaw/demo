package com.springboot.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create By SINYA
 * Description:
 */
public class TimeHelper {

    public static Date convertStrToDate(String timeStr) {
        Date res = null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            res = simpleDateFormat.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return res;
    }

}
