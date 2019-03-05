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

    /*个人中心接口*/

    /**
     * 个人中心资料查询
     * 根据账号获取用户信息
     * @param user
     * @return
     */
    List<User> getUserByAccount(User user);

    /**
     * 个人中心资料修改
     * 根据ID修改
     * @param user
     */
    void modifyUserInfoById(User user);


    /*用户管理接口*/

    /**
     * 用户管理查询
     * 获取用户、角色、部门信息
     * @param user
     * @return
     */
    List<User> getURGInfoListByPage(User user);

    /**
     * 删除用户
     * @param user
     */
    void deleteUserByID(User user);

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

    /**
     * 注册
     * @param user
     */
    void createUser(User user);
}
