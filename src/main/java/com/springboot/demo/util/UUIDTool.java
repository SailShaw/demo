package com.springboot.demo.util;

import java.util.UUID;

public class UUIDTool {



    static UUID uuid;

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

