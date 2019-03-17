package com.springboot.demo.mapper;

import com.springboot.demo.entity.Application;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Create By SINYA
 * Create Date 2019/2/27
 * Description: Mapper For User
 */

@Mapper
public interface ApplicationMapper {


    /**
     * 获取本部门所有申请记录
     * @param application
     * @return
     */
    List<Application> getAllFormByDept(Application application);

    /**
     * 获取个人所有申请记录
     * @param application
     * @return
     */
    List<Application> getAllFormByUser(Application application);

    /**
     * 根据ID获取表信息
     * @param application
     * @return
     */
    List<Application> getFormInfoByFormId(Application application);

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

    /**
     * 获取场地列表
     * @return
     */
    List<Application> getPlaceList();

}
