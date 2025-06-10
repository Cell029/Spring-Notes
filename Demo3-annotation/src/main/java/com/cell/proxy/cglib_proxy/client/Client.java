package com.cell.proxy.cglib_proxy.client;

import com.cell.proxy.cglib_proxy.service.TimeMethodInterceptor;
import com.cell.proxy.cglib_proxy.service.UserService;
import net.sf.cglib.proxy.Enhancer;

public class Client {
    public static void main(String[] args) {
        // 创建字节码增强器，这个对象是 CGLIB 库中的核心对象，依靠它来生成代理类
        Enhancer enhancer = new Enhancer();
        // 告诉cglib要继承哪个类
        enhancer.setSuperclass(UserService.class);
        // 设置回调接口（等同于JDK动态代理的调用处理器 InvocationHandler）
        // 在cglib中是方法拦截器接口：MethodInterceptor
        enhancer.setCallback(new TimeMethodInterceptor());
        // 创建代理对象，这里做两件事：1. 在内存中生成 UserService 的子类，也就是代理类的字节码；2.创建代理类对象
        UserService userServiceProxy = (UserService)enhancer.create();
        // 调用代理对象的代理方法
        boolean success = userServiceProxy.login("admin", "123");
        System.out.println(success? "登陆成功" : "登陆失败");
        userServiceProxy.logout();
    }
}
