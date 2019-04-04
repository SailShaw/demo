package com.springboot.demo.controller;

import com.springboot.demo.core.model.PageBean;
import com.springboot.demo.core.interceptor.aop.Operation;
import com.springboot.demo.entity.Application;
import com.springboot.demo.entity.User;
import com.springboot.demo.service.IApplicationService;
import com.springboot.demo.util.ObjectHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Controller for Application
 */

@RestController
@RequestMapping("/application")
public class ApplicationController {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Resource
    private IApplicationService applicationService;

    /**
     * 获取本部门下所有记录
     * @param application
     * @return
     */
    @Operation(value = "获取本部门下所有记录")
    @RequestMapping(value = "/getFormListByDept")
    public PageBean<Application> getFormListByDept(HttpServletRequest request, Application application) {
        //定义数据集
        List<Application> resultList = null;
        //获取登陆用户信息
        User user = (User) request.getSession().getAttribute("user");
        //设置查询条件
        application.setGroupId(user.getGroupId());
        try {
            // 获取分页参数
            Integer pageNum = StringUtils.isEmpty(request.getParameter("pageNum")) ? 1 : Integer.parseInt(request.getParameter("pageNum"));
            Integer pageSize = 9;
            resultList = applicationService.getFormListByDept(pageNum, pageSize, application);
        } catch (Exception e) {
            logger.error("getFormListByDept()" + e);
        }
        //Page对象
        PageBean<Application> pageBean = new PageBean<>(resultList);
        return pageBean;
    }

    /**
     * 获取本人所有申请记录
     * @param application
     * @return
     */
    @Operation(value = "获取本人所有申请记录")
    @RequestMapping(value = "/getFormListByUser")
    public PageBean<Application> getFormListByUser(HttpServletRequest request, Application application) {
        //定义数据集
        List<Application> resultList = null;
        //获取登陆用户信息
        User user = (User) request.getSession().getAttribute("user");
        //设置查询条件
        application.setUserId(user.getUserId());
        //分页
        try {
            // 获取分页参数
            Integer pageNum = StringUtils.isEmpty(request.getParameter("pageNum")) ? 1 : Integer.parseInt(request.getParameter("pageNum"));
            Integer pageSize = 9;
            resultList = applicationService.getFormListByUser(pageNum, pageSize, application);
        } catch (Exception e) {
            logger.error("getFormListByUser" + e);
        }
        //Page对象
        PageBean<Application> pageBean = new PageBean<>(resultList);
        return pageBean;
    }

    /**
     * 审核申请表
     *
     * @param application
     */
    @Operation(value = "审核申请表")
    @RequestMapping(value = "/verifyFormById")
    public String verifyFormById(HttpServletRequest request, Application application) throws IllegalAccessException {
        String result = "";
        //获取登陆用户信息
        User user = (User) request.getSession().getAttribute("user");
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(application, new String[]{"serialVersionUID"})){
            application.setReviewer(user.getZnName());
            if (applicationService.verifyFormById(application)) {
                result = "SUCCESS";
            } else {
                result = "ERROR";
            }
        }else {
            logger.error("verifyFormById() -> Json is Null");
            result = "JSON_IS_NULL";
        }


        return result;
    }


    /**
     * 修改申请表
     *
     * @param application
     */
    @Operation(value = "修改申请表")
    @RequestMapping(value = "/modifyFormById")
    public String modifyFormById(Application application) throws IllegalAccessException {
        String result = "";
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(application, new String[]{"serialVersionUID"})){
            if (applicationService.modifyFormById(application)) {
                result = "SUCCESS";
            } else {
                result = "ERROR";
            }
        }else {
            logger.error("modifyFormById() -> Json is Null");
            result = "JSON_IS_NULL";
        }
        return result;
    }

    /**
     * 新增申请表
     * @param application
     */
//    @Operation(value = "添加申请表")
    @RequestMapping(value = "/createAppForm")
    public String createAppForm(HttpServletRequest request, Application application) throws IllegalAccessException {
        String result = "";
        //获取session中的用户信息
        User user = (User) request.getSession().getAttribute("user");
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(application, new String[]{"serialVersionUID"})){
            //注入对象
            application.setUserId(user.getUserId());
            application.setGroupId(user.getGroupId());
            if (applicationService.createAppForm(application)) {
                result = "success";
            } else {
                result = "error";
            }
        }else {
            logger.error("createAppForm() -> Json is Null");
            result = "JSON_IS_NULL";
        }
        //返回结果
        return result;
    }

//    @Operation("关闭表单")
    @RequestMapping("/closeFormById")
    public String closeFormById(Application application) {
        String result = null;

        if (application != null) {
            result = applicationService.closeFormById(application);
        } else {
            logger.error("closeFormById() -> Object is Null");
            result = "JSON_IS_NULL";
        }
        return result;
    }

//    @Operation("删除表单")
    @RequestMapping("/deleteFormById")
    public String deleteFormById(Application application) throws IllegalAccessException {
        String result = null;

        if (ObjectHandle.reflectFieldIsNotALLNull(application, new String[]{"serialVersionUID"})) {
            result = applicationService.deleteFormById(application);
        } else {
            logger.error("closeFormById() -> Object is Null");
            result = "JSON_IS_NULL";
        }
        return result;
    }


    //    @Operation("存入表单数据")
    @RequestMapping("/getDetailToCache")
    public String getDetailToCache(HttpServletRequest request, Application application) throws IllegalAccessException {
        String result = null;
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(application, new String[]{"serialVersionUID"})) {
            //查询
            Application cache = applicationService.getFormInfoByFormId(application);
            //存入session
            request.getSession().setAttribute("cache", cache);
            result = "SUCCESS";
        } else {
            logger.error("getDetailToCache() -> Json is null");
            result = "JSON_IS_NULL";
        }
        return result;
    }

    //    @Operation("获取表单详情")
    @RequestMapping("/getDetailOnCache")
    public Application getDetailOnCache(HttpServletRequest request) throws IllegalAccessException {
        Application result = null;
        result = (Application) request.getSession().getAttribute("cache");
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(result, new String[]{"serialVersionUID"})) {
            return result;
        }else {
            logger.error("getDetailOnCache() -> Session is null");
            return null;
        }
    }

}
