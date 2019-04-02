package com.springboot.demo.service.Impl;

import cn.hutool.extra.mail.MailUtil;
import com.github.pagehelper.PageHelper;
import com.springboot.demo.entity.Application;
import com.springboot.demo.entity.User;
import com.springboot.demo.mapper.ApplicationMapper;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.service.IApplicationService;
import com.springboot.demo.util.SnowFlake;
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
    public List<Application> getAllFormByDept(Integer pageNum, Integer pageSize, Application application) {
        //分页
        PageHelper.startPage(pageNum, pageSize);
        //
        List<Application> result = applicationMapper.getAllFormByDept(application);
        return result;
    }

    /**
     * 获取个人所有申请记录
     *
     * @param application
     * @return
     */
    @Override
    public List<Application> getAllFormByUser(Integer pageNum, Integer pageSize, Application application) {
        //分页(第几页,每页几个)
        PageHelper.startPage(pageNum, pageSize);
        //
        List<Application> result = applicationMapper.getAllFormByUser(application);
        return result;
    }

    /**
     * 审核申请表
     *
     * @param application
     */
    @Override
    public boolean modifyFormStatusByFormId(Application application) {

        //获取该表单所属用户的邮箱
        User user = new User();

        user.setUserId(application.getUserId());

        User userinfo = userMapper.findUserByUser(user);

        String recipient = userinfo.getEmail();
        //设置邮件标题
        String mailTitle = "审核结果通知";
        //获取邮件内容
        String mailText = application.getMailMsg();

        //修改审核状态

        //发送邮件(邮箱地址,标题,正文,是否是html)
        MailUtil.send(recipient, mailTitle, mailText, false);

        if (applicationMapper.modifyFormStatusByFormId(application)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改申请表
     *
     * @param application
     */
    @Override
    public boolean modifyFormInfoByFormId(Application application) {

        if (applicationMapper.modifyFormInfoByFormId(application)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 新增表单
     *
     * @param application
     */
    @Override
    public boolean createAppForm(Application application) {
        //表单ID自动生成
        application.setFormId(String.valueOf(snowFlake.nextId()));

        //执行新增操作
        if (applicationMapper.createAppForm(application)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 获取表单详情
     * @return
     */
    @Override
    public Application getFormInfoByFormId(Application application) {
        Application details = applicationMapper.getFormInfoByFormId(application);
        return details;
    }

    /**
     * 关闭表单
     * @param application
     * @return
     */
    @Override
    public String closeFormById(Application application) {

        String result = null;

        if (applicationMapper.closeFormById(application)) {
            result = "SUCCESS";
        } else {
            result = "ERROR";
        }
        return result;
    }

    /**
     * 删除表单
     * @param application
     * @return
     */
    @Override
    public String deleteFormById(Application application) {
        String result = null;

        if (applicationMapper.deleteFormById(application)) {
            result = "SUCCESS";
        } else {
            result = "ERROR";
        }
        return result;
    }
}
