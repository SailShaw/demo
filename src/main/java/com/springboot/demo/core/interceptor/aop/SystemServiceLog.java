package com.springboot.demo.core.interceptor.aop;

import java.lang.annotation.*;

/**
 * Create By SINYA
 * Description:自定义注解,拦截Service
 */

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {
    String description() default "";
}
