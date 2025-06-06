package com.cell.spring6.factory.easy_factory;

public class MessageFactory {
    public static MessageService createService(String type) {
        if ("email".equalsIgnoreCase(type)) {
            return new EmailService();
        } else if ("sms".equalsIgnoreCase(type)) {
            return new SmsService();
        }
        throw new IllegalArgumentException("不支持的类型：" + type);
    }
}
