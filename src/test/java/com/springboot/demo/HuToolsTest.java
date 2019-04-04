package com.springboot.demo;

import com.springboot.demo.entity.User;
import com.springboot.demo.util.ObjectHandle;

/**
 * Create By SINYA
 * Create Date 2019/3/1
 * Description:
 */
public class HuToolsTest {


    public static void main(String[] args) throws IllegalAccessException {

//        String title = "验证您的电子邮件地址";
//        String content =
//                        "<p>尊敬的<strong>XX</strong>，您好：</p>" +
//                        "<p>您正在对您的 CAMS 账号 (Sinya) 进行重置密码操作。</p>" +
//                        "<p>您的验证码是</p>" +
//                        "<p><h1>"+RandomUtil.randomInt(999999) + "</h1></p>" +
//                        "<p>如果您未做过这些更改或认为有未经授权的人员访问了您的帐户，您应尽快登录 C A M S 系统进行更改密码,以防账户丢失。</p>" +
//                        "<p>此致</p>" +
//                        "<p>CAMS 支持</p>";
//        MailUtil.send("ShawSail@live.cn",title,content,true);

        User user = new User();
        String re = null;
        user.setUserId("1");
        if(ObjectHandle.reflectFieldIsNotALLNull(user,new String[]{"serialVersionUID"})){
            //不为空
        }else {
            //为空;
        }

        System.out.println(re);
    }
}
