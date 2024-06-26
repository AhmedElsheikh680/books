package com.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class logTimeAspect {

    Logger log = LoggerFactory.getLogger(logTimeAspect.class);

//    @Around(value = "execution(* com.spring.service..*(..))")
//    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        long startTime = System.currentTimeMillis();
//        StringBuilder sb = new StringBuilder("KPI:");
//        sb.append("[").append(joinPoint.getKind()).append("]\tfor: ").append(joinPoint.getSignature())
//                .append("\twithArgs: ").append("(").append(StringUtils.join(joinPoint.getArgs(), ",")).append(")");
//        sb.append("\ttook: ");
//        Object returnValue = joinPoint.proceed();
//        log.info(sb.append(System.currentTimeMillis() - startTime).append(" ms.").toString());
//
//        return returnValue;
//    }

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
