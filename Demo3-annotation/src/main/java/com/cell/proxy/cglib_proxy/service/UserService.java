package com.cell.proxy.cglib_proxy.service;

public class UserService {
    public boolean login(String username, String password){
        System.out.println("用户正在登录系统....");
        if ("admin".equals(username) && "123".equals(password)) {
            return true;
        }
        return false;
    }

    public void logout(){
        System.out.println("用户正在退出系统....");
    }
}
