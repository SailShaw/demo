package com.springboot.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/")
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);



    @Resource
    private IUserService userService;

    @Resource
    private UserMapper userMapper;

    @RequestMapping("register")
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

    @RequestMapping("login")
    public String login(HttpServletResponse response,
                        HttpSession session,
                        User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        //账号是否存在
        User censor = userMapper.findUserByAccount(user);
        if (censor == null) {
            logger.error("用户名不存在");
            return "user is not exist";
        }else {
            boolean result= MD5.checkpassword(user.getPasscode(),censor.getPasscode());
            if (result) {
                session.setAttribute("user",censor);
                return "success";
            }else {
                return "login";
            }
        }
    }




//    @RequestMapping("/logout")
//    public String logout(HttpSession session){
//        session.removeAttribute();
//    }


}
