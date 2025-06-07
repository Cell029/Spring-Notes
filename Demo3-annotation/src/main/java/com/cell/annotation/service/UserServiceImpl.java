package com.cell.annotation.service;

import com.cell.annotation.dao.UserDao;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Resource(name = "userDao")
    private UserDao userDao;
    @Override
    public String getUserInfo() {
        return "UserServiceImpl: " + userDao.getUserName();
    }
}
