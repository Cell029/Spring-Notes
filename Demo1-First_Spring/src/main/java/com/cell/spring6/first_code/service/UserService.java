package com.cell.spring6.first_code.service;

import com.cell.spring6.first_code.dao.UserDaoImpl;

public class UserService {
    private UserDaoImpl userDaoImpl;

    // 使用set方式注入，必须提供set方法
    // 反射机制要调用这个方法给属性赋值的
    public void setUserDaoImpl(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    public void save(){
        userDaoImpl.insert();
    }
}
