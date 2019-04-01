package com.springboot.demo;

import cn.hutool.core.util.RandomUtil;
import com.springboot.demo.util.SnowFlake;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Create By SINYA
 * Description:
 */
public class TestSnowFlake {

    private static final SnowFlake snowFlake = new SnowFlake(3,9);

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {


        for (int i = 1; i <= 180 ; i++) {
//            System.out.println(String.valueOf(snowFlake.nextId()));
            System.out.println(String.valueOf(RandomUtil.randomInt(10,3000)));
//            System.out.println(MD5.EncoderByMd5("test"+i));
        }


    }
}
