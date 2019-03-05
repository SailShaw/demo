package com.springboot.demo.controller;

import com.springboot.demo.entity.User;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Create By SINYA
 * Create Date 2019/2/27
 * Description: Controller For User
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;


    /**
     * 根据账号获取用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/getUserByAccount",method = RequestMethod.GET)
    public List<User> getUserByAccount(User user){
        List<User> resultList = userService.getUserByAccount(user);
        return resultList;
    }


    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping(value = "/createUser")
    public String createUser(User user){
        userService.createUser(user);
        return "test";
    }

    /**
     * 根据ID修改用户信息
     * @param user
     */
    @RequestMapping(value = "/modifyUserInfoById",method = RequestMethod.POST)
     public void modifyUserInfoById(User user){
        userService.modifyUserInfoById(user);
    }

    /**
     * 删除用户
     * @param user
     */
    @RequestMapping(value = "/deleteUserByID",method = RequestMethod.POST)
    public void deleteUserByID(User user){
        userService.deleteUserByID(user);
    }


    /**
     * 获取邮箱发送重置密码链接以及验证码
     */
    @RequestMapping(value = "/modifyPasswordByEmail",method = RequestMethod.POST)
    public void modifyPasswordByEmail(User user){

//        userService.ModifyPassWordByEmail(user);
    }



}
