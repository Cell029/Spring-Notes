package com.cell.spring6.circular_dependency.test;

import com.cell.spring6.circular_dependency.Husband;
import com.cell.spring6.circular_dependency.Wife;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CircularDependencyTest {
    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("circular_dependency/cd.xml");
        Husband husbandBean = applicationContext.getBean("husbandBean", Husband.class);
        Wife wifeBean = applicationContext.getBean("wifeBean", Wife.class);
        System.out.println(husbandBean);
        System.out.println(wifeBean);
    }

    @Test
    public void test2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("circular_dependency/cd.xml");
        Husband hBean = applicationContext.getBean("husbandBean", Husband.class);
        Wife wBean = applicationContext.getBean("wifeBean", Wife.class);
        System.out.println(hBean);
        System.out.println(wBean);
    }
    
}
