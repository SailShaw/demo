package com.springboot.demo.controller;

import com.springboot.demo.core.model.PageBean;
import com.springboot.demo.core.interceptor.aop.Operation;
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

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserService userService;
    @Resource
    private UserMapper userMapper;


    /**
     * 获取用户管理列表
     * @param request
     * @param user
     * @return
     */
//    @Operation(value = "获取用户管理列表")
    @RequestMapping("/getURGInfoListByPage")
    public PageBean<User> getURGInfoListByPage(HttpServletRequest request, User user){
        logger.info("getURGInfoListByPage()" + "Begin:");
        //定义数据集
        List<User> resultList = null;
        //分页
        try{
            // 获取分页参数
            Integer pageNum = StringUtils.isEmpty(request.getParameter("pageNum")) ? 1 : Integer.parseInt(request.getParameter("pageNum"));
            Integer pageSize = 9;

            resultList = userService.getURGInfoListByPage(pageNum,pageSize,user);
        }catch(Exception e){
            //logger
            logger.error("getURGInfoListByPage" + e);
        }
        //Page对象
        PageBean<User> pageBean = new PageBean<>(resultList);
        return pageBean;
    }


    /**
     * 根据ID修改用户信息
     * @param user
     */
    @Operation(value = "根据ID修改用户信息")
    @RequestMapping(value = "/modifyUserInfoById")
     public String modifyUserInfoById(User user){

        String result = null;

        if (user != null) {
            result = userService.modifyUserInfoById(user);
        } else {
            result = "JSON_IS_NULL";
        }
        return result;
    }


//    @Operation("查询用户信息")
    @RequestMapping("/getUserToFind")
    public String getUserToFind(HttpServletRequest request,User user) throws IllegalAccessException {
        String result = null;
        //判空
        if(!ObjectHandle.reflectFieldIsNotALLNull(user,new String[]{"serialVersionUID"})){
            result =  "JSON_IS_NULL";
        }else{
            //查询
            User cache = userMapper.findUserByUser(user);
            //存入session
            request.getSession().setAttribute("cache",cache);
            result = "SUCCESS";
        }
        return result;
    }
//    @Operation("获取用户信息")
    @RequestMapping("/getUserOnCache")
    public User getUserOnCache(HttpServletRequest request){
        User result = null;
        //空指针异常处理
        try {
            result = (User) request.getSession().getAttribute("cache");
        }catch (NullPointerException e){
            logger.error("getUserOnCache()"+e);
            result = null;
        }finally {
//            request.getSession().removeAttribute("cache");
            return result;
        }
    }



    /**
     * 修改密码
     * @param request
     * @return
     */
    @Operation(value = "修改密码")
    @RequestMapping("updatePassword")
    public String updatePasswordByUser(HttpServletRequest request) {
        String result = null;

        //获取session
        User censor = (User) request.getSession().getAttribute("user");
        //获取完整信息
        User user = userMapper.findUserByUser(censor);

        //获取输入的旧密码
        String oldPassword = request.getParameter("oldPassword");
        //获取输入的新密码
        String newPassword = request.getParameter("newPassword");

        //验证旧密码是否与数据库中相同
        boolean flag= false;
        try {
            flag = MD5.checkpassword(oldPassword, user.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (flag == true) {
            //执行密码修改
            user.setPassword(newPassword);
            userService.updatePassword(user);
            result =  "SUCCESS";
        }else {
            result =  "PASSWORD_NOT_MATCH";
        }

        return result;
    }

    /**
     * 修改用户角色与部门信息
     * @param request
     * @param user
     * @return
     */
    @Operation(value = "修改用户角色与部门信息")
    @RequestMapping("/modifyURGInfo")
    public String  modifyURGInfoById(HttpServletRequest request,User user){

        String result = null;

        //获取当前用户
        User censor = (User) request.getSession().getAttribute("user");
        //判空
        if (user != null) {
            //设置更新者
            user.setUpdateBy(censor.getZnName());
            result = userService.modifyURGInfoById(user);
        } else {
            result = "JSON_IS_NULL";
        }
        return result;
    }

    /**
     * 删除用户
     * @param user
     */
//    @Operation(value = "删除用户")
    @RequestMapping("/deleteUserByID")
    public String deleteUserByID(HttpServletRequest request,User user){

        String result = null;

        User operator = (User) request.getSession().getAttribute("user");

        if (user != null) {
            //设置更新者
            user.setUpdateBy(operator.getZnName());
            result = userService.deleteUserByID(user);
        } else {
            result = "JSON_IS_NULL";
        }
        return result;
    }
}
