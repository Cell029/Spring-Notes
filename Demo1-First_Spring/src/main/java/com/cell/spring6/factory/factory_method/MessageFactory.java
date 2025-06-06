package com.cell.spring6.factory.factory_method;

public class MessageFactory {
    // 静态工厂方法
    public static MessageService createEmailService() {
        //System.out.println("静态方法被调用");
        return new EmailService();
    }

    // 实例工厂方法
    public MessageService createEmailServiceByInstance() {
        //System.out.println("实例方法被调用");
        return new EmailService();
    }
}
