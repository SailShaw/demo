package com.springboot.demo.service;

import com.springboot.demo.entity.Application;

import java.util.List;

/**
 * Create By SINYA
 * Create Date 2019/3/1
 * Description:
 */
public interface IApplicationService {

    /**
     * 获取本部门所有申请记录
     * @param application
     * @return
     */
    List<Application> getAllFormByDept(Application application);


    /**
     * 根据ID获取表信息
     */

    List<Application> getFormInfoByFormId(Application application);


    /**
     * 获取个人所有申请记录
     * @param application
     * @return
     */
    List<Application> getAllFormByUser(Application application);


    /**
     * 修改表单审核状态
     * @param application
     */
    void modifyFormStatusByFormId(Application application);

    /**
     * 修改表单信息
     * @param application
     */
    void modifyFormInfoByFormId(Application application);


    /**
     * 新增申请表
     * @param application
     */
    void createAppForm(Application application);




}
