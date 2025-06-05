package com.cell.spring6.first_code.bean;

// bean 对象封装 User 信息,让 Spring 帮助创建 User 对象
public class User {
    public User() {
        System.out.println("无参构造器被调用");
    }
}
