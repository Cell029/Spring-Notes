package com.cell.proxy.dynamic.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/*
专门负责计时的一个调用处理器对象
*/
public class TimeInvocationHandler implements InvocationHandler {

    // 目标对象
    private Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    // 当代理对象调用代理方法的时候,注册在 InvocationHandler 调用处理器中的 invoke() 方法就会被调用

    /*
    * 第一个参数:
    *   Object proxy: 代理对象的引用(使用较少)
    * 第二个参数:
    *   Method method: 目标对象的目标方法, 通过这个执行目标方法
    * 第三个参数:
    *   Object[] args: 目标方法上的实参
    * */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        long begin = System.currentTimeMillis();
        // 将目标对象和目标对象需要使用的参数传递进来
        Object retValue = method.invoke(target, args);
        long end = System.currentTimeMillis();
        System.out.println("耗时"+(end - begin)+"毫秒");
        return retValue;
    }
}
