package com.springboot.demo.util;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * Create By SINYA
 * Description:获取日期并转换成字符串
 */
public class TimeHelper {


    public static String getNowTime(){
        String result = DateUtil.format(new Date(),"yyyy-MM-dd");;
        return result;
    }
}
