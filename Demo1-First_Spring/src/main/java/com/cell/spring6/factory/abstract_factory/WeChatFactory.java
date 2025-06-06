package com.cell.spring6.factory.abstract_factory;

public class WeChatFactory implements PaymentFactory{
    @Override
    public PayService createPayService() {
        return new WeChatPayService();
    }

    @Override
    public RefundService createRefundService() {
        return new WeChatRefundService();
    }
}
