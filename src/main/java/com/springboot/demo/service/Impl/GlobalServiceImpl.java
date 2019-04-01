package com.springboot.demo.service.Impl;

import cn.hutool.extra.mail.MailUtil;
import com.springboot.demo.entity.Place;
import com.springboot.demo.entity.User;
import com.springboot.demo.mapper.PlaceMapper;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.service.IGlobalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
@Service
public class GlobalServiceImpl implements IGlobalService {



    @Resource
    private PlaceMapper placeMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 获取所有场地
     * @return
     */
    @Override
    public List<Place> getPlaceList() {
        List<Place> result = placeMapper.getPlaceList();
        return result;
    }

    /**
     * 获取角色列表
     * @return
     */
    @Override
    public List<User> getRoleList() {
        List<User> result = userMapper.getRoleList();
        return result;

    }

    /**
     * 获取部门列表
     * @return
     */
    @Override
    public List<User> getGroupList() {
        List<User> result = userMapper.getGroupList();
        return result;

    }

    @Override
    public boolean sendEmailOfTest(List<String> recipient, String mailTitle, String mailContent) {
        //发送
        MailUtil.send(recipient, mailTitle, mailContent, true);
        return true;
    }

}
