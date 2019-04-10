package com.springboot.demo.core.interceptor.aop;

import com.springboot.demo.core.model.ResultData;
import com.springboot.demo.entity.Menu;
import com.springboot.demo.entity.User;
import com.springboot.demo.mapper.MenuMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/4/10 16:52
 * Update Time: 2019/4/10 16:52
 * Project Name: demo
 * Description:
 */
@Aspect
@Component
public class ValidateAspect {

    private final static Logger logger = LoggerFactory.getLogger(ValidateAspect.class);

    private final static int len = 21;

    @Resource
    private MenuMapper menuMapper;

    @Around(value = "@annotation(com.springboot.demo.core.interceptor.aop.Validate)")
    public Object doBefore(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("拦截请求");
        ResultData resultData = new ResultData();

        //获取到请求的属性
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取到请求对象
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        //URL：根据请求对象拿到访问的地址
        String url = request.getRequestURL().substring(len);
        Object object = null;
        //获取当前用户权限列表
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user.getRoleId()==null){
            logger.error("未登录");
            response.setStatus(403);
            return "403";
        }
        List<Menu> roleList = menuMapper.getMenuListByRole(user.getRoleId());

        for (Menu m:roleList) {
            if (url.equals(m.getPermitUrl())) {
                object =  pjp.proceed();
                break;
            }
        }
        return object;
    }

}
