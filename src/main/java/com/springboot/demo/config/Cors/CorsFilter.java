package com.springboot.demo.config.Cors;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create By SINYA
 * Description:跨域请求过滤器
 */
@Component
public class CorsFilter extends OncePerRequestFilter {





    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setCharacterEncoding("UTF-8");
        filterChain.doFilter(request, response);
    }


    @Override
    public void destroy() {

    }
}