package com.springboot.demo.service;

import com.springboot.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create By SINYA
 * Create Date 2019/2/27
 * Description:
 */

public interface IUserService {

    //根据ID获取用户信息
    List<User> getUserByAccount(User user);

    //根据所有用户
    List<User> getAllUserInfo();

    //添加
    void addUser(User user);

    //更新
    void updateUser(User user);

    //删除
    void deleteUser(String id);
}
