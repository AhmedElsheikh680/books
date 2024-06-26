package com.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class loggingAspect {

    Logger log = LoggerFactory.getLogger(loggingAspect.class);



    @Pointcut(value="execution(* com.spring.repo..*(..))")
    public void forRepositoryLog(){}
    @Pointcut(value = "execution(* com.spring.service..*(..))")
    public void forServiceLog(){}
    @Pointcut(value = "execution(* com.spring.controller..*(..))")
    public void forControllerLog(){}
    @Pointcut(value = "forRepositoryLog() || forServiceLog() || forControllerLog()")
    public void  forAllApp(){}

    @Before(value = "forAllApp()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toString();
        log.info("====> Method Name Is >> {}  "+methodName );
        Object [] args = joinPoint.getArgs();
        for (Object arg: args) {
            log.info("====> argument >> {}", arg);
        }
    }


















}
