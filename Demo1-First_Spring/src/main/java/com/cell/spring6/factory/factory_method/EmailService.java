package com.cell.spring6.factory.factory_method;

public class EmailService implements MessageService{
    @Override
    public void send(String msg) {
        System.out.println("发送邮件：" + msg);
    }
}
