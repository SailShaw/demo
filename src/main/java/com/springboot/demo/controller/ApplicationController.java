package com.springboot.demo.controller;

import com.springboot.demo.core.interceptor.aop.Operation;
import com.springboot.demo.core.interceptor.aop.Validate;
import com.springboot.demo.core.model.PageBean;
import com.springboot.demo.core.model.ResultData;
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

    //业务类错误提示
    private final static String ERROR_INFO = "20001:JSON_IS_NULL";
    private final static Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Resource
    private IApplicationService applicationService;

    /**
     * 获取本部门下所有记录
     *
     * @param application
     * @return
     */
    @Validate
    @Operation(value = "获取本部门下所有记录")
    @RequestMapping(value = "/getFormListByDept")
    public ResultData getFormListByDept(HttpServletRequest request, Application application) {
        logger.info(" getFormListByDept() -> Begin");
        //定义数据集
        List<Application> resultList = null;
        ResultData resultData = new ResultData();
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
            logger.error(" getFormListByDept()" + e);
        }
        //Page对象
        PageBean<Application> pageBean = new PageBean<>(resultList);
        resultData.setData(pageBean);
        logger.info(" getFormListByDept() -> End");
        return resultData;
    }

    /**
     * 获取本人所有申请记录
     *
     * @param application
     * @return
     */
    @Validate
    @Operation(value = "获取本人所有申请记录")
    @RequestMapping(value = "/getFormListByUser")
    public ResultData getFormListByUser(HttpServletRequest request, Application application) {
        logger.info(" getFormListByUser() -> Begin");
        //定义数据集
        ResultData resultData = new ResultData();
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
            logger.error(" getFormListByUser" + e);
        }
        //Page对象
        PageBean<Application> pageBean = new PageBean<>(resultList);
        resultData.setData(pageBean);
        logger.info("getFormListByUser() -> End");
        return resultData;
    }

    /**
     * 审核申请表
     * 检测接收到的Json是否为空
     *
     * @param application
     */
    @Validate
    @Operation(value = "审核申请表")
    @RequestMapping(value = "/verifyFormById")
    public ResultData verifyFormById(HttpServletRequest request, Application application) throws IllegalAccessException {
        logger.info("verifyFormById() -> Begin");
        ResultData resultData = new ResultData();
        //获取登陆用户信息
        User user = (User) request.getSession().getAttribute("user");
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(application, new String[]{"serialVersionUID"})) {
            application.setReviewer(user.getZnName());
            applicationService.verifyFormById(application);
        } else {
            logger.error(" verifyFormById() ->" + ERROR_INFO);
            resultData.setCode(20001);
            resultData.setMessage("接收到的数据为空");
        }
        logger.info(" verifyFormById() -> End");
        return resultData;
    }


    /**
     * 修改申请表
     * 检测接收到的Json是否为空
     *
     * @param application
     */
    @Validate
    @Operation(value = "修改申请表")
    @RequestMapping(value = "/modifyFormById")
    public ResultData modifyFormById(HttpServletRequest request, Application application) throws IllegalAccessException {
        logger.info(" modifyFormById() -> Begin");
        ResultData resultData = new ResultData();
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(application, new String[]{"serialVersionUID"})) {
            //执行
            applicationService.modifyFormById(application);
            resultData.setMessage("修改成功");
        } else {
            logger.error(" modifyFormById() ->" + ERROR_INFO);
            resultData.setCode(20001);
            resultData.setMessage("接收到的数据为空");
        }
        logger.info(" modifyFormById() -> End");
        return resultData;
    }

    /**
     * 新增申请表
     * 检测接收到的Json是否为空
     *
     * @param application
     */
    @Validate
    @Operation(value = "添加申请表")
    @RequestMapping(value = "/createAppForm")
    public ResultData createAppForm(HttpServletRequest request, Application application) throws IllegalAccessException {
        logger.info(" createAppForm() -> Begin");
        ResultData resultData = new ResultData();
        //从session里获取当前用户的名字
        User userInfo = (User) request.getSession().getAttribute("user");
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(application, new String[]{"serialVersionUID"})) {
            //注入对象
            application.setUserId(userInfo.getUserId());
            application.setGroupId(userInfo.getGroupId());
            application.setZnName(userInfo.getZnName());
            //执行
            applicationService.createAppForm(application);
            resultData.setMessage("修改成功");
        } else {
            logger.error("createPlace() ->" + ERROR_INFO);
            resultData.setCode(20001);
            resultData.setMessage("接收到的数据为空");
        }
        logger.info(" createAppForm() -> End");
        return resultData;
    }

    @Validate
    @Operation("关闭表单")
    @RequestMapping("/closeFormById")
    public ResultData closeFormById(Application application) throws IllegalAccessException {
        logger.info(" () -> Begin");
        ResultData resultData = new ResultData();
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(application, new String[]{"serialVersionUID"})) {
            //执行
            application.setIsOff("1");
            applicationService.closeFormById(application);
        } else {
            logger.error("createPlace() -> 20001:JSON_IS_NULL ");
            resultData.setCode(20001);
            resultData.setMessage("接收到的数据为空");
        }
        logger.info(" () -> End");
        return resultData;
    }






    /**
     * 删除表单:逻辑删除表单
     *
     * @param application
     * @return
     * @throws IllegalAccessException
     */
    @Validate
    @Operation("删除表单")
    @RequestMapping("/deleteFormById")
    public ResultData deleteFormById(Application application) throws IllegalAccessException {
        logger.info(" deleteFormById() -> Begin");
        ResultData resultData = new ResultData();
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(application, new String[]{"serialVersionUID"})) {
            //执行
            application.setIsEff("1");
            applicationService.deleteFormById(application);
            resultData.setMessage("修改成功");
        } else {
            logger.error(" deleteFormById() ->" + ERROR_INFO);
            resultData.setCode(20001);
            resultData.setMessage("接收到的数据为空");
        }
        logger.info(" deleteFormById() -> End");
        return resultData;
    }

    /**
     * 根据表单ID查询数据并存入Session
     *
     * @param request
     * @param application
     * @return
     * @throws IllegalAccessException
     */
    @Validate
    @Operation("查询表单详情")
    @RequestMapping("/getDetailToCache")
    public ResultData getDetailToCache(HttpServletRequest request, Application application) throws IllegalAccessException {
        logger.info(" getDetailToCache() -> Begin");
        ResultData resultData = new ResultData();
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(application, new String[]{"serialVersionUID"})) {
            //查询
            Application cache = applicationService.getFormInfoByFormId(application);
            //存入session
            request.getSession().setAttribute("cache", cache);
            resultData.setMessage("修改成功");
        } else {
            logger.error("getDetailToCache() ->" + ERROR_INFO);
            resultData.setCode(20001);
            resultData.setMessage("接收到的数据为空");
        }
        logger.info(" getDetailToCache() -> End");
        return resultData;
    }

    /**
     * 从Session中获取表单数据
     *
     * @param request
     * @return
     * @throws IllegalAccessException
     */
    @Validate
    @Operation("获取表单详情")
    @RequestMapping("/getDetailOnCache")
    public ResultData getDetailOnCache(HttpServletRequest request) throws IllegalAccessException {
        logger.info(" getDetailOnCache() -> Begin");
        ResultData resultData = new ResultData();
        //从session里获取当前用户的名字
        Application application = (Application) request.getSession().getAttribute("cache");

        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(application, new String[]{"serialVersionUID"})) {
            //执行
            resultData.setData(application);
        } else {
            logger.error("getDetailOnCache() ->" + ERROR_INFO);
            resultData.setCode(20001);
            resultData.setMessage("接收到的数据为空");
        }
        logger.info(" getDetailOnCache() -> End");
        return resultData;
    }

}
