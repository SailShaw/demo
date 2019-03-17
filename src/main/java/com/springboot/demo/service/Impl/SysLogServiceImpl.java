package com.springboot.demo.service.Impl;

import com.springboot.demo.entity.SysLog;
import com.springboot.demo.mapper.SysLogMapper;
import com.springboot.demo.service.ISysLogService;

import javax.annotation.Resource;

/**
 * Create By SINYA
 * Description:
 */
public class SysLogServiceImpl implements ISysLogService {

    @Resource
    private SysLogMapper sysLogMapper;


    @Override
    public void saveSysLog(SysLog sysLog) {
        sysLogMapper.saveSysLog(sysLog);
    }
}
