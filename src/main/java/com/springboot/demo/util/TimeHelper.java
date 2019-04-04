package com.springboot.demo.util;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Utils for TimeHelper
 */

public class TimeHelper {

    /**
     * 1.获取当前时间
     * 2.转换成字符串
     * @return
     */
    public static String getNowTime(){
        String result = DateUtil.format(new Date(),"yyyy-MM-dd");;
        return result;
    }
}
