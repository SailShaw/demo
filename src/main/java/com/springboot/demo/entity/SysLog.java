package com.springboot.demo.entity;

import java.io.Serializable;

/**
 * Create By SINYA
 * Description:
 */
public class SysLog implements Serializable {

    private static final long serialVersionUID = 2713975405735052081L;

    private String logId;//日志ID
    private String userName;//用户名字
    private String userIp;//用户IP
    private String requestMethod;//请求方法
    private String requestDesc;//方法描述
    private String createTime;//记录日期


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestDesc() {
        return requestDesc;
    }

    public void setRequestDesc(String requestDesc) {
        this.requestDesc = requestDesc;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
