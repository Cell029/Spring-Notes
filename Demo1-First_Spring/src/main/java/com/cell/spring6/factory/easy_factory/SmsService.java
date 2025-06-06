package com.cell.spring6.factory.easy_factory;

public class SmsService implements MessageService{
    @Override
    public void sendMessage(String msg) {
        System.out.println("发送短信：" + msg);
    }
}
