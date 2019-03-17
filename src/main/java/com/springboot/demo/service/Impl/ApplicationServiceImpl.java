package com.springboot.demo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.springboot.demo.entity.Application;
import com.springboot.demo.mapper.ApplicationMapper;
import com.springboot.demo.service.IApplicationService;
import com.springboot.demo.util.UUIDTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By SINYA
 * Create Date 2019/3/1
 * Description:
 */
@Service
public class ApplicationServiceImpl implements IApplicationService {

    @Resource
    private ApplicationMapper applicationMapper;

    /**
     * 获取部门下所有记录
     * @param pageSize
     * @param pageNum
     * @param application
     * @return
     */
    @Override
    public List<Application> getAllFormByDept(Integer pageNum, Integer pageSize, Application application) {
        //分页
        PageHelper.startPage(pageNum,pageSize);
        //
        List<Application> result = applicationMapper.getAllFormByDept(application);
        return result;
    }

    /**
     * 获取个人所有申请记录
     * @param application
     * @return
     */
    @Override
    public List<Application> getAllFormByUser(Integer pageNum, Integer pageSize, Application application) {
        //分页(第几页,每页几个)
        PageHelper.startPage(pageNum,pageSize);
        //
        List<Application> result = applicationMapper.getAllFormByUser(application);
        return result;
    }

    /**
     * 修改表单审核状态
     * @param application
     */
    @Override
    public void modifyFormStatusByFormId(Application application) {

        //获取该表单所属用户的邮箱

        //获取邮件内容
//        String mailText =

        //修改审核状态
        applicationMapper.modifyFormStatusByFormId(application);

        //发送邮件
//        MailUtil.send();

    }

    /**
     * 修改表单信息
     * @param application
     */
    @Override
    public void modifyFormInfoByFormId(Application application) {

        applicationMapper.modifyFormInfoByFormId(application);

    }

    /**
     * 新增表单
     * @param application
     */
    @Override
    public void createAppForm(Application application) {
        //表单ID自动生成
        application.setFormId(UUIDTool.getUUID());
        //用户、部门ID直接获取当前用户的数据
//        session.getuserId();
//        session.getgroupId();
        //内容校验框架

        //执行新增操作
        applicationMapper.createAppForm(application);

    }

    /**
     * 获取所有场地
     * @return
     */
    @Override
    public List<Application> getPlaceList() {
        List<Application> result = applicationMapper.getPlaceList();
        return result;
    }
}
