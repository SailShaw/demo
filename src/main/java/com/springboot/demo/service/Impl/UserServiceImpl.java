package com.springboot.demo.service.Impl;

import com.github.pagehelper.PageHelper;
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
    public List<User> getURGInfoListByPage(Integer pageNum, Integer pageSize, User user) {
        //分页
        PageHelper.startPage(pageNum,pageSize);
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
     * 注册
     * @param user
     */
    @Override
    public void register(User user) {
        //生成userID
        String id = UUIDTool.getUUID();
        user.setUserId(id);
        //新增用户
        userMapper.createUser(user);
        //向用户角色表插入userId
        userMapper.addUserRole(id);
        //向用户部门表插入userId
        userMapper.addUserGroup(id);
    }

    /**
     * 获取角色列表
     * @return
     */
    @Override
    public List<User> getRoleList() {
        List<User> result = userMapper.getRoleList();
        return result;

    }
/**
     * 获取部门列表
     * @return
     */
    @Override
    public List<User> getGroupList() {
        List<User> result = userMapper.getGroupList();
        return result;

    }

    /**
     * 分配角色和部门
     * @param user
     */
    @Override
    public String modifyURGInfoById(User user) {
        if (userMapper.modifyRoleByUser(user) && (userMapper.modifyGroupByUser(user))){
            return "success";
        }else {
            return "error";
        }
    }




}
