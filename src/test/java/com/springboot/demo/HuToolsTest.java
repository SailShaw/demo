package com.springboot.demo;

import cn.hutool.system.SystemUtil;

/**
 * Create By SINYA
 * Create Date 2019/3/1
 * Description:
 */
public class HuToolsTest {


    public static void main(String[] args) {

        String uaStr = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.163 Safari/535.1";
        System.out.println(SystemUtil.getRuntimeInfo());
        System.out.println(SystemUtil.getUserInfo());
    }
}
