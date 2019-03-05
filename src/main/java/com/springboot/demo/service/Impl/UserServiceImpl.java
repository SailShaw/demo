package com.springboot.demo.service.Impl;

import com.springboot.demo.entity.User;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.service.IUserService;
import com.springboot.demo.util.UUIDTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By SINYA
 * Create Date 2019/2/27
 * Description:Service Implements For User
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;


    /**
     * 查询用户信息
     * @param user
     * @return
     */
    @Override
    public List<User> getUserByAccount(User user) {
        List<User> result = userMapper.getUserByAccount(user);
        return result;
    }

    /**
     * 根据ID修改用户信息
     * @param user
     */
    @Override
    public void modifyUserInfoById(User user) {
        userMapper.modifyUserInfoById(user);
    }

    /**
     * 用户管理查询
     * @param user
     * @return
     */
    @Override
    public List<User> getURGInfoListByPage(User user) {
        List<User> result = userMapper.getURGInfoListByPage(user);
        return result;
    }

    /**
     * 删除用户
     * @param user
     */
    @Override
    public void deleteUserByID(User user) {
        userMapper.deleteUserByID(user);
    }

    /**
     * 修改用户部门
     * @param user
     */
    @Override
    public void modifyGroupByUser(User user) {

        userMapper.modifyGroupByUser(user);
    }

    /**
     * 用该用户角色
     * @param user
     */
    @Override
    public void modifyRoleByUser(User user) {
        userMapper.modifyRoleByUser(user);
    }

    /**
     * 注册
     * @param user
     */
    @Override
    public void createUser(User user) {
        //生成userID
        String id = UUIDTool.getUUID();
        user.setUserId(id);
        //t_user
        userMapper.createUser(user);
        //t_user_role
        userMapper.addUserRole(id);
        //t_usergroup
        userMapper.addUserGroup(id);
    }


}
