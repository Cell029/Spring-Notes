package com.cell.aop.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


// 切面类
@Aspect
@Component("myAspect")
@Order(2) //设置优先级
public class MyAspect {

    /*// 定义可复用的切点表达式
    @Pointcut("execution(* com.cell.aop.service.OrderService.*(..))")
    public void orderServiceMethods() {} // 方法体为空，仅作为切点的标识

    // 这就是需要增强的代码（通知）
    // 切点表达式，OrderService 类中的所有方法
    @Before("execution(* com.cell.aop.service.OrderService.*(..))")
    public void advice(){
        System.out.println("前置通知");
    }

    @AfterReturning("execution(* com.cell.aop.service.OrderService.*(..))")
    public void afterReturningAdvice(){
        System.out.println("后置通知");
    }

    @AfterReturning(pointcut = "execution(* com.cell.aop.service.OrderService.*(..))", returning = "result")
    public void logOrderResult(JoinPoint joinPoint, Object result) {
        System.out.println("方法 " + joinPoint.getSignature().getName() + " 返回: " + result);
    }


    @Around("execution(* com.cell.aop.service.OrderService.*(..))")
    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知开始");
        // 执行目标方法
        proceedingJoinPoint.proceed();
        System.out.println("环绕通知结束");
    }

    @AfterThrowing("execution(* com.cell.aop.service.OrderService.*(..))")
    public void afterThrowingAdvice(){
        System.out.println("异常通知");
    }

    @AfterThrowing(pointcut = "execution(* com.cell.aop.service.OrderService.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        System.out.println("方法 " + joinPoint.getSignature().getName() + " 抛出异常: " + ex.getMessage());
    }

    @After("execution(* com.cell.aop.service.OrderService.*(..))")
    public void afterAdvice(){
        System.out.println("最终通知");
    }*/

    @Pointcut("execution(* com.cell.aop.service.OrderService.*(..))")
    public void orderService() {}

    @Pointcut("execution(* com.cell.aop.service.UserService.*(..))")
    public void userService() {}

    // 组合切点：所有服务
    @Pointcut("orderService() || userService()")
    public void allServices() {}

    // 应用于所有服务的通知
    @Before("allServices()")
    public void logServiceAccess(JoinPoint joinPoint) {
        System.out.println("访问方法：" + joinPoint.getSignature().getName());
    }
}
