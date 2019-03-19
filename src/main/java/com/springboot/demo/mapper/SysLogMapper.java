package com.springboot.demo.mapper;

import com.springboot.demo.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
@Mapper
public interface SysLogMapper {

    //保存日志
    void saveLog(SysLog sysLog);
    //查询日志列表
    List<SysLog> getSysLogListByPage(SysLog sysLog);
}
