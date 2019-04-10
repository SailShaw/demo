package com.springboot.demo.core.interceptor.aop;

import java.lang.annotation.*;

/**
 * Create By: SINYA
 * Create Time: 2019/4/10 16:51
 * Update Time: 2019/4/10 16:51
 * Project Name: demo
 * Description:
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Validate {
}
