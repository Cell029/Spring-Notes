package com.cell.spring6.factory.abstract_factory;

public class AlipayFactory implements PaymentFactory{
    @Override
    public PayService createPayService() {
        return new AlipayPayService();
    }

    @Override
    public RefundService createRefundService() {
        return new AlipayRefundService();
    }
}
