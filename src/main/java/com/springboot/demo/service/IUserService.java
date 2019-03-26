package com.springboot.demo.service;

import com.springboot.demo.entity.User;

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


    //获取用户、角色、部门信息

    List<User> getURGInfoListByPage(Integer pageNum, Integer pageSize, User user);

    //删除用户

    String deleteUserByID(User user);

    //注册
    void register(User user);

    //角色列表
    List<User> getRoleList();
    //获取部门列表
    List<User> getGroupList();

    //用户管理
    String modifyURGInfoById(User user);

    //发送验证码

    void sendVerificationCode(Integer VerifyComde,User user);

    void resetPassword(User user);

}
