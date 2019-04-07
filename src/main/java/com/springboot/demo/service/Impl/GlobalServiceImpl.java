package com.springboot.demo.service.Impl;

import cn.hutool.extra.mail.MailUtil;
import com.springboot.demo.entity.Place;
import com.springboot.demo.entity.User;
import com.springboot.demo.mapper.GlobalMapper;
import com.springboot.demo.mapper.PlaceMapper;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.service.IGlobalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Implements for Global
 */

@Service
public class GlobalServiceImpl implements IGlobalService {



    @Resource
    private PlaceMapper placeMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private GlobalMapper globalMapper;

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

    @Override
    public Map<String, Object> dataStatistics() {

        Map<String, Object> map = new HashMap<>();
        //活动申请总数
        map.put("total",globalMapper.applicationTotal());
        //审核类型统计
        map.put("typeCount",globalMapper.typeCount());
        //用户数统计
        map.put("userCount",globalMapper.userCount());
        //资源数统计
        map.put("placeCount",globalMapper.placeCount());
        //上一周每日申请数统计
        map.put("lastWeekNumCount",globalMapper.lastWeekNumCount());
        //两周内申请数据
        map.put("twoWeekDataCount",globalMapper.twoWeekDataCount());
        return map;
    }

}
