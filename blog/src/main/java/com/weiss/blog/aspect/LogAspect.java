package com.weiss.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 日志处理:
 * 实现的目标为：
*     -- 获取所需信息
 *     在controller包下所有的类与方法， 的每个请求， 请求地址， ip， 所使用的方法及类名， 以及所使用的方法需要传入的参数都可以封装成一个类
 *     返回到日志， 方便日志调试。
 *     实现所使用的方法为:aop， 利用前置通知在方法执行前获取到用户所使用的ip， 请求地址(url), 为了获取到所使用的类名方法(className)则利用切点对象(joinPoint)
 *     相应的方法获取到类名(getSignature().getDeclaringTypeName())， 与方法名(getSignature().getName()))
 *     获取参数也是使用切点对象相应的方法(getArgs())
 *
 *   --传递信息
 *      利用返回通知(@AfterReturn)，在方法执行完后将其封装好的类返回到控制台或写入日志
 *
 */

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //execution(* com.weiss.blog.controller.*.*(..))
    //意义为：定义一个 返回值任意， 在com.weiss.blog.controller包下所有类中的所有方法
    @Pointcut("execution(* com.weiss.blog.controller.*.*(..))")
    public void log(){}

    @Before("log()")//前置通知，选中切面
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);

        logger.info("Request : {}", requestLog);
    }

    @After("log()") //后置通知
    public void doAfter(){
//        logger.info("-------------doAfter----------------");
    }

    //拦截后返回内容
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result ： {} ", result);
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
