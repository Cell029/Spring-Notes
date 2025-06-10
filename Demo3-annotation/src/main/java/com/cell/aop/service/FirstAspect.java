package com.cell.aop.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1) //设置优先级
public class FirstAspect {
    @Around("execution(* com.cell.aop.service.OrderService.*(..))")
    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("FirstAspect环绕通知开始");
        // 执行目标方法。
        proceedingJoinPoint.proceed();
        System.out.println("FirstAspect环绕通知结束");
    }

    @Before("execution(* com.cell.aop.service.OrderService.*(..))")
    public void beforeAdvice(){
        System.out.println("FirstAspect前置通知");
    }

    @AfterReturning("execution(* com.cell.aop.service.OrderService.*(..))")
    public void afterReturningAdvice(){
        System.out.println("FirstAspect后置通知");
    }

    @AfterThrowing("execution(* com.cell.aop.service.OrderService.*(..))")
    public void afterThrowingAdvice(){
        System.out.println("FirstAspect异常通知");
    }

    @After("execution(* com.cell.aop.service.OrderService.*(..))")
    public void afterAdvice(){
        System.out.println("FirstAspect最终通知");
    }
}
