package com.cell.annotation.dao;

import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao {
    public String getUserName() {
        return "Alice";
    }
}
