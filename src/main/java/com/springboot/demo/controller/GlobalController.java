package com.springboot.demo.controller;

import com.springboot.demo.core.interceptor.aop.Operation;
import com.springboot.demo.entity.Menu;
import com.springboot.demo.entity.Place;
import com.springboot.demo.entity.User;
import com.springboot.demo.service.IApplicationService;
import com.springboot.demo.service.IGlobalService;
import com.springboot.demo.service.IMenuService;
import com.springboot.demo.util.ObjectHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Controller for Global
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
    public User getInfo(HttpServletRequest request) throws IllegalAccessException {
        //获取session
        User user = (User) request.getSession().getAttribute("user");
        //判空
        if(ObjectHandle.reflectFieldIsNotALLNull(user,new String[]{"serialVersionUID"})){
            return user;
        }else {
            return null;
        }
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

    @RequestMapping("/dataStatistics")
    public Map<String,Object> dataStatistics(){

        //1.申请表总数统计,待审核统计,已通过统计,未通过统计

        //2.已注册用户数统计

        //3.资源数统计

        //4.两周内提交的申请表数据查询

        //5.图表数据:横坐标为一周7天,纵坐标为当天提交申请表总数

        return globalService.dataStatistics();


    }

}
