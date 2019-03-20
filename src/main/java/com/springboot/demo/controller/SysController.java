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


    @Operation(value = "查询系统日志")
    /**
     * 获取本部门下所有记录
     * @param application
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

}
