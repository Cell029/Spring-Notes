package com.cell.proxy.dynamic.utils;

import com.cell.proxy.dynamic.service.TimeInvocationHandler;

import java.lang.reflect.Proxy;

public class ProxyUtil {
    public static Object newProxyInstance(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new TimeInvocationHandler(target));
    }
}
