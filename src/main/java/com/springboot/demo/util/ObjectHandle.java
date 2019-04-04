package com.springboot.demo.util;

import java.lang.reflect.Field;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Utils for MD5
 */
public class ObjectHandle {

    /**
     *
     * @param obj  需要判断的对象
     * @param exclude 需要排除的 key 如String["id","name","age"]
     * @return flag True or False
     * @throws IllegalAccessException
     */
    public static boolean reflectFieldIsNotALLNull(Object obj, String[] exclude) throws IllegalAccessException {
        boolean flag;
        if (obj == null) return false;
        //通过反射获取该对象的私有变量
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int j = 0; j < fields.length; j++) {

            flag = false;
            if (exclude != null)
                for (String ex : exclude) {
                    if (ex.equals(fields[j].getName())) {
                        flag = true;
                        break;
                    }
                }
            if (flag) {
                continue;
            }

            fields[j].setAccessible(true);
            if(null != fields[j].get(obj)){
                return true;
            }
        }
        return false;
    }
}
