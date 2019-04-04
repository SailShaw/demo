package com.springboot.demo;

import com.springboot.demo.util.MD5;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Create By SINYA
 * Description:
 */
public class TestMD5 {


    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String val = "1009";

        System.out.println(MD5.EncoderByMd5(val));


        System.out.println(MD5.checkpassword("y0706","FYIYP9U4L8W5KMWCV1saVA=="));

    }

}
