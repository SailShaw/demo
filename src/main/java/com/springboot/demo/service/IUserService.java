package com.springboot.demo.service;

import com.springboot.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create By SINYA
 * Create Date 2019/2/27
 * Description:Service For User
 */

public interface IUserService {


    /**
     * 根据账号获取用户信息
     * @param user
     * @return
     */
    List<User> getUserByAccount(User user);

    /**
     * 获取用户、角色、部门信息
     * @param user
     * @return
     */
    List<User> getURGInfoListByPage(User user);

    /**
     * 注册
     * @param user
     */
    void createUser(User user);

    /**
     * 修改账号信息
     * @param user
     */
    void modifyUserInfoByAccount(User user);

    /**
     * 删除用户
     * @param user
     */
    void deleteUserByAccount(User user);

    /**
     * 修改用户所属部门
     * @param user
     */
    void modifyGroupByUser(User user);

    /**
     * 修改用户所属角色
     * @param user
     */
    void modifyRoleByUser(User user);


}
