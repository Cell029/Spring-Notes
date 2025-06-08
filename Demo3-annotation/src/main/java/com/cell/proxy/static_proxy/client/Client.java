package com.cell.proxy.static_proxy.client;

import com.cell.proxy.static_proxy.service.OrderService;
import com.cell.proxy.static_proxy.service.OrderServiceImpl;
import com.cell.proxy.static_proxy.service.OrderServiceProxy;

public class Client {
    public static void main(String[] args) {
        // 创建目标对象
        OrderService target = new OrderServiceImpl();
        // 创建代理对象
        OrderService proxy = new OrderServiceProxy(target);
        // 调用代理对象的代理方法
        proxy.generate();
        proxy.modify();
        proxy.detail();
    }
}
