package com.springboot.demo.core.interceptor.aop;

import cn.hutool.system.SystemUtil;
import com.springboot.demo.entity.SysLog;
import com.springboot.demo.entity.User;
import com.springboot.demo.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Create By SINYA
 * Description:
 */
@Aspect
@Component
public class SysAspect {

    @Resource
    private ISysLogService sysLogService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.springboot.demo.core.interceptor.aop.Operation)")
    public void logPointCut(){
    }

    @AfterReturning("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        SysLog sysLog = new SysLog();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取操作
        Operation operation = method.getAnnotation(Operation.class);
        if (operation != null) {
            String value = operation.value();
            sysLog.setRequestDesc(value);//保存获取的操作
        }
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        /*
         * 1.取得请求中的参数
         * 2.遍历
         * 3.判断 obj instance of HttpServletRequest
         * 4.赋值,结束循环
         * 5.转型HttpServletRequest req = (HttpServletRequest) obj;
         * 6.判空String params = JSONUtil.toJsonStr(args);
         */
        Object[] args = joinPoint.getArgs();
        Object obj = null;
        //遍历方法参数数组
        for (Object val : args) {
            if (val instanceof HttpServletRequest) {
                obj = val;
                break;
            }
        }
        //获取request
        HttpServletRequest request = null;
        if(!StringUtils.isEmpty(obj)){
            request = (HttpServletRequest) obj;
        }
        User userInfo = null;
        //判空
        if (!StringUtils.isEmpty(request)) {
            userInfo = (User) request.getSession().getAttribute("user");
        }
        //注入Syslog对象
        //username应从session里取出
        sysLog.setUserName(userInfo.getZnName());
        sysLog.setUserIp(SystemUtil.getHostInfo().getAddress());
        sysLog.setRequestMethod(className + "." + methodName);
        //调用service保存SysLog实体类到数据库
        sysLogService.saveLog(sysLog);
    }

}
