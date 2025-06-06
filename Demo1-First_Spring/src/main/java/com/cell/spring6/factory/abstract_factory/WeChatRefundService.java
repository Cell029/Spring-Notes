package com.cell.spring6.factory.abstract_factory;

public class WeChatRefundService implements RefundService{
    @Override
    public void refund() {
        System.out.println("使用微信退款");
    }
}
