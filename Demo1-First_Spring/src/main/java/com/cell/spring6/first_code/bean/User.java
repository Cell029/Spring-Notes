package com.cell.spring6.first_code.bean;

// bean 对象封装 User 信息,让 Spring 帮助创建 User 对象
public class User {
    private String username;
    private String password;
    private int age;

    public User() {
        // System.out.println("无参构造器被调用");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
