package com.cell.spring6.factory.abstract_factory;

public class WeChatPayService implements PayService{
    @Override
    public void pay() {
        System.out.println("使用微信支付");
    }
}
