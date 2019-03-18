package com.springboot.demo.mapper;

import com.springboot.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    void modifyUserInfoById(User user);

    /**
     * 删除用户
     * @param user
     */
    void deleteUserByID(User user);

    /**
     * 修改用户所属部门
     * @param user
     */
    boolean modifyGroupByUser(User user);

    /**
     * 修改用户所属角色
     * @param user
     */
    boolean modifyRoleByUser(User user);

    /**
     * 注册-用户表
     * @param user
     */


    /**
     * 注册-用户组织关系表
     * @param id
     */


    /**
     * 注册-用户角色关系表
     * @param id
     */



    //获取角色列表
    List<User> getRoleList();
    //获取部门列表
    List<User> getGroupList();

    //注册步骤1-查重
    User findUserByAccount(User user);
    //注册步骤2-User表新增
    void createUser(User user);
    //注册步骤3-关系表新增
    void addUserRole(String id);
    void addUserGroup(String id);
}
