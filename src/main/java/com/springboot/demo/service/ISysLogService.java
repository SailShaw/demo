package com.springboot.demo.service;

import com.springboot.demo.entity.SysLog;

import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
public interface ISysLogService {

    //保存日志
    void saveLog(SysLog sysLog);
    //查询日志列表
    List<SysLog> getSysLogListByPage(Integer pageNum,Integer pageSize,SysLog sysLog);

    boolean sendEmailOfTest(List<String> recipient,String mailTitle,String mailContent);
}
