package com.springboot.demo.service.Impl;

import cn.hutool.extra.mail.MailUtil;
import com.github.pagehelper.PageHelper;
import com.springboot.demo.entity.Application;
import com.springboot.demo.entity.User;
import com.springboot.demo.mapper.ApplicationMapper;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.service.IApplicationService;
import com.springboot.demo.util.SnowFlake;
import com.springboot.demo.util.TimeHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Implements for Application
 */

@Service
public class ApplicationServiceImpl implements IApplicationService {

    @Resource
    private ApplicationMapper applicationMapper;

    @Resource
    private UserMapper userMapper;

    private SnowFlake snowFlake = new SnowFlake(4, 9);

    /**
     * 获取部门下所有记录
     *
     * @param pageSize
     * @param pageNum
     * @param application
     * @return
     */
    @Override
    public List<Application> getFormListByDept(Integer pageNum, Integer pageSize, Application application) {
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<Application> result = applicationMapper.getFormListByDept(application);
        return result;
    }

    /**
     * 获取个人所有申请记录
     *
     * @param application
     * @return
     */
    @Override
    public List<Application> getFormListByUser(Integer pageNum, Integer pageSize, Application application) {
        //分页+
        PageHelper.startPage(pageNum, pageSize);
        List<Application> result = applicationMapper.getFormListByUser(application);
        return result;
    }

    /**
     * 审核申请表
     *
     * @param application
     */
    @Override
    public void verifyFormById(Application application) {
        //获取该表单所属用户的邮箱
        User user = new User();
        boolean flag;
        user.setUserId(application.getUserId());
        User userinfo = userMapper.findUserByUser(user);
        String recipient = userinfo.getEmail();
        //设置邮件标题
        String mailTitle = "审核结果通知";
        //获取邮件内容
        String mailText = application.getMailMsg();
        //设置审核时间
        application.setVerifyTime(TimeHelper.getNowTime());
        //修改审核状态
        applicationMapper.verifyFormById(application);
        //发送邮件(邮箱地址,标题,正文,是否是html)
        MailUtil.send(recipient, mailTitle, mailText, true);
    }

    /**
     * 修改申请表
     *
     * @param application
     */
    @Override
    public void modifyFormById(Application application) {
        application.setUpdateTime(TimeHelper.getNowTime());
        applicationMapper.modifyFormById(application);
    }

    /**
     * 新增表单
     *
     * @param application
     */
    @Override
    public void createAppForm(Application application) {
        //表单ID自动生成
        application.setFormId(String.valueOf(snowFlake.nextId()));
        //执行新增操作
        applicationMapper.createAppForm(application);

    }

    /**
     * 获取表单详情
     *
     * @return
     */
    @Override
    public Application getFormInfoByFormId(Application application) {
        Application details = applicationMapper.getFormInfoByFormId(application);
        return details;
    }

    /**
     * 关闭表单
     *
     * @param application
     * @return
     */
    @Override
    public void closeFormById(Application application) {
        applicationMapper.closeFormById(application);
    }

    /**
     * 删除表单
     *
     * @param application
     * @return
     */
    @Override
    public void deleteFormById(Application application) {
        applicationMapper.deleteFormById(application);
    }
}
