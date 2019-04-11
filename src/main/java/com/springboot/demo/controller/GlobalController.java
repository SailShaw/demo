package com.springboot.demo.controller;

import com.springboot.demo.core.interceptor.aop.Operation;
import com.springboot.demo.core.interceptor.aop.Validate;
import com.springboot.demo.core.model.ResultData;
import com.springboot.demo.entity.Place;
import com.springboot.demo.entity.User;
import com.springboot.demo.service.IGlobalService;
import com.springboot.demo.util.ObjectHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    private IGlobalService globalService;


    /**
     * 首页数据展示
     *
     * @return
     */
//    @Validate
    @RequestMapping("/dataStatistics")
    public ResultData dataStatistics() {
        /**
         * 1.申请表总数统计,待审核统计,已通过统计,未通过统计
         * 2.已注册用户数统计
         * 3.资源数统计
         * 4.两周内提交的申请表数据查询
         * 5.图表数据:横坐标为一周7天,纵坐标为当天提交申请表总数
         */
        Map<String, Object> map = globalService.dataStatistics();
        ResultData resultData = new ResultData(map);
        return resultData;
    }

    /**
     * 角色下拉列表
     *
     * @return
     */
    @Validate
    @RequestMapping(value = "/getRoleList")
    public ResultData getRoleList() {
        List<User> result = globalService.getRoleList();
        ResultData resultData = new ResultData(result);
        return resultData;
    }

    /**
     * 部门下拉列表
     *
     * @return
     */
    @Validate
    @RequestMapping(value = "/getGroupList")
    public ResultData getGroupList() {
        List<User> result = globalService.getGroupList();
        ResultData resultData = new ResultData(result);
        return resultData;
    }

    /**
     * 场地下拉列表
     *
     * @return result
     */
    @Validate
    @RequestMapping("/getPlaceList")
    public ResultData getPlaceList() {
        List<Place> result = globalService.getPlaceList();
        ResultData resultData = new ResultData(result);
        return resultData;
    }

    /**
     * 获取当前登录用户信息(用于nav信息展示)
     *
     * @return
     */
    @Validate
    @RequestMapping("/getLoginUserInfo")
    public ResultData getInfo(HttpServletRequest request) throws IllegalAccessException {
        ResultData resultData = new ResultData();
        //获取session
        User user = (User) request.getSession().getAttribute("user");
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(user, new String[]{"serialVersionUID"})) {
            resultData.setData(user);
        } else {
            resultData.setData(null);
            resultData.setMassage("未登录");
        }
        return resultData;
    }

    /**
     * 邮箱测试功能
     *
     * @param request
     * @return
     */
    @Validate
    @Operation(value = "发送测试邮件")
    @RequestMapping(value = "/sendEmailOfTest")
    public ResultData sendEmailOfTest(HttpServletRequest request) {
        ResultData resultData = new ResultData();
        // 收件人(单个或多个)
        String recipients = request.getParameter("recipient");
        List<String> recipient = Arrays.asList(recipients.split(","));
        //邮件标题
        String mailTitle = request.getParameter("mailTitle");
        //邮件内容
        String mailContent = request.getParameter("mailContent");
        //发送
        if (globalService.sendEmailOfTest(recipient, mailTitle, mailContent)) {
            resultData.setMassage("发送成功，若长时间未收到请将“test_cams@126.com”加入收信白名单");
        } else {
            logger.error("sendEmailOfTest() -> SendEmail Fail");
            resultData.setMassage("发送失败，未知错误");
        }
        return resultData;
    }
}
