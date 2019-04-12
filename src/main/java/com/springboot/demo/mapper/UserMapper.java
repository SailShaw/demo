package com.springboot.demo.mapper;

import com.springboot.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Mapper for User
 */

@Mapper
public interface UserMapper {

    /**
     * 1.User里可根据id,account,email来查找
     * 2.可通过多个条件避免查询到重复数据
     * @param user
     * @return
     */
    User findUserByUser(@Param("user")User user);


    /**
     * 获取用户管理清单
     * @param user
     * @return
     */
    List<User> getURGInfoListByPage(User user);

    //修改账号信息

    void modifyUserInfoById(User user);

    /**
     * 逻辑删除用户
     * 1.通过leftJoin关联ID,实现多表同时更新,
     * @param user
     * @return
     */
    void deleteUserByID(User user);



    /**
     * 1.修改用户所属部门
     * @param user
     * @return
     */
    void modifyRoleAndGroupByUser(User user);

    //获取角色下拉列表
    List<User> getRoleList();

    //获取部门下拉列表
    List<User> getGroupList();

    /**
     * 1.注册 - 用户表
     * @param user
     */
    void createUser(User user);

    /**
     * 2.注册 - 角色表
     * @param user
     */
    void addUserRole(User user);

    /**
     * 3.注册 - 组织表
     * @param user
     */
    void addUserGroup(User user);


    /**
     * 重置密码
     * @param user
     */
    void resetPassword(User user);

    /**
     * 记录登录时间
     * @param user
     */
    void recordLoginTime(User user);

    /**
     * 修改密码
     * @param user
     * @return
     */
    void updatePassword(User user);
}
