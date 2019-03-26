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
    List<Application> getAllFormByDept(Integer pageNum, Integer pageSize, Application application);


    /**
     * 获取个人所有申请记录
     * @param application
     * @return
     */
    List<Application> getAllFormByUser(Integer pageNum, Integer pageSize, Application application);


    /**
     * 修改表单审核状态
     * @param application
     */
    boolean modifyFormStatusByFormId(Application application);

    /**
     * 修改表单信息
     * @param application
     */
    boolean modifyFormInfoByFormId(Application application);


    /**
     * 新增申请表
     * @param application
     */
    boolean createAppForm(Application application);


    /**
     * 获取场地列表
     * @return
     */
    List<Application> getPlaceList();


}
