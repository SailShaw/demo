/*
package com.springboot.demo.core.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * Create By: SINYA
 * Create Time: 2019/4/6 18:02
 * Update Time: 2019/4/6 18:02
 * Project Name: demo
 * Description:
 *//*


@Configuration
public class SessionInterceptor implements WebMvcConfigurer {

    @Resource
    private Authentication authentication;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List list = new ArrayList();
        list.add("/sendResetPasswordLink");
        list.add("/resetPasswordByCode");
        list.add("/register");
        list.add("/login");
        //设置拦截的路径、不拦截的路径、优先级等等
        registry.addInterceptor(authentication).addPathPatterns("/*").excludePathPatterns(list);
    }
}
*/
