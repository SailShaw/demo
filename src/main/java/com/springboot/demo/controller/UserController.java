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
import java.util.List;

/**
 * Create By SINYA
 * Create Date 2019/2/27
 * Description:
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;


    @RequestMapping(value = "/getUserByAccount",method = RequestMethod.GET)

    public List<User> getUserByAccount(User user){
        List<User> resultList = userService.getUserByAccount(user);
        return resultList;
    }

    @RequestMapping(value = "/getAllUserInfo",method = RequestMethod.GET)
    public List<User> getAllUserInfo(){
        List<User> list = userService.getAllUserInfo();
        return list;
    }

    @RequestMapping(value = "/createUser",method = RequestMethod.GET)
    public void createUser(User user){
        userService.addUser(user);
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.GET)
    public void updateUser(User user){
        userService.updateUser(user);
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    public void deleteUser(String id){
        userService.deleteUser(id);
    }


}
