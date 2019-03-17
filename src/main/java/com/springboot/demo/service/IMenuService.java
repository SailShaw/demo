package com.springboot.demo.service;

import com.springboot.demo.entity.Menu;

import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
public interface IMenuService {

    /**
     * 根据角色ID获取菜单列表
     * @param menu
     * @return
     */
    List<Menu> getMenuListByRole(Menu menu);
}
