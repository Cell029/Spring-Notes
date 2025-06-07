package com.cell.test;

import com.cell.annotation.AppConfig;
import com.cell.annotation.bean.User;
import com.cell.annotation.controller.AppController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationTest {
    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User userBean = applicationContext.getBean("userBean", User.class);
        System.out.println(userBean);
    }

    @Test
    public void test2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        AppController appController = applicationContext.getBean("appController", AppController.class);
        String userInfo = appController.getUserInfo();
        System.out.println(userInfo);
    }

    @Test
    public void test3() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        AppController appController = annotationConfigApplicationContext.getBean("appController", AppController.class);
        String userInfo = appController.getUserInfo();
        System.out.println(userInfo);
    }
}
