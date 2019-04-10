package com.springboot.demo;

import com.springboot.demo.entity.Menu;
import com.springboot.demo.mapper.MenuMapper;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
public class TestMD5 {

    private static MenuMapper menuMapper;


    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {

//        String val = "1009";
//
//        System.out.println(MD5.EncoderByMd5(val));
//
//
//        System.out.println(MD5.checkpassword("y0706","FYIYP9U4L8W5KMWCV1saVA=="));


        List<Menu> roleList = new ArrayList<>();

        Menu menu1 = new Menu();
        Menu menu2 = new Menu();
        Menu menu3 = new Menu();
        Menu menu4 = new Menu();
        Menu menu5 = new Menu();

        menu1.setPermitUrl("/user/getURGInfoListByPage");
        menu2.setPermitUrl("/user/modifyUserInfoById");
        menu3.setPermitUrl("/user/getUserToFind");
        menu4.setPermitUrl("/user/getUserOnCache");
        menu5.setPermitUrl("/user/updatePassword");

        roleList.add(menu1);
        roleList.add(menu2);
        roleList.add(menu3);
        roleList.add(menu4);
        roleList.add(menu5);
        String url = "/user/getURGInfoListByPage";
        String string = null;
        for (Menu m : roleList) {
            if (url.equals(m.getPermitUrl())) {
                string = "权限列表里有";
                break;
            }
        }
        System.out.println(string);


    }

}
