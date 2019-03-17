package com.springboot.demo.service.Impl;

import com.springboot.demo.entity.Menu;
import com.springboot.demo.mapper.MenuMapper;
import com.springboot.demo.service.IMenuService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
public class MenuServiceImpl implements IMenuService {


    @Resource
    private MenuMapper menuMapper;


    @Override
    public List<Menu> getMenuListByRole(Menu menu) {
        List<Menu> result = menuMapper.getMenuListByRole(menu);
        return result;
    }
}
