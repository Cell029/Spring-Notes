package com.cell.myspring.test;

import com.cell.myspring.bean.User;
import com.cell.myspring.bean.UserService;
import org.junit.Test;
import org.myspringframwork.core.ApplicationContext;
import org.myspringframwork.core.ClassPathXmlApplicationContext;

public class MySpringTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("myspring.xml");
        User user = (User) context.getBean("user");
        System.out.println(user);
        UserService userService = (UserService) context.getBean("userService");
        userService.save();
    }
}
