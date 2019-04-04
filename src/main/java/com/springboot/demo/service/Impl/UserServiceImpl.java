package com.springboot.demo.service.Impl;

import cn.hutool.extra.mail.MailUtil;
import com.github.pagehelper.PageHelper;
import com.springboot.demo.entity.User;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.service.IUserService;
import com.springboot.demo.util.MD5;
import com.springboot.demo.util.SnowFlake;
import com.springboot.demo.util.TimeHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Implements for User
 */

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    private SnowFlake snowFlake = new SnowFlake(3, 9);


    /**
     * 根据ID修改用户信息
     *
     * @param user
     */
    @Override
    public String modifyUserInfoById(User user) {

        String result = null;
        //执行判断
        if (userMapper.modifyUserInfoById(user)) {
            result = "SUCCESS";
        } else {
            result = "ERROR";
        }

        return result;
    }

    /**
     * 用户管理查询
     *
     * @param user
     * @return
     */
    @Override
    public List<User> getURGInfoListByPage(Integer pageNum, Integer pageSize, User user) {
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<User> result = userMapper.getURGInfoListByPage(user);
        return result;
    }

    /**
     * 删除用户
     *
     * @param user
     */
    @Override
    public String deleteUserByID(User user) {

        String result = null;
        //判断执行
        if (userMapper.deleteUserByID(user)) {
            result = "SUCCESS";
        } else {
            result = "ERROR";
        }
        return result;
    }


    /**
     * 注册
     *
     * @param user
     */
    @Override
    public String register(User user) {
        String result = null;

        if (user != null) {
            //生成userID
            String id = String.valueOf(snowFlake.nextId());
            user.setUserId(id);
            //密码加密
            try {
                user.setPassword(MD5.EncoderByMd5(user.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //插入记录(user、user_role、user_group)
            userMapper.createUser(user);
            userMapper.addUserRole(id);
            userMapper.addUserGroup(id);
            result = "SUCCESS";
        } else {
            result = "JSON_IS_NULL";
        }
        return result;
    }

    /**
     * 分配角色和部门
     *
     * @param user
     */
    @Override
    public String modifyURGInfoById(User user) {

        String result = null;

        if (userMapper.modifyRoleByUser(user) && (userMapper.modifyGroupByUser(user))) {
            result = "SUCCESS";
        } else {
            result = "ERROR";
        }
        return result;
    }

    /**
     * 发送验证码
     *
     * @param user
     */
    @Override
    public void sendVerificationCode(Integer VerifyComde, User user) {
        String title = "验证您的电子邮件地址";
        String content =
                "<p>尊敬的<strong>" + user.getZnName() + "</strong>，您好：</p>" +
                        "<p>您正在对您的 CAMS 账号 (" + user.getAccount() + ") 进行重置密码操作。</p>" +
                        "<p>您的验证码是</p>" +
                        "<p><h1>" + VerifyComde + "</h1></p>" +
                        "<p>如果您未做过这些更改或认为有未经授权的人员访问了您的帐户，您应尽快登录 C A M S 系统进行更改密码,以防账户丢失。</p>" +
                        "<p>此致</p>" +
                        "<p>CAMS 支持</p>";
        MailUtil.send(user.getEmail(), title, content, true);
    }

    /**
     * 重置密码
     *
     * @param user
     */
    @Override
    public void resetPassword(User user) {

        //密码加密
        try {
            user.setPassword(MD5.EncoderByMd5(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        userMapper.resetPassword(user);
    }

    /**
     * 记录登录日期
     *
     * @param user
     */
    @Override
    public void recordLoginTime(User user) {
        String lastLoginTime = TimeHelper.getNowTime();
        user.setLastLogin(lastLoginTime);
        userMapper.recordLoginTime(user);
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @Override
    public String updatePassword(User user) {
        String result = null;

        if (userMapper.updatePassword(user)) {
            result = "SUCCESS";
        } else {
            result = "ERROR";
        }
        return result;
    }
}
