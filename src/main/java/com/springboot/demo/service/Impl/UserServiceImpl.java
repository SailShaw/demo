package com.springboot.demo.service.Impl;

import cn.hutool.extra.mail.MailUtil;
import com.github.pagehelper.PageHelper;
import com.springboot.demo.entity.User;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.service.IUserService;
import com.springboot.demo.util.MD5;
import com.springboot.demo.util.UUIDTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Create By SINYA
 * Create Date 2019/2/27
 * Description:Service Implements For User
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;


    /**
     * 查询用户信息
     *
     * @param user
     * @return
     */
    @Override
    public List<User> getUserByAccount(User user) {
        List<User> result = userMapper.getUserByAccount(user);
        return result;
    }

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
    public String deleteUserByID(User user) {
        if (userMapper.deleteUserByID(user) && userMapper.deleteUserRoleByID(user) && userMapper.deleteUserGroupByID(user)) {
            return "success";
        }else{
            return "error";
        }
    }


    /**
     * 注册
     *
     * @param user
     */
    @Override
    public void register(User user) {
        //生成userID
        String id = UUIDTool.getUUID();
        user.setUserId(id);
        //密码加密

        try {
            user.setPasscode(MD5.EncoderByMd5(user.getPasscode()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //新增用户
        userMapper.createUser(user);
        //向用户角色表插入userId
        userMapper.addUserRole(id);
        //向用户部门表插入userId
        userMapper.addUserGroup(id);
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    @Override
    public List<User> getRoleList() {
        List<User> result = userMapper.getRoleList();
        return result;

    }

    /**
     * 获取部门列表
     *
     * @return
     */
    @Override
    public List<User> getGroupList() {
        List<User> result = userMapper.getGroupList();
        return result;

    }

    /**
     * 分配角色和部门
     *
     * @param user
     */
    @Override
    public String modifyURGInfoById(User user) {
        if (userMapper.modifyRoleByUser(user) && (userMapper.modifyGroupByUser(user))) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 发送验证码
     * @param user
     */
    @Override
    public void sendVerificationCode(Integer VerifyComde,User user) {
        String title = "验证您的电子邮件地址";
        String content =
                "<p>尊敬的<strong>XX</strong>，您好：</p>" +
                "<p>您正在对您的 CAMS 账号 ("+user.getAccount()+") 进行重置密码操作。</p>" +
                "<p>您的验证码是</p>" +
                "<p><h1>" + VerifyComde + "</h1></p>" +
                "<p>如果您未做过这些更改或认为有未经授权的人员访问了您的帐户，您应尽快登录 C A M S 系统进行更改密码,以防账户丢失。</p>" +
                "<p>此致</p>" +
                "<p>CAMS 支持</p>";
        MailUtil.send(user.getEmail(), title, content, true);
    }

    /**
     * 重置密码
     * @param user
     */
    @Override
    public void resetPassword(User user) {
        userMapper.resetPassword(user);
    }


}
