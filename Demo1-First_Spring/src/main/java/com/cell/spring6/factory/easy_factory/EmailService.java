package com.cell.spring6.factory.easy_factory;

public class EmailService implements MessageService{
    @Override
    public void sendMessage(String msg) {
        System.out.println("发送邮件：" + msg);
    }
}
