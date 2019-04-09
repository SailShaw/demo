package com.springboot.demo.controller;

import cn.hutool.core.util.RandomUtil;
import com.springboot.demo.entity.User;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.service.IUserService;
import com.springboot.demo.util.MD5;
import com.springboot.demo.util.ObjectHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Controller for Login
 */

@RestController
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Resource
    private IUserService userService;

    @Resource
    private UserMapper userMapper;


    @RequestMapping("/register")
    public String register(User user) throws IllegalAccessException {
        String result = null;
        //小写转换
        user.setAccount(user.getAccount().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(user.getPassword().toLowerCase());
        //已注册查询
        User censor = userMapper.findUserByUser(user);
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(censor, new String[]{"serialVersionUID"})) {
            logger.error("register() -> USER_IS_EXIST");
            result =  "USER_IS_EXIST";
        }else {

            result = userService.register(user); ;
        }
        return result;
    }


    @RequestMapping("/login")
    public String login(HttpServletRequest request, User user)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String result = null;
        //账号是否存在
        User censor = userMapper.findUserByUser(user);
        if (censor == null) {
            logger.error("login() -> USER_NOT_EXIST");
            return "USER_NOT_EXIST";
        }else {
            boolean flag= MD5.checkpassword(user.getPassword(),censor.getPassword());
            if (flag == true) {
                //记录登录日期
                userService.recordLoginTime(censor);
                //将密码置为null
                censor.setPassword(null);
                //存入session
                request.getSession().setAttribute("user",censor);
                result =  "SUCCESS";
            }else {
                logger.error("login() -> PASSWORD_NOT_MATCH");
                result =  "PASSWORD_NOT_MATCH";
            }
        }
        return result;
    }

    /**
     * 获取邮箱发送验证码
     * @Descript:使用session存储user对象和验证码
     */
    @RequestMapping("/sendResetPasswordLink")
    public String sendResetPasswordLink(HttpServletRequest request,User user){
        String result = null;
        //验证码
        int verifyCode = RandomUtil.randomInt(100000,999999);
        Map<String,Object> map = new HashMap<>();
        //获取邮箱地址
        String account = request.getParameter("account").toLowerCase();
        String email = request.getParameter("email").toLowerCase();
        //注入对象
        user.setAccount(account);user.setEmail(email);
        //判空(空指针异常处理)
        try{
            //查询账号信息(账号邮箱不匹配时user为空)
            User userInfo = userMapper.findUserByUser(user);
            map.put("verifyCode",verifyCode);
            map.put("userInfo",userInfo);
            //存入session
            request.getSession().setAttribute("content",map);
            //发送
            userService.sendVerificationCode(verifyCode,userInfo);
            logger.info("sendResetPasswordLink -> 成功");
            result =  "SUCCESS";
        }catch (NullPointerException e){
            logger.error("findUserByEmail() -> NullPointerException");
            result =  "ACCOUNT_NOT_MATCH_EMAIL";
            //移除session
            request.getSession().removeAttribute("content");
        }finally {
            return result;
        }
    }

    /**
     * 根据验证码重置密码
     * @param request
     * @return
     */
    @RequestMapping("/resetPasswordByCode")
    public String resetPasswordByCode(HttpServletRequest request){
        String result = null;
        //获取session中的数据
        Map<String,Object> map = new HashMap<>();
        map = (Map<String, Object>) request.getSession().getAttribute("content");

        String verifyCode = request.getParameter("verifyCode");
        String password = request.getParameter("passcode");
        User userInfo = (User) map.get("userInfo");
        //注入对象(判空处理)
        if ((!StringUtils.isEmpty(verifyCode)) && verifyCode.equals(map.get("verifyCode") + "")) {
            userInfo.setPassword(password);
            userService.resetPassword(userInfo);
            //移除session
            request.getSession().removeAttribute("content");
            result =  "SUCCESS";

        }else{
            logger.error("resetPasswordByCode():error->"+verifyCode);
            result =  "VERIFYCODE_NOT_MATCH";
        }
        return result;
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @RequestMapping("/loginOut")
    public String logout(HttpServletRequest request){
        String result = null;
        //移除session
        request.getSession().removeAttribute("user");
        result =  "SUCCESS";
        logger.info("loginOut() -> User Login Out");
        return result;
    }


}
