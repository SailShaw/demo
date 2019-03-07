package com.springboot.demo.util;

import java.util.UUID;

/**
 * Create By SINYA
 * Create Date 2019/2/27
 * Description:Tools For UUID
 */

public class UUIDTool {

    public static UUID uuid;

    /**
     * 随机生成32位UUID
     * @return
     */
    public static String getUUID(){
        //获取+转换
        String result = uuid.randomUUID().toString();
        //去除"-"号
        result= result.replace("-","");
        return result;
    }

}

