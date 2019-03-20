package com.springboot.demo.controller;

import com.springboot.demo.core.common.PageBean;
import com.springboot.demo.core.interceptor.aop.Operation;
import com.springboot.demo.entity.User;
import com.springboot.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    private final static Logger logger = LoggerFactory.getLogger(SysController.class);

    @Resource
    private IUserService userService;


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
     * 获取用户管理列表
     * @param request
     * @param user
     * @return
     */
    @Operation(value = "获取用户管理列表")
    @RequestMapping("/getURGInfoListByPage")
    public PageBean<User> getURGInfoListByPage(HttpServletRequest request, User user){
        //logger

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
    public void deleteUserByID(User user){
        userService.deleteUserByID(user);
    }


    /**
     * 获取邮箱发送重置密码链接以及验证码
     */
    @Operation(value = "获取邮箱发送重置密码链接以及验证码")
    @RequestMapping(value = "/modifyPasswordByEmail",method = RequestMethod.POST)
    public void modifyPasswordByEmail(User user){

//        userService.ModifyPassWordByEmail(user);
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
