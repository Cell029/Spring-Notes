package com.cell.proxy.dynamic.client;

import com.cell.proxy.dynamic.service.OrderService;
import com.cell.proxy.dynamic.service.OrderServiceImpl;
import com.cell.proxy.dynamic.service.TimeInvocationHandler;
import com.cell.proxy.dynamic.utils.ProxyUtil;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        // 创建目标对象
        OrderService target = new OrderServiceImpl();
        // 创建代理对象
        /*
         * 1. newProxyInstance 为新代理对象,通过这个方法可以创建代理对象,通过 Proxy.newProxyInstance 在内存中动态生成一个代理类的字节码文件,然后通过这个代理类实例化对象
         * 2. newProxyInstance 有三个参数:
         *   第一个参数:Classloader loader
         *       类加载器:JDK 要求目标类的类加载器必须和代理类的类加载器使用同一个, 所以把目标类的类加载器传递给 Proxy
         *   第二个参数:Class<?>[] interfaces
         *       代理类和目标类要实现同一个或同一些接口, 所以要通过目标类来获取它实现的接口
         *   第三个参数:InvocationHandler h
         *       通过这个接口传递需要增强的程序, 所以要在这个接口中编写代码, 所以得写这个接口的实现类
         * */
        // OrderService proxyObj = (OrderService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new TimeInvocationHandler(target));
        OrderService proxyObj = (OrderService) ProxyUtil.newProxyInstance(target);
        // 调用代理对象的代理方法
        proxyObj.generate(); // 执行时会调用 invoke 方法
        proxyObj.modify();
        proxyObj.detail();
        String name = proxyObj.getName();
        System.out.println(name);
    }


}
