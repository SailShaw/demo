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
     * 根据账号获取用户信息
     * @param user
     * @return
     */
    @Override
    public List<User> getUserByAccount(User user) {
        List<User> resultList = userMapper.getUserByAccount(user);
        return resultList;
    }

    /**
     * 获取用户、角色、组织关系列表
     * @param user
     * @return
     */
    @Override
    public List<User> getURGInfoListByPage(User user) {
        List<User> result = userMapper.getURGInfoListByPage(user);
        return  result;
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

    /**
     * 修改用户信息
     * @param user
     */
    @Override
    public void modifyUserInfoByAccount(User user) {
        userMapper.modifyUserInfoByAccount(user);
    }

    /**
     * 删除用户
     * @param user
     */
    @Override
    public void deleteUserByAccount(User user) {
        userMapper.deleteUserByAccount(user);
    }

    /**
     * 修改用户所属组织
     * @param user
     */
    @Override
    public void modifyGroupByUser(User user) {
        userMapper.modifyGroupByUser(user);
    }

    /**
     * 修改用户所属角色
     * @param user
     */
    @Override
    public void modifyRoleByUser(User user) {
        userMapper.modifyRoleByUser(user);
    }
}
