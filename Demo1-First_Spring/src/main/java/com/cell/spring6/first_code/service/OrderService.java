package com.cell.spring6.first_code.service;

import com.cell.spring6.first_code.dao.OrderDao;
import com.cell.spring6.first_code.dao.UserDaoImpl;

public class OrderService {
    private OrderDao orderDao;
    private UserDaoImpl userDaoImpl;

    public OrderService() {

    }

    // 通过反射机制调用构造方法给属性赋值
    public OrderService(OrderDao orderDao, UserDaoImpl userDaoImpl) {
        this.orderDao = orderDao;
        this.userDaoImpl = userDaoImpl;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void delete(){
        orderDao.deleteById();
        userDaoImpl.insert();
    }

    public void generate() {
        orderDao.insert();
    }
}
