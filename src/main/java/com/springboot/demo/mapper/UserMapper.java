package com.springboot.demo.mapper;

import com.springboot.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Create By SINYA
 * Create Date 2019/2/27
 * Description:
 */

@Mapper
public interface UserMapper {


    //根据ID查询
    List<User> getUserByAccount(User user);

    //根据名称查询
    List<User> getAllUserInfo();

    //添加
    void addUser(User user);

    //更新
    void updateUser(User user);

    //删除
    void deleteUser(int id);

}
