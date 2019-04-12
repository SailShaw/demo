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
    public void modifyUserInfoById(User user) {
        userMapper.modifyUserInfoById(user);
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
    public void deleteUserByID(User user) {
        userMapper.deleteUserByID(user);
    }


    /**
     * 注册
     *
     * @param user
     */
    @Override
    public void register(User user) {
        //生成userID
        user.setUserId(String.valueOf(snowFlake.nextId()));
        //密码加密
        try {
            user.setPassword(MD5.EncoderByMd5(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        user.setCreateTime(TimeHelper.getNowTime());
        //插入记录(user、user_role、user_group)
        userMapper.createUser(user);
        userMapper.addUserRole(user);
        userMapper.addUserGroup(user);
    }

    /**
     * 分配角色和部门
     *
     * @param user
     */
    @Override
    public void modifyURGInfoById(User user) {
        userMapper.modifyRoleAndGroupByUser(user);
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
        String newPassword = user.getPassword();
        //密码加密
        try {
            user.setPassword(MD5.EncoderByMd5(newPassword));
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
    public void updatePassword(User user) {
        userMapper.updatePassword(user);
    }
}
