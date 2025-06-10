package com.cell.aopXml.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {
    // 前置通知
    public void beforeLog(JoinPoint joinPoint) {
        System.out.println("前置通知: " + joinPoint.getSignature().getName() + " 方法开始执行");
    }

    // 最终通知
    public void afterLog(JoinPoint joinPoint) {
        System.out.println("后置通知: " + joinPoint.getSignature().getName() + " 方法执行结束");
    }

    // 后置通知
    public void afterReturningLog(JoinPoint joinPoint, Object result) {
        System.out.println("返回后通知: " + joinPoint.getSignature().getName() + " 方法返回值: " + result);
    }

    // 异常通知
    public void afterThrowingLog(JoinPoint joinPoint, Exception ex) {
        System.out.println("异常通知: " + joinPoint.getSignature().getName() + " 方法抛出异常: " + ex.getMessage());
    }

    // 环绕通知
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知: " + joinPoint.getSignature().getName() + " 方法开始");
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("环绕通知: " + joinPoint.getSignature().getName() + " 方法执行耗时: " + (endTime - startTime) + "ms");
        return result;
    }
}
