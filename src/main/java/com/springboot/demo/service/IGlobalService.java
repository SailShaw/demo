package com.springboot.demo.service;

import com.springboot.demo.entity.Place;
import com.springboot.demo.entity.User;

import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
public interface IGlobalService {

    //获取场地列表
    List<Place> getPlaceList();
    //获取角色列表
    List<User> getRoleList();
    //获取部门列表
    List<User> getGroupList();

    boolean sendEmailOfTest(List<String> recipient,String mailTitle,String mailContent);
}
