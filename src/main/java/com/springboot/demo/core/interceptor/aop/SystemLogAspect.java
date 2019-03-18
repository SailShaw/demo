package com.springboot.demo.core.interceptor.aop;


import cn.hutool.system.SystemUtil;
import com.springboot.demo.entity.SysLog;
import com.springboot.demo.entity.User;
import com.springboot.demo.service.ISysLogService;
import com.springboot.demo.util.UUIDTool;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;


/**
 * Create By SINYA
 * Description:日志切面类
 */

@Aspect
@Component
@SuppressWarnings("all")
public class SystemLogAspect {

    @Resource
    private ISysLogService sysLogService;

    private final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //Service层切点
    @Pointcut("@annotation(com.springboot.demo.core.interceptor.aop.SystemServiceLog)")
    public void serviceAspect(){
    }

    //Controller层切点
    @Pointcut("@annotation(com.springboot.demo.core.interceptor.aop.SystemControllerLog)")
    public void controllerAspect(){

    }

    /**
     * @Description 前置通知 用于拦截Controller层记录用户的操作
     * @param joinPoint
     */

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        //读取session中的用户
        User user = (User) session.getAttribute("user");

        //获取用户IP地址
        String ip = SystemUtil.getHostInfo().getAddress();

        try {
            //控制台输出
            System.out.println("------------->前置通知开始<-------------");
            System.out.println("请求方法"+(joinPoint.getTarget().getClass().getName() + "."
                    + joinPoint.getSignature().getName()));
            System.out.println("方法描述" + getControllerMethodDescription(joinPoint));
            System.out.println("请求者" + user.getZnName());
            System.out.println("请求地址" + ip);

            //保存到数据库
            SysLog log = null;
            log.setLogId(UUIDTool.getUUID());
            log.setUserName(user.getZnName());
            log.setRequestMethod(joinPoint.getTarget().getClass().getName() + "."
                    + joinPoint.getSignature().getName());
            log.setRequestDesc(getControllerMethodDescription(joinPoint));

            sysLogService.saveLog(log);
        }catch (Exception e){
            logger.error("------------->前置通知<-------------");
            logger.error("异常信息:{}",e.getMessage());
        }
    }

    /**
     * @Description  异常通知 用于拦截service层记录异常日志
     * @param joinPoint
     * @param e
     */

    @AfterThrowing(pointcut = "serviceAspect()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable e){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        User user = (User) session.getAttribute("user");
        //获取请求ip
        String ip = SystemUtil.getHostInfo().getAddress();
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs()!=null&&joinPoint.getArgs().length>0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params += (joinPoint.getArgs()[i]) +";";
            }
        }
        try{
            /*========控制台输出=========*/
            System.out.println("=====异常通知开始=====");
            System.out.println("异常代码:" + e.getClass().getName());
            System.out.println("异常信息:" + e.getMessage());
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getServiceMethodDescription(joinPoint));
            System.out.println("请求人:" + user.getZnName());
            System.out.println("请求IP:" + ip);
            System.out.println("请求参数:" + params);

            //保存到数据库
            SysLog log = null;
            log.setLogId(UUIDTool.getUUID());
            log.setUserName(user.getZnName());
            log.setRequestMethod(joinPoint.getTarget().getClass().getName() + "."
                    + joinPoint.getSignature().getName());
            log.setRequestDesc(getControllerMethodDescription(joinPoint));

            sysLogService.saveLog(log);
        }catch (Exception ex){
            logger.error("------------->异常通知<-------------");
            logger.error("异常信息:{}",ex.getMessage());
        }

    }

    /**
     * @Description  获取注解中对方法的描述信息 用于service层注解
     * @param joinPoint
     * @return
     * @throws Exception
     */

    public static String getServiceMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for(Method method:methods){
            if (method.getName().equals(methodName)) {
                Class[] classz = method.getParameterTypes();
                if (classz.length == arguments.length){
                    description = method.getAnnotation(SystemServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }


    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

}
