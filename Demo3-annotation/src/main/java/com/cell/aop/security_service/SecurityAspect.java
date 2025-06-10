package com.cell.aop.security_service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SecurityAspect {
    @Pointcut("execution(* com.cell.aop.security_service..save*(..))")
    public void savePointcut(){}

    @Pointcut("execution(* com.cell.aop.security_service..delete*(..))")
    public void deletePointcut(){}

    @Pointcut("execution(* com.cell.aop.security_service..modify*(..))")
    public void modifyPointcut(){}

    @Before("savePointcut() || deletePointcut() || modifyPointcut()")
    public void beforeAdivce(JoinPoint joinpoint){
        System.out.println("正在操作"+joinpoint.getSignature().getName()+"方法");
    }
}
