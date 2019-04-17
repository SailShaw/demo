package com.springboot.demo.controller;

import com.springboot.demo.core.interceptor.aop.Validate;
import com.springboot.demo.core.model.PageBean;
import com.springboot.demo.core.interceptor.aop.Operation;
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
import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Controller for User
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private final static String ERROR_INFO = "20001:JSON_IS_NULL";
    private final static String ERROR_PassWord = "10004:PASSWORD_NOT_MATCH";
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserService userService;
    @Resource
    private UserMapper userMapper;


    /**
     * 获取用户管理列表
     *
     * @param request
     * @param user
     * @return
     */

    @Validate
    @Operation(value = "获取用户管理列表")
    @RequestMapping("/getURGInfoListByPage")
    public ResultData getURGInfoListByPage(HttpServletRequest request, User user) {
        logger.info(" getURGInfoListByPage() -> Begin");
        //定义数据集
        ResultData resultData = new ResultData();
        List<User> resultList = null;
        //分页
        try {
            // 获取分页参数
            Integer pageNum = StringUtils.isEmpty(request.getParameter("pageNum")) ? 1 : Integer.parseInt(request.getParameter("pageNum"));
            Integer pageSize = 9;
            resultList = userService.getURGInfoListByPage(pageNum, pageSize, user);
        } catch (Exception e) {
            //logger
            logger.error(" getURGInfoListByPage()->" + e);
        }
        //Page对象
        PageBean<User> pageBean = new PageBean<>(resultList);
        resultData.setData(pageBean);
        logger.info(" getURGInfoListByPage() -> End");
        return resultData;
    }


    /**
     * 根据ID修改用户信息
     *
     * @param user
     */
    @Operation(value = "根据ID修改用户信息")
    @RequestMapping(value = "/modifyUserInfoById")
    public ResultData modifyUserInfoById(HttpServletRequest request,User user) throws IllegalAccessException {
        logger.info(" modifyUserInfoById() -> Begin");
        ResultData resultData = new ResultData();
        //从session里获取当前用户的名字
        User userInfo = (User) request.getSession().getAttribute("user");
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(user,new String[]{"serialVersionUID"})){
            //设置id
            user.setUserId(userInfo.getUserId());
            //执行修改
            userService.modifyUserInfoById(user);
            //Session替换
            request.getSession().removeAttribute("user");
            User reUser = userMapper.findUserByUser(user);
            //移除密码
            reUser.setPassword(null);
            request.getSession().setAttribute("user",reUser);
        }else{
            logger.error(" modifyUserInfoById() ->" + ERROR_INFO);
            resultData.setCode(20001);
            resultData.setMessage("接收到的数据为空");
        }
        logger.info(" modifyUserInfoById() -> End");
        return resultData;
    }


    @Operation("查询用户信息")
    @RequestMapping("/getUserToCache")
    public ResultData getUserToCache(HttpServletRequest request, User user) throws IllegalAccessException {
        logger.info(" getUserToCache() -> Begin");
        ResultData resultData = new ResultData();
        //从session里获取当前用户的名字
        User userInfo = (User) request.getSession().getAttribute("user");
        //判空
        if (!ObjectHandle.reflectFieldIsNotALLNull(user, new String[]{"serialVersionUID"})) {
            logger.error(" getUserToCache() ->" + ERROR_INFO);
            resultData.setCode(20001);
            resultData.setMessage("接收到的数据为空");
        } else {
            //执行查询
            User cache = userMapper.findUserByUser(user);
            //存入session
            request.getSession().setAttribute("cache", cache);
            resultData.setMessage("修改成功");
        }
        return resultData;
    }

    @Operation("获取用户信息")
    @RequestMapping("/getUserOnCache")
    public ResultData getUserOnCache(HttpServletRequest request) throws IllegalAccessException {
        logger.info(" getUserOnCache() -> Begin");
        ResultData resultData = new ResultData();
        //从session里获取当前用户的名字
        User userInfo = (User) request.getSession().getAttribute("cache");
        //判空
        if (!ObjectHandle.reflectFieldIsNotALLNull(userInfo, new String[]{"serialVersionUID"})) {
            logger.error(" getUserOnCache() ->" + ERROR_INFO);
            resultData.setCode(20001);
            resultData.setData(null);
            resultData.setMessage("接收到的数据为空");
        }else {
            resultData.setData(userInfo);
        }
        logger.info(" getUserOnCache() -> End");
        return resultData;
    }


    /**
     * 修改密码
     *
     * @param request
     * @return
     */
    @Validate
    @Operation(value = "修改密码")
    @RequestMapping("/updatePassword")
    public ResultData updatePasswordByUser(HttpServletRequest request) {
        logger.info(" updatePasswordByUser() -> Begin");
        ResultData resultData = new ResultData();
        //获取session
        User censor = (User) request.getSession().getAttribute("user");
        //获取完整信息
        User user = userMapper.findUserByUser(censor);
        //获取输入的旧密码
        String oldPassword = request.getParameter("oldPassword");
        //获取输入的新密码
        String newPassword = request.getParameter("newPassword");
        String reNewPassword = request.getParameter("reNewPassword");
        if (!newPassword.equals(reNewPassword)){
            resultData.setCode(10004);
            resultData.setMessage("两次输入的密码不一致");
            return resultData;
        }
        //验证旧密码是否与数据库中相同
        boolean flag = false;
        try {
            flag = MD5.checkpassword(oldPassword, user.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (flag) {
            //执行密码修改
            user.setPassword(newPassword);
            userService.updatePassword(user);
        } else {
            logger.error(" updatePasswordByUser() ->" + ERROR_PassWord);
            resultData.setCode(10004);
            resultData.setMessage("旧密码不匹配");
        }
        return resultData;
    }

    /**
     * 修改用户角色与部门信息
     *
     * @param request
     * @param user
     * @return
     */
    @Operation(value = "修改用户角色与部门信息")
    @RequestMapping("/modifyURGInfo")
    public ResultData modifyURGInfoById(HttpServletRequest request, User user) throws IllegalAccessException {
        logger.info(" modifyURGInfoById() -> Begin");
        ResultData resultData = new ResultData();
        //从session里获取当前用户的名字
        User userInfo = (User) request.getSession().getAttribute("user");
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(userInfo, new String[]{"serialVersionUID"})) {
            //注入对象
            user.setUpdateBy(userInfo.getZnName());
            userService.modifyURGInfoById(user);
        } else {
            logger.error(" modifyURGInfoById() ->" + ERROR_INFO);
            resultData.setCode(20001);
            resultData.setMessage("接收到的数据为空");
        }
        logger.info(" modifyURGInfoById() -> End");
        return resultData;
    }

    /**
     * 删除用户
     *
     * @param user
     */
    @Operation(value = "删除用户")
    @RequestMapping("/deleteUserByID")
    public ResultData deleteUserByID(HttpServletRequest request, User user) throws IllegalAccessException {
        logger.info(" deleteUserByID() -> Begin");
        ResultData resultData = new ResultData();
        //从session里获取当前用户的名字
        User userInfo = (User) request.getSession().getAttribute("user");
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(userInfo, new String[]{"serialVersionUID"})) {
            //注入对象
            user.setUpdateBy(userInfo.getZnName());
            //执行
            userService.deleteUserByID(user);
        } else {
            logger.error(" deleteUserByID() ->" + ERROR_INFO);
            resultData.setCode(20001);
            resultData.setMessage("接收到的数据为空");
        }
        logger.info(" deleteUserByID() -> End");
        return resultData;
    }
}
