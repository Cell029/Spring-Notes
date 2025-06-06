package com.cell.spring6.factory.test;

import com.cell.spring6.factory.abstract_factory.PayService;
import com.cell.spring6.factory.abstract_factory.RefundService;
import com.cell.spring6.factory.easy_factory.MessageFactory;
import com.cell.spring6.factory.factory_method.MessageService;
import com.cell.spring6.factory.factory_method.EmailService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryTest {
    @Test
    public void test1() {
        /*MessageService service = MessageFactory.createService("email");
        service.sendMessage("Hello Spring!");*/
    }

    @Test
    public void test2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("factory.xml");
        EmailService emailService = applicationContext.getBean("emailService", EmailService.class);
        emailService.send("Hello Factory Method!");
    }

    @Test
    public void test3() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("factory.xml");
        MessageService emailServiceInstance = applicationContext.getBean("emailServiceInstance", MessageService.class);
        emailServiceInstance.send("Hello Factory Method!");
    }

    @Test
    public void test4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("factory.xml");
        PayService payService = context.getBean("payService", PayService.class);
        RefundService refundService = context.getBean("refundService", RefundService.class);
        payService.pay();
        refundService.refund();
    }

    @Test
    public void test5() {
        ApplicationContext context = new ClassPathXmlApplicationContext("factory.xml");
        PayService payServiceAli = context.getBean("payServiceAli", PayService.class);
        RefundService refundServiceAli = context.getBean("refundServiceAli", RefundService.class);
        payServiceAli.pay();
        refundServiceAli.refund();
    }
}
