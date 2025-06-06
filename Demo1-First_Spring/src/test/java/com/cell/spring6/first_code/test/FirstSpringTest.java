package com.cell.spring6.first_code.test;

import com.cell.spring6.first_code.bean.SpringBean;
import com.cell.spring6.first_code.bean.Student;
import com.cell.spring6.first_code.jdbc.DataSourceConfig;
import com.cell.spring6.first_code.jdbc.MyDataSource;
import com.cell.spring6.first_code.service.OrderService;
import com.cell.spring6.first_code.service.UserService;
import com.cell.spring6.first_code.bean.User;
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

    @Test
    public void test4(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = applicationContext.getBean("userServiceBean", UserService.class);
        userService.save();
    }

    @Test
    public void test5(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        OrderService orderServiceBean = applicationContext.getBean("orderServiceBean", OrderService.class);
        orderServiceBean.delete();
    }

    @Test
    public void test6(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("set-di.xml");
        User user = applicationContext.getBean("userBean", User.class);
        System.out.println(user);
    }

    @Test
    public void test7(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("set-di.xml");
        MyDataSource myDataSourceBean = applicationContext.getBean("myDataSourceBean", MyDataSource.class);
        System.out.println(myDataSourceBean);
    }

    @Test
    public void test8(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("set-di.xml");
        Student studentBean = applicationContext.getBean("studentBean", Student.class);
        System.out.println(studentBean);
    }

    @Test
    public void test9() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("set-di.xml");
        DataSourceConfig dataSourceConfig = applicationContext.getBean("dataSourceConfig", DataSourceConfig.class);
        dataSourceConfig.print();
    }

    @Test
    public void test10() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("set-di.xml");
        MyDataSource dataSource = applicationContext.getBean("dataSource", MyDataSource.class);
        System.out.println(dataSource);
    }

    @Test
    public void test11() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean-scope.xml");
        SpringBean springBean1 = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println(springBean1);
        SpringBean springBean2 = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println(springBean2);
        SpringBean springBean3 = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println(springBean3);
    }

    @Test
    public void test12() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean-scope.xml");
        SpringBean springBean1 = applicationContext.getBean("springBean2", SpringBean.class);
        System.out.println(springBean1);
        SpringBean springBean2 = applicationContext.getBean("springBean2", SpringBean.class);
        System.out.println(springBean2);
        SpringBean springBean3 = applicationContext.getBean("springBean2", SpringBean.class);
        System.out.println(springBean3);
    }

    @Test
    public void test13() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean-scope.xml");
        SpringBean sb1 = applicationContext.getBean("sb", SpringBean.class);
        SpringBean sb2 = applicationContext.getBean("sb", SpringBean.class);
        System.out.println(sb1);
        System.out.println(sb2);
        // 启动线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                SpringBean a = applicationContext.getBean("sb", SpringBean.class);
                SpringBean b = applicationContext.getBean("sb", SpringBean.class);
                System.out.println(a);
                System.out.println(b);
            }
        }).start();
    }
}
