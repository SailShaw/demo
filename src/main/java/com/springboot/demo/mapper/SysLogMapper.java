package com.springboot.demo.mapper;

import com.springboot.demo.entity.SysLog;

import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
public interface SysLogMapper {

    //保存日志
    SysLog saveLog(SysLog sysLog);
    //查询日志列表
    List<SysLog> getSysLogListByPage(SysLog sysLog);
}
