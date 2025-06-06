package com.cell.spring6.bean_lifecycle.test;

import com.cell.spring6.bean_lifecycle.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class BeanLifecycleTest {
    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean_lifecycle/lifecycle.xml");
        User userBean = applicationContext.getBean("userBean", User.class);
        System.out.println("8.使用Bean");
        // 只有正常关闭spring容器才会执行销毁方法
        ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) applicationContext;
        context.close();
    }

    @Test
    public void test2() {
        // 自己new的对象
        User user = new User();
        System.out.println(user);

        // 创建 默认可列表BeanFactory 对象
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 注册Bean
        factory.registerSingleton("userBean", user);
        // 从spring容器中获取bean
        User userBean = factory.getBean("userBean", User.class);
        System.out.println(userBean);
    }
}
