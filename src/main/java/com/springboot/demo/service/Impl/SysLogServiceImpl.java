package com.springboot.demo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.springboot.demo.entity.SysLog;
import com.springboot.demo.mapper.SysLogMapper;
import com.springboot.demo.service.ISysLogService;
import com.springboot.demo.util.SnowFlake;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Implements for Syslog
 */

@Service
public class SysLogServiceImpl implements ISysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    private SnowFlake snowFlake = new SnowFlake(2, 9);

    /**
     * 保存日志
     *
     * @param sysLog
     */
    @Override
    public void saveLog(SysLog sysLog) {
        //生成ID
        sysLog.setLogId(String.valueOf(snowFlake.nextId()));
        //保存
        sysLogMapper.saveLog(sysLog);
    }

    /**
     * 查询日志列表
     *
     * @param pageNum
     * @param pageSize
     * @param sysLog
     * @return
     */
    @Override
    public List<SysLog> getSysLogListByPage(Integer pageNum, Integer pageSize, SysLog sysLog) {
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<SysLog> result = sysLogMapper.getSysLogListByPage(sysLog);
        return result;
    }


}
