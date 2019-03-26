package com.springboot.demo.controller;

import cn.hutool.core.util.RandomUtil;
import com.springboot.demo.core.common.PageBean;
import com.springboot.demo.core.interceptor.aop.Operation;
import com.springboot.demo.entity.User;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By SINYA
 * Create Date 2019/2/27
 * Description: Controller For User
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
     * 根据账号获取用户信息
     * @param user
     * @return
     */
    @Operation(value = "根据账号获取用户信息")
    @RequestMapping("/getUserByAccount")
    public List<User> getUserByAccount(User user){
        List<User> resultList = userService.getUserByAccount(user);
        return resultList;
    }

    /**
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("/getUserInfoById")
    public User getUserInfoById(HttpServletRequest request,User user){
        //从session里获取用户信息
        User userInfo = (User) request.getSession().getAttribute("user");
//        List<User> result = (List<User>) userInfo;
        return userInfo;
    }

    /**
     * 获取用户管理列表
     * @param request
     * @param user
     * @return
     */
    @Operation(value = "获取用户管理列表")
    @RequestMapping("/getURGInfoListByPage")
    public PageBean<User> getURGInfoListByPage(HttpServletRequest request, User user){
        logger.info("getURGInfoListByPage()" + "Begin:");
        //定义数据集
        List<User> resultList = null;
        //分页
        try{
            // 获取分页参数
            String _pageNum = request.getParameter("pageNum");
            String _pageSize = request.getParameter("pageSize");
            //转型+判空(设置默认分页大小为7)
            Integer pageNum = StringUtils.isEmpty(_pageNum) ? 1 : Integer.parseInt(_pageNum);
            Integer pageSize = StringUtils.isEmpty(_pageSize) ? 7 : Integer.parseInt(_pageSize);

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
    @RequestMapping(value = "/modifyUserInfoById",method = RequestMethod.POST)
     public void modifyUserInfoById(User user){
        userService.modifyUserInfoById(user);
    }

    /**
     * 删除用户
     * @param user
     */
    @Operation(value = "删除用户")
    @RequestMapping(value = "/deleteUserByID",method = RequestMethod.POST)
    public String deleteUserByID(User user){
        String result = userService.deleteUserByID(user);
        return result;
    }


    /**
     * 获取邮箱发送验证码
     * @Descript:使用session存储user对象和验证码
     */
    @Operation(value = "获取邮箱发送验证码")
    @RequestMapping("/sendResetPasswordLink")
    public void sendResetPasswordLink(HttpServletRequest request,User user){
        //验证码
        int VerifyComde =RandomUtil.randomInt(999999);
        //获取邮箱地址
        String mailAddress = request.getParameter("email");
        //查询账号信息
        User userInfo = userMapper.findUserByEmail(user);
        Map<String,Object> map = new HashMap<>();

        map.put("VerifyComde",VerifyComde);
        map.put("userInfo",userInfo);
        //存入session
        request.getSession().setAttribute("content",map);
        //发送
        userService.sendVerificationCode(VerifyComde,user);

    }

    @Operation(value = "重置密码")
    @RequestMapping("/resetPasswordByCode")
    public String resetPasswordByCode(HttpServletRequest request,User user){
        //获取session中的数据
        Map<String,Object> map = new HashMap<>();
        map = (Map<String, Object>) request.getSession().getAttribute("content");
        String code = request.getParameter("VerifyCode");
        String password = request.getParameter("password");
        User userInfo = (User) map.get("userInfo");
        //注入对象

        if (code.equals(map.get("VerifyComde"))) {
            userInfo.setPasscode(password);
            userService.resetPassword(userInfo);
            return "success";
        }else{
            logger.error("resetPasswordByCode():error->"+code);
            return "error";
        }

    }

    /**
     * 获取角色列表
     * @return
     */
    @RequestMapping(value = "/getRoleList")
    public List<User> getRoleList(){
        List<User> result = userService.getRoleList();
        return result;
    }

    /**
     * 获取部门列表
     * @return
     */
    @RequestMapping(value = "/getGroupList")
    public List<User> getGroupList(){
        List<User> result = userService.getGroupList();
        return result;
    }

    @Operation(value = "修改用户角色与部门信息")
    @RequestMapping("/modifyURGInfo")
    public String  modifyURGInfoById(User user){
        String result = userService.modifyURGInfoById(user);
        return result;
    }



}
