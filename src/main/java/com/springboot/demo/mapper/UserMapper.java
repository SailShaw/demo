package com.springboot.demo.mapper;

import com.springboot.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Create By SINYA
 * Create Date 2019/2/27
 * Description: Mapper For User
 */

@Mapper
public interface UserMapper {


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

    /**
     * 注册-用户表
     * @param user
     */
    void createUser(User user);

    /**
     * 注册-用户组织关系表
     * @param id
     */
    void addUserGroup(String id);

    /**
     * 注册-用户角色关系表
     * @param id
     */
    void addUserRole(String id);

}
