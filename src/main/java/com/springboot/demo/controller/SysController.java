package com.springboot.demo.controller;

import com.springboot.demo.core.interceptor.aop.Validate;
import com.springboot.demo.core.model.PageBean;
import com.springboot.demo.core.model.ResultData;
import com.springboot.demo.entity.SysLog;
import com.springboot.demo.service.ISysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Controller for System
 */

@RestController
@RequestMapping("/system")
public class SysController {

    private final static Logger logger = LoggerFactory.getLogger(SysController.class);

    @Resource
    private ISysLogService sysLogService;

    /**
     * 获取系统日志记录
     *
     * @param request
     * @param sysLog
     * @return
     */
    @Validate
    @RequestMapping(value = "/getSysLogListByPage")
    public ResultData getSysLogListByPage(HttpServletRequest request, SysLog sysLog) {
        logger.info("getSysLogListByPage() -> Begin");
        //定义数据集
        ResultData resultData = new ResultData();
        List<SysLog> resultList = null;
        try {
            // 获取分页参数
            Integer pageNum = StringUtils.isEmpty(request.getParameter("pageNum")) ? 1 : Integer.parseInt(request.getParameter("pageNum"));
            Integer pageSize = 9;
            resultList = sysLogService.getSysLogListByPage(pageNum, pageSize, sysLog);
        } catch (Exception e) {
            logger.error("getSysLogListByPage" + e);
        }
        //Page对象
        PageBean<SysLog> pageBean = new PageBean<>(resultList);
        resultData.setData(pageBean);
        logger.info("getSysLogListByPage() -> End");
        return resultData;
    }

}
