package com.cell.spring6.first_code.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FirstSpringTest {
    @Test
    public void test1() {
        // 1. 获取 Spring 容器对象
        // 这行代码执行后就会开始解析 spring.xml 文件,然后将 xml 文件里面的所有 bean 对象 new 出来然后放到容器中
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        // 2. 根据 bean 的 id 从 Spring 容器中获取这个对象
        Object userBean = applicationContext.getBean("userBean");
        System.out.println(userBean);

        Object userDaoBean = applicationContext.getBean("userDaoBean");
        System.out.println(userDaoBean);
    }

    @Test
    public void test2() {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.cell.spring6.first_code.bean.User");
            Object obj = clazz.getDeclaredConstructor().newInstance();
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Object dateBean = applicationContext.getBean("dateBean");
        System.out.println(dateBean);
    }
}
