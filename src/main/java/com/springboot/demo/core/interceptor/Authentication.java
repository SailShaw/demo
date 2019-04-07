/*
package com.springboot.demo.core.interceptor;

import com.springboot.demo.entity.User;
import com.springboot.demo.util.ObjectHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/**
 * Create By: SINYA
 * Create Time: 2019/4/6 10:57
 * Update Time: 2019/4/6 10:57
 * Project Name: demo
 * Description:
 *//*

public class Authentication implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(Authentication.class);

    */
/*
     * 进入controller层之前拦截请求
     * 返回值：表示是否将当前的请求拦截下来  false：拦截请求，请求别终止。true：请求不被拦截，继续执行
     * Object obj:表示被拦的请求的目标对象（controller中方法）
     *//*

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        logger.info("请求地址拦截");
        User user = (User) request.getSession().getAttribute("user");
        if (ObjectHandle.reflectFieldIsNotALLNull(user,new String[]{"serialVersionUID"})) {
            return true;
        }else {

            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("处理请求完成后视图渲染之前的处理操作");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("视图渲染之后的操作0");
    }
}
*/
