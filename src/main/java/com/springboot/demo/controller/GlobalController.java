package com.springboot.demo.controller;

import com.springboot.demo.core.interceptor.aop.Operation;
import com.springboot.demo.entity.Menu;
import com.springboot.demo.entity.Place;
import com.springboot.demo.entity.User;
import com.springboot.demo.service.IApplicationService;
import com.springboot.demo.service.IGlobalService;
import com.springboot.demo.service.IMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
@RestController
@RequestMapping("/global")
public class GlobalController {

    private final static Logger logger = LoggerFactory.getLogger(GlobalController.class);

    @Resource
    private IMenuService menuService;

    @Resource
    private IGlobalService globalService;

    @Resource
    private IApplicationService applicationService;

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

    /**
     * 获取角色列表
     * @return
     */
    @RequestMapping(value = "/getRoleList")
    public List<User> getRoleList(){
        List<User> result = globalService.getRoleList();
        return result;
    }

    /**
     * 获取部门列表
     * @return
     */
    @RequestMapping(value = "/getGroupList")
    public List<User> getGroupList(){
        List<User> result = globalService.getGroupList();
        return result;
    }

    /**
     * 获取场地列表
     * @return result
     */
    @RequestMapping("/getPlaceList")
    public List<Place> getPlaceList(){
        List<Place> result = globalService.getPlaceList();
        return result;
    }

    /**
     * 获取登录用户信息
     * @return
     */
    @RequestMapping("/getLoginUserInfo")
    public User getInfo(HttpServletRequest request){
        //获取session
        User user = (User) request.getSession().getAttribute("user");
        return user;
    }

    /**
     * 邮箱发信功能测试
     * @param request
     * @return
     */
    @Operation(value = "发送测试邮件")
    @RequestMapping(value = "/sendEmailOfTest")
    public String sendEmailOfTest(HttpServletRequest request){

        String result = null;
        String recipients = request.getParameter("recipient");

        // 收件人(单个或多个)
        List<String> recipient = Arrays.asList(recipients.split(","));

        //邮件标题
        String mailTitle = request.getParameter("mailTitle");
        //邮件内容
        String mailContent = request.getParameter("mailContent");

        if (globalService.sendEmailOfTest(recipient,mailTitle,mailContent)) {
            result =  "SUCCESS";
        }else{
            logger.error("sendEmailOfTest() -> 发送失败");
            result =  "ERROR";
        }
        return result;
    }

}
