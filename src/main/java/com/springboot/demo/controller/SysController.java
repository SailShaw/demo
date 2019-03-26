package com.springboot.demo.controller;

import com.springboot.demo.core.common.PageBean;
import com.springboot.demo.core.interceptor.aop.Operation;
import com.springboot.demo.entity.SysLog;
import com.springboot.demo.service.ISysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
@RestController
@RequestMapping("/system")
public class SysController {

    private final static Logger logger = LoggerFactory.getLogger(SysController.class);

    @Resource
    private ISysLogService sysLogService;



    /**
     * 获取系统日志记录
     * @param request
     * @param sysLog
     * @return
     */
    @RequestMapping(value = "/getSysLogListByPage")
    public PageBean<SysLog> getSysLogListByPage(HttpServletRequest request, SysLog sysLog){

        //定义数据集
        List<SysLog> resultList = null;
        //分页
        try{
            // 获取分页参数
            String _pageNum = request.getParameter("pageNum");
            String _pageSize = request.getParameter("pageSize");
            //转型+判空
            Integer pageNum = StringUtils.isEmpty(_pageNum) ? 1 : Integer.parseInt(_pageNum);
            Integer pageSize = StringUtils.isEmpty(_pageSize) ? 7 : Integer.parseInt(_pageSize);

            resultList = sysLogService.getSysLogListByPage(pageNum,pageSize,sysLog);
        }catch(Exception e){
            //logger
            logger.error("分页参数异常");
        }
        //Page对象
        PageBean<SysLog> pageBean = new PageBean<>(resultList);
        return pageBean;
    }

    /**
     * 获取本部门下所有记录
     * @param request
     * @return
     */
    @Operation(value = "发送测试邮件")
    @RequestMapping(value = "/sendEmailOfTest")
    public String sendEmailOfTest(HttpServletRequest request){
        String recipients = request.getParameter("recipient");

        // 收件人(单个或多个)
        List<String> recipient = Arrays.asList(recipients.split(","));

        //邮件标题
        String mailTitle = request.getParameter("mailTitle");
        //邮件内容
        String mailContent = request.getParameter("mailContent");

        if (sysLogService.sendEmailOfTest(recipient,mailTitle,mailContent)) {
            return "SUCCESS";
        }else{
            logger.error("sendEmailOfTest() -> 发送失败");
            return "ERROR";
        }
    }
}
