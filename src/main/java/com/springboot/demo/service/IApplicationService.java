package com.springboot.demo.service;

import com.springboot.demo.entity.Application;

import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Service for Application
 */
public interface IApplicationService {

    /**
     * 获取本部门所有申请记录
     *
     * @param application
     * @return
     */
    List<Application> getFormListByDept(Integer pageNum, Integer pageSize, Application application);


    /**
     * 获取个人所有申请记录
     *
     * @param application
     * @return
     */
    List<Application> getFormListByUser(Integer pageNum, Integer pageSize, Application application);


    /**
     * 修改表单审核状态
     *
     * @param application
     */
    void verifyFormById(Application application);

    /**
     * 修改表单信息
     *
     * @param application
     */
    void modifyFormById(Application application);


    /**
     * 新增申请表
     *
     * @param application
     */
    void createAppForm(Application application);


    /**
     * 获取表单详情
     *
     * @param application
     * @return
     */
    Application getFormInfoByFormId(Application application);

    /**
     * 关闭表单
     *
     * @param application
     * @return
     */
    void closeFormById(Application application);

    /**
     * 删除表单
     *
     * @param application
     * @return
     */
    void deleteFormById(Application application);

}
