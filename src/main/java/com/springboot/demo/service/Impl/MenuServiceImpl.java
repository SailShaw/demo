package com.springboot.demo.service.Impl;

import com.springboot.demo.entity.Menu;
import com.springboot.demo.mapper.MenuMapper;
import com.springboot.demo.service.IMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Implements for Menu
 */

@Service
public class MenuServiceImpl implements IMenuService {

    @Resource
    private MenuMapper menuMapper;


    /**
     * 根据ID获取菜单列表
     * @param menu
     * @return
     */
    @Override
    public List<Menu> getMenuListByRole(Menu menu) {
        List<Menu> result = menuMapper.getMenuListByRole(menu);
        return result;
    }
}
