package com.springboot.demo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.springboot.demo.entity.SysLog;
import com.springboot.demo.mapper.SysLogMapper;
import com.springboot.demo.service.ISysLogService;
import com.springboot.demo.util.UUIDTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
@Service
public class SysLogServiceImpl implements ISysLogService{

    @Resource
    private SysLogMapper sysLogMapper;

    /**
     * 保存日志
     * @param sysLog
     */
    @Override
    public void saveLog(SysLog sysLog) {
        sysLog.setLogId(UUIDTool.getUUID());
        sysLogMapper.saveLog(sysLog);
    }

    /**
     * 查询日志列表
     * @param pageNum
     * @param pageSize
     * @param sysLog
     * @return
     */
    @Override
    public List<SysLog> getSysLogListByPage(Integer pageNum, Integer pageSize, SysLog sysLog) {
        PageHelper.startPage(pageNum,pageSize);

        List<SysLog> result = sysLogMapper.getSysLogListByPage(sysLog);
        return result;
    }
}
