package com.springboot.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create By SINYA
 * Description:
 */
public class RegexUtil {


    /**
     * 验证账号是否符合规则
     * @param account
     * @return
     */
    public static boolean reviewAccount(String account){
        boolean flag = false;
        try {
            String check = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(account);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证密码是否符合规则
     * @param password
     * @return
     */

    public static boolean reviewPassword(String password){
        boolean flag = false;
        try {
            String check = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(password);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    /**
     * 验证邮箱地址是否正确
     * @param email
     * @return
     */
    public static boolean reviewEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }



}
