package com.cell.aop.service;

import org.springframework.stereotype.Component;

// 目标类
@Component
public class UserService {
    public void service() {
        System.out.println("正在服务中！");
    }
}
