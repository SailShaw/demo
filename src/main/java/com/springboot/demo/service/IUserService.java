package com.springboot.demo.service;

import com.springboot.demo.entity.User;

import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Service for User
 */

public interface IUserService {

    //根据ID修改资料

    String modifyUserInfoById(User user);


    //获取用户、角色、部门信息

    List<User> getURGInfoListByPage(Integer pageNum, Integer pageSize, User user);

    //删除用户

    String deleteUserByID(User user);

    //注册
    void register(User user);

    //用户管理
    String modifyURGInfoById(User user);

    //发送验证码

    void sendVerificationCode(Integer VerifyComde,User user);

    //重置密码
    void resetPassword(User user);

    //记录登录日期
    void recordLoginTime(User user);

    String updatePassword(User censor);
}
