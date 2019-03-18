package com.springboot.demo.core.exception;

/**
 * Create By SINYA
 * Description:业务异常类
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 2332608236621015980L;

    //错误代码
    private String errCode;
    private String errMsg;
}
