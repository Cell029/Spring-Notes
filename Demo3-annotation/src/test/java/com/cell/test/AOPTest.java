package com.cell.test;

import com.cell.aop.security_service.ProductService;
import com.cell.aop.security_service.SecurityConfiguration;
import com.cell.aop.service.OrderService;
import com.cell.aop.service.UserService;
import com.cell.aop.transaction_service.AccountService;
import com.cell.aop.transaction_service.AopConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {
    @Test
    public void testAOP(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop.xml");
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        orderService.generate();
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.service();
    }

    @Test
    public void testAOPXml(){
        ApplicationContext context = new ClassPathXmlApplicationContext("aopXml.xml");
        com.cell.aopXml.service.UserService userService = context.getBean("userService", com.cell.aopXml.service.UserService.class);
        userService.login();
    }

    @Test
    public void testAOPTransaction(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfiguration.class);
        com.cell.aop.transaction_service.OrderService orderService = applicationContext.getBean("orderService", com.cell.aop.transaction_service.OrderService.class);
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        // 生成订单
        orderService.generate();
        // 取消订单
        orderService.cancel();
        // 转账
        accountService.transfer();
        // 取款
        accountService.withdraw();
    }

    @Test
    public void testAOPSecurity(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SecurityConfiguration.class);
        ProductService productService = applicationContext.getBean("productService", ProductService.class);
        productService.getProduct();
        productService.saveProduct();
        productService.deleteProduct();
        productService.modifyProduct();
    }
}
