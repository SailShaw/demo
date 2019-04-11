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
        //判空
        if (user == null || user.getRoleId() == null) {
            resultData.setCode(101);
            resultData.setMassage("未登录");
            object = resultData;
        } else {
            //获取该角色可访问权限清单
            List<Menu> roleList = menuMapper.getMenuListByRole(user.getRoleId());
            //判断结果标记
            boolean flag = false;
            for (int i = 0; i < roleList.size(); i++) {
                if (!url.equals(roleList.get(i).getPermitUrl())) {
                    //不满足，继续判断，并标记
                    flag = false;
                    continue;
                }
            }
            //循环完成后，根据标记判断是否有权限
            if (flag == true) {
                //继续执行
                object = pjp.proceed();
            }else {
                //返回403
                resultData.setCode(403);
                resultData.setMassage("无权访问");
                object = resultData;
            }
        }
        logger.info("拦截结束，返回结果");
        return object;
    }

}
