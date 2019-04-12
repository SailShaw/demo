package com.springboot.demo.mapper;

import com.springboot.demo.entity.Application;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Mapper for Application
 */

@Mapper
public interface ApplicationMapper {


    /**
     * 获取本部门所有申请记录
     *
     * @param application
     * @return
     */
    List<Application> getFormListByDept(Application application);

    /**
     * 获取个人所有申请记录
     *
     * @param application
     * @return
     */
    List<Application> getFormListByUser(Application application);

    /**
     * 根据ID获取表信息
     *
     * @param application
     * @return
     */
    Application getFormInfoByFormId(Application application);

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
     * 关闭表单
     * 不可编辑,只可查看,由管理员操作该状态
     *
     * @param application
     * @return
     */
    void closeFormById(Application application);

    /**
     * 删除表单
     * 逻辑删除该表单,但数据仍在表中,
     *
     * @param application
     * @return
     */
    void deleteFormById(Application application);

}
