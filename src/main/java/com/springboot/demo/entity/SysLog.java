package com.springboot.demo.entity;

import java.io.Serializable;

/**
 * Create By SINYA
 * Description:
 */
public class SysLog implements Serializable {

    private static final long serialVersionUID = 2713975405735052081L;

    private String longId;//
    private String userId;//
    private String operation;//
    private String timeCost;//
    private String method;//
    private String parmas;//
    private String createTime;//

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLongId() {
        return longId;
    }

    public void setLongId(String longId) {
        this.longId = longId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(String timeCost) {
        this.timeCost = timeCost;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParmas() {
        return parmas;
    }

    public void setParmas(String parmas) {
        this.parmas = parmas;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
