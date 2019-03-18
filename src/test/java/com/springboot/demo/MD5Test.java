package com.springboot.demo;

import com.springboot.demo.util.MD5;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Create By SINYA
 * Description:
 */
public class MD5Test {


    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String val = "s0706";

        System.out.println(MD5.EncoderByMd5(val));
    }

}
