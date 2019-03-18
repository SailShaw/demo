package com.springboot.demo.controller;

import com.springboot.demo.core.common.PageBean;
import com.springboot.demo.entity.Application;
import com.springboot.demo.service.IApplicationService;
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
@RequestMapping("/application")
public class ApplicationController {

    @Resource
    private IApplicationService applicationService;

    /**
     * 获取本部门下所有记录
     * @param application
     * @return
     */
    @RequestMapping(value = "/getAllFormByDept")
    public PageBean<Application> getAllFormByDept(Integer pageSize, Integer pageNum, Application application){
        //logger

        //定义数据集
        List<Application> resultList = null;

        //分页
        try{

            // 获取分页参数

            resultList = applicationService.getAllFormByDept(pageNum,pageSize,application);
        }catch(Exception e){
            //logger
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
    @RequestMapping(value = "/getAllFormByUser")
    public PageBean<Application> getAllFormByUser(HttpServletRequest request, Application application){
        //logger

        //定义数据集
        List<Application> resultList = null;

        //分页
        try{

            // 获取分页参数
            String _pageNum = request.getParameter("pageNum");
            String _pageSize = request.getParameter("pageSize");
            //转型+判空
            Integer pageNum = StringUtils.isEmpty(_pageNum) ? 1 : Integer.parseInt(_pageNum);
            Integer pageSize = StringUtils.isEmpty(_pageSize) ? 7 : Integer.parseInt(_pageSize);

            resultList = applicationService.getAllFormByUser(pageNum,pageSize,application);
        }catch(Exception e){
            //logger
        }

        //Page对象

        PageBean<Application> pageBean = new PageBean<>(resultList);
        return pageBean;
    }

    /**
     * 审核申请表
     * @param application
     */
    @RequestMapping(value = "/modifyFormStatusByFormId")
    public void modifyFormStatusByFormId(Application application){
        applicationService.modifyFormStatusByFormId(application);
    }

    /**
     * 修改申请表
     * @param application
     */
    @RequestMapping(value = "/modifyFormInfoByFormId")
    public void modifyFormInfoByFormId(Application application){
        applicationService.modifyFormInfoByFormId(application);
    }

    /**
     * 新增申请表
     * @param application
     */
    @RequestMapping(value = "/createAppForm")
    public void createAppForm(Application application){
        applicationService.createAppForm(application);
    }


    /**
     * 获取场地列表
     * @return
     */
    @RequestMapping(value = "/getPlaceList")
    public List<Application> getPlaceList(){
        List<Application> result = applicationService.getPlaceList();
        return result;
    }
}
