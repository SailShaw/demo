package com.springboot.demo.core.interceptor.aop;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Aspect for Operation
 */

@Target({METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Operation {
    String value() default "";
}
