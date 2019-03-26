package com.springboot.demo.controller;

import com.springboot.demo.core.interceptor.aop.Operation;
import com.springboot.demo.entity.User;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.service.IUserService;
import com.springboot.demo.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Create By SINYA
 * Description:
 */

@RestController
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);



    @Resource
    private IUserService userService;

    @Resource
    private UserMapper userMapper;

    @Operation(value = "注册账号")
    @RequestMapping("register")
    public String register(User user){

        //小写转换
        user.setAccount(user.getAccount().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());
        user.setPasscode(user.getPasscode().toLowerCase());

        //已注册查询
        User censor = userMapper.findUserByAccount(user);
        if (censor != null) {
            return "USER_IS_EXIST";
        }else {
            userService.register(user);
            return "SUCCESS";
        }

    }

    @Operation(value = "登录系统")
    @RequestMapping("login")
    public String login(HttpServletRequest request,
                        User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //账号是否存在
        User censor = userMapper.findUserByAccount(user);
        if (censor == null) {
            logger.error("用户名不存在");
            return "USER_NOT_EXIST";
        }else {
            boolean result= MD5.checkpassword(user.getPasscode(),censor.getPasscode());
            if (result == true) {
                //存入session
                request.getSession().setAttribute("user",censor);
                return "success";
            }else {
                return "login";
            }
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "success";
    }


}
