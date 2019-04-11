package com.springboot.demo.core.model;

/**
 * Create By: SINYA
 * Create Time: 2019/4/10 15:12
 * Update Time: 2019/4/10 15:12
 * Project Name: demo
 * Description:
 */
public class ResultData {

    /**
     * 自定义状态码
     */
    private int code;
    /**
     * 返回数据
     */
    private Object data;
    /**
     * 自定义消息
     */
    private String massage;

    public static int SUCCESS_CODE = 200;
    private String SUCCESS_MSG = "操作成功";

    /* ConStructor */
    public ResultData() {
        this.code = SUCCESS_CODE;
        this.massage = SUCCESS_MSG;

    }

    public ResultData(int code, String massage) {
        this.code = code;
        this.massage = massage;
    }

    public ResultData(Object data) {
        this.code=SUCCESS_CODE;
        this.massage = SUCCESS_MSG;
        this.data = data;
    }

    public ResultData(int code, Object data, String massage) {
        this.code = code;
        this.data = data;
        this.massage = massage;
    }

    /* Getter & Setter */

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
