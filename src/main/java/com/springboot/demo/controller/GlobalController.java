package com.springboot.demo.controller;

import com.springboot.demo.entity.Menu;
import com.springboot.demo.entity.User;
import com.springboot.demo.service.IMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
@RestController
public class GlobalController {

    private final static Logger logger = LoggerFactory.getLogger(GlobalController.class);

    @Resource
    private IMenuService menuService;

    /**
     * 根据角色id获取菜单栏
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping("/getMenu")
    public List<Menu> getMenu(HttpServletRequest request,Menu menu){

        List<Menu> result = new ArrayList<>();
        //获取登录用户的信息
        User userInfo = (User) request.getSession().getAttribute("user");
        //设置
        menu.setRoleId(userInfo.getRoleId());
        //执行
        result  = menuService.getMenuListByRole(menu);

        return result;
    }
}
