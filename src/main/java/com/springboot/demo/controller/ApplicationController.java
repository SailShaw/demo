package com.springboot.demo.controller;

import com.springboot.demo.core.common.PageBean;
import com.springboot.demo.core.interceptor.aop.Operation;
import com.springboot.demo.entity.Application;
import com.springboot.demo.entity.User;
import com.springboot.demo.service.IApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/app")
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
    @RequestMapping(value = "/getAllFormByDept")
    public PageBean<Application> getAllFormByDept(HttpServletRequest request, Application application){

        //定义数据集
        List<Application> resultList = null;

        //获取登陆用户信息
        User user = (User) request.getSession().getAttribute("user");
        //设置查询条件
        application.setGroupId(user.getGroupId());

        try{
            // 获取分页参数
            Integer pageNum = StringUtils.isEmpty(request.getParameter("pageNum")) ? 1 : Integer.parseInt(request.getParameter("pageNum"));
            Integer pageSize = 9;
            resultList = applicationService.getAllFormByDept(pageNum,pageSize,application);
        }catch(Exception e){
            logger.error("getAllFormByDept()"+e);
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
    @RequestMapping(value = "/getAllFormByUser")
    public PageBean<Application> getAllFormByUser(HttpServletRequest request, Application application){
        //logger

        //定义数据集
        List<Application> resultList = null;
        //获取登陆用户信息
        User user = (User) request.getSession().getAttribute("user");
        //设置查询条件
        application.setUserId(user.getUserId());
        //分页
        try{
            // 获取分页参数
            Integer pageNum = StringUtils.isEmpty(request.getParameter("pageNum")) ? 1 : Integer.parseInt(request.getParameter("pageNum"));
            Integer pageSize = 9;
            resultList = applicationService.getAllFormByUser(pageNum,pageSize,application);
        }catch(Exception e){
            logger.error("getAllFormByUser"+e);
        }
        //Page对象
        PageBean<Application> pageBean = new PageBean<>(resultList);
        return pageBean;
    }

    /**
     * 审核申请表
     * @param application
     */
    @Operation(value = "审核申请表")
    @RequestMapping(value = "/modifyFormStatusByFormId")
    public String modifyFormStatusByFormId(HttpServletRequest request,Application application){

        //获取登陆用户信息
        User user = (User) request.getSession().getAttribute("user");
        //设置审核人
        application.setReviewer(user.getZnName());

        //返回结果
        String result = "";

        if (applicationService.modifyFormStatusByFormId(application)){
            result = "success";
        }else {
            result = "error";
        }
        return result;
    }


    /**
     * 修改申请表
     * @param application
     */
    @Operation(value = "修改申请表")
    @RequestMapping(value = "/modifyFormInfoByFormId")
    public String modifyFormInfoByFormId(Application application){
        //返回结果
        String result = "";

        if (applicationService.modifyFormInfoByFormId(application)){
            result = "success";
        }else {
            result = "error";
        }
        return result;
    }

    /**
     * 新增申请表
     * @param application
     */
    @Operation(value = "添加申请表")
    @RequestMapping(value = "/createAppForm")
    public String createAppForm(HttpServletRequest request,Application application){
        //用户ID、部门ID直接获取当前用户的数据
        User user = (User) request.getSession().getAttribute("user");
        application.setUserId(user.getUserId());
        application.setGroupId(user.getGroupId());

        //返回结果
        String result = "";

        if (applicationService.createAppForm(application)) {
            result = "success";
        }else {
            result = "error";
        }
        return result;

    }

    @Operation("关闭表单")
    @RequestMapping("/closeFormById")
    public String closeFormById(Application application){
        String result = null;

        if (application != null) {
            result = applicationService.closeFormById(application);
        }else {
            logger.error("closeFormById() -> Object is Null");
            result = "JSON_IS_NULL";
        }
        return result;
    }

    @Operation("删除表单")
    @RequestMapping("/closeFormById")
    public String deleteFormById(Application application){
        String result = null;

        if (application != null) {
            result = applicationService.deleteFormById(application);
        }else {
            logger.error("closeFormById() -> Object is Null");
            result = "JSON_IS_NULL";
        }
        return result;
    }


    /**
     * 获取表单详情
     * @param application
     * @return
     */
    @RequestMapping("/getFormInfoByFormId")
    public Application getFormInfoByFormId(Application application){
        Application details = null;
        if (application != null) {
            details = applicationService.getFormInfoByFormId(application);
        }else{
            logger.error("getFormInfoByFormId() -> Object is Null");
            return null;
        }
        return details;

    }

}
