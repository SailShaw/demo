package com.springboot.demo;

import com.springboot.demo.entity.Menu;
import com.springboot.demo.mapper.MenuMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By SINYA
 * Create Date 2019/3/1
 * Description:
 */
public class TestHuTools {

    @Resource
    private static MenuMapper menuMapper;


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

        String local = "http://localhost:8888/application/getFormByUser";

        System.out.println();

        Menu menu = new Menu();
        menu.setRoleId("1");
        List<Map<String,String>> lis = new ArrayList<>();

        Map<String,String> map1 = new HashMap<>();
        map1.put("permitUrl","application/getFormByUser");

        Map<String,String> map2 = new HashMap<>();
        map2.put("permitUrl","/application/getFormListByDept");

        Map<String,String> map3 = new HashMap<>();
        map3.put("permitUrl","/application/verifyFormById");
        //数据处理
        String url= local.substring(21);
        lis.add(map1);
        lis.add(map2);
        lis.add(map3);

        Map<String,String> map4 = new HashMap<>();
        map4.put("permitUrl","/application/verifyFormById");
        //遍历
        for (int i = 0; i < lis.size(); i++) {
            if (lis.contains(map4)) {
                System.out.println("有权限");
                break;
            }else {
                System.out.println("无权限");
                break;
            }
        }
    }
}
