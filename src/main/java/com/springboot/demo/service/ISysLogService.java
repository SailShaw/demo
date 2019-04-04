package com.springboot.demo.service;

import com.springboot.demo.entity.SysLog;

import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Service for Syslog
 */
public interface ISysLogService {

    //保存日志
    void saveLog(SysLog sysLog);
    //查询日志列表
    List<SysLog> getSysLogListByPage(Integer pageNum,Integer pageSize,SysLog sysLog);


}
