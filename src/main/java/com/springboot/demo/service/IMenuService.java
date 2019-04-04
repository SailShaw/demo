package com.springboot.demo.service;

import com.springboot.demo.entity.Menu;

import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Service for Menu
 */
public interface IMenuService {

    /**
     * 根据角色ID获取菜单列表
     * @param menu
     * @return
     */
    List<Menu> getMenuListByRole(Menu menu);
}
