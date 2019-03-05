package com.springboot.demo.controller;

import com.springboot.demo.entity.Application;
import com.springboot.demo.service.IApplicationService;
import com.springboot.demo.util.UUIDTool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By SINYA
 * Create Date 2019/2/27
 * Description: Controller For User
 */

@RestController
@RequestMapping("/app")
public class ApplicationController {

    @Resource
    private IApplicationService applicationService;

    /**
     * 获取本部门下所有记录
     * @param application
     * @return
     */
    @RequestMapping(value = "/getAllFormByDept",method = RequestMethod.GET)
    public List<Application> getAllFormByDept(Application application){
        List<Application> resultList = applicationService.getAllFormByDept(application);
        return resultList;
    }

    /**
     * 获取本人所有申请记录
     * @param application
     * @return
     */
    @RequestMapping(value = "/getAllFormByUser",method = RequestMethod.GET)
    public List<Application> getAllFormByUser(Application application){
        List<Application> resultList = applicationService.getAllFormByUser(application);
        return resultList;
    }

    @RequestMapping(value = "/getFormInfoByFormId",method = RequestMethod.GET)
    public List<Application> getFormInfoByFormId(Application application){
        List<Application> resultList = applicationService.getFormInfoByFormId(application);
        return resultList;
    }

    /**
     * 审核申请表
     * @param application
     */
    @RequestMapping(value = "/modifyFormStatusByFormId",method = RequestMethod.POST)
    public void modifyFormStatusByFormId(Application application){
        applicationService.modifyFormStatusByFormId(application);
    }

    /**
     * 修改申请表
     * @param application
     */
    @RequestMapping(value = "/modifyFormInfoByFormId",method = RequestMethod.POST)
    public void modifyFormInfoByFormId(Application application){
        applicationService.modifyFormInfoByFormId(application);
    }

    /**
     * 新增申请表
     * @param application
     */
    @RequestMapping(value = "/createAppForm",method = RequestMethod.POST)
    public void createAppForm(Application application){
        applicationService.createAppForm(application);
    }
}
