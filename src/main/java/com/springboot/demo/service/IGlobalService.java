package com.springboot.demo.service;

import com.springboot.demo.entity.Place;
import com.springboot.demo.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Service for Global
 */

public interface IGlobalService {

    //获取场地列表
    List<Place> getPlaceList();
    //获取角色列表
    List<User> getRoleList();
    //获取部门列表
    List<User> getGroupList();

    boolean sendEmailOfTest(List<String> recipient,String mailTitle,String mailContent);

    Map<String,Object> dataStatistics();
}
