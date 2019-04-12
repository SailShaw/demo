package com.springboot.demo.controller;

import cn.hutool.core.util.RandomUtil;
import com.springboot.demo.core.model.ResultData;
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
    public ResultData register(User user) throws IllegalAccessException {
        ResultData resultData = new ResultData();
        //小写转换
        user.setAccount(user.getAccount().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());
        //已注册查询
        User censor = userMapper.findUserByUser(user);
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(censor, new String[]{"serialVersionUID"})) {
            logger.info("register() -> 10002:USER_IS_EXIST");
            resultData.setCode(10002);
            resultData.setMassage("账号已存在");
        } else {
            //执行注册
            logger.info("register() -> SUCCESS");
            userService.register(user);
        }
        return resultData;
    }


    @RequestMapping("/login")
    public ResultData login(HttpServletRequest request, User user)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        ResultData resultData = new ResultData();
        //账号是否存在
        User censor = userMapper.findUserByUser(user);
        if (censor == null) {
            logger.info("login() -> 10003:USER_NOT_EXIST");
            resultData.setCode(10003);
            resultData.setMassage("用户不存在");
            return resultData;
        }
        boolean flag = MD5.checkpassword(user.getPassword(), censor.getPassword());
        if (!flag) {
            logger.info("login() -> PASSWORD_NOT_MATCH");
            resultData.setCode(10004);
            resultData.setMassage("密码不正确");
            return resultData;
        }
        //记录登录日期
        userService.recordLoginTime(censor);
        //存入session前将密码置为null
        censor.setPassword(null);
        //存入session
        request.getSession().setAttribute("user", censor);
        resultData.setMassage("登录成功");
        logger.info("login() -> SUCCESS");
        return resultData;
    }

    /**
     * 获取邮箱发送验证码
     *
     * @Descript:使用session存储user对象和验证码
     */
    @RequestMapping("/sendResetPasswordLink")
    public ResultData sendResetPasswordLink(HttpServletRequest request, User user) throws IllegalAccessException {
        ResultData resultData = new ResultData();
        Map<String, Object> map = new HashMap<>();
        //验证码
        int verifyCode = RandomUtil.randomInt(100000, 999999);
        //获取邮箱地址
        String account = request.getParameter("account").toLowerCase();
        String email = request.getParameter("email").toLowerCase();
        //注入对象
        user.setAccount(account);
        user.setEmail(email);
        //判空(空指针异常处理)
        //查询账号信息(账号邮箱不匹配时user为空)
        User userInfo = userMapper.findUserByUser(user);
        if (!ObjectHandle.reflectFieldIsNotALLNull(userInfo, new String[]{"serialVersionUID"})) {
            logger.debug("findUserByEmail() -> 10006:ACCOUNT_NOT_MATCH_EMAIL");
            resultData.setCode(10006);
            resultData.setMassage("账号与邮箱不匹配,请注意大小写");
        } else {
            map.put("verifyCode", verifyCode);
            map.put("userInfo", userInfo);
            //存入session
            request.getSession().setAttribute("content", map);
            //发送
            userService.sendVerificationCode(verifyCode, userInfo);
            logger.info("sendResetPasswordLink() -> SUCCESS");
        }
        return resultData;
    }

    /**
     * 根据验证码重置密码
     *
     * @param request
     * @return
     */
    @RequestMapping("/resetPasswordByCode")
    public ResultData resetPasswordByCode(HttpServletRequest request) {
        ResultData resultData = new ResultData();
        //1获取session中的数据
        Map<String, Object> map = new HashMap<>();
        map = (Map<String, Object>) request.getSession().getAttribute("content");
        //2接收数据
        String verifyCode = request.getParameter("verifyCode");
        String password = request.getParameter("password");
        User userInfo = (User) map.get("userInfo");
        //3注入对象(判空处理)
        if (!StringUtils.isEmpty(verifyCode) && verifyCode.equals(map.get("verifyCode") + "")) {
            //4修改密码
            userInfo.setPassword(password);
            userService.resetPassword(userInfo);
            //移除session
            request.getSession().removeAttribute("content");
            logger.info("resetPasswordByCode() -> SUCCESS");
        } else {
            logger.debug("resetPasswordByCode() -> 10007:VERIFYCODE_NOT_MATCH");
            resultData.setCode(10007);
            resultData.setMassage("验证码不正确");
        }
        return resultData;
    }

    /**
     * 注销
     *
     * @param request
     * @return
     */
    @RequestMapping("/loginOut")
    public ResultData logout(HttpServletRequest request) {
        //移除session
        request.getSession().removeAttribute("user");
        logger.info("loginOut() -> User Login Out");
        ResultData resultData = new ResultData();
        resultData.setMassage("注销成功");
        return resultData;
    }


}
