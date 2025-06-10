package com.cell.aop.service;

import org.springframework.stereotype.Component;

// 目标类
@Component("orderService")
public class OrderService {
    // 目标方法
    public void generate(){
        System.out.println("订单已生成！");
        /*if (1 == 1) {
            throw new RuntimeException("模拟异常发生");
        }*/
    }
}
