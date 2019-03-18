package com.springboot.demo.controller;

import com.springboot.demo.core.interceptor.aop.SystemControllerLog;
import com.springboot.demo.entity.User;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.service.IUserService;
import com.springboot.demo.util.MD5;
import com.springboot.demo.util.RegexUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Create By SINYA
 * Description:
 */

@RestController
public class LoginController {


    @Resource
    private IUserService userService;

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/register")
    @SystemControllerLog(description = "注册")
    public String register(User user,HttpServletResponse response){

        //小写转换
        user.setAccount(user.getAccount().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());
        user.setPasscode(user.getPasscode().toLowerCase());

        //校验
        if (!RegexUtil.reviewAccount(user.getAccount())) {
            return "账号格式不正确";
        }
        if (!RegexUtil.reviewAccount(user.getEmail())) {
            return "邮箱格式不正确";
        }
        if (!RegexUtil.reviewAccount(user.getPasscode())) {
            return "密码格式不正确";
        }
        //已注册查询
        User censor = userMapper.findUserByAccount(user);
        if (censor != null) {
            return "用户名已存在";
        }else {
            userService.register(user);
            return "success";
        }

    }

    @RequestMapping("/login")
    @SystemControllerLog(description = "登录")
    public String login(User user, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        //校验
        if (!RegexUtil.reviewAccount(user.getAccount())) {
            return "账号格式不正确";
        }
        if (!RegexUtil.reviewAccount(user.getPasscode())) {
            return "密码格式不正确";
        }
        //账号是否存在
        User censor = userMapper.findUserByAccount(user);
        if (censor == null) {
            return "用户名不存在";
        }else {
            boolean result= MD5.checkpassword(user.getPasscode(),censor.getPasscode());
            if (result) {
                session.setAttribute("user",censor);
                return "success";
            }else {
                return "密码错误";
            }
        }
    }


}
