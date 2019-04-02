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
    Application getFormInfoByFormId(Application application);

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
     * 关闭表单
     * 不可编辑,只可查看,由管理员操作该状态
     * @param application
     * @return
     */
    boolean closeFormById(Application application);

    /**
     * 删除表单
     * 逻辑删除该表单,但数据仍在表中,
     * @param application
     * @return
     */
    boolean deleteFormById(Application application);

}
