package com.cell.spring6.factory.abstract_factory;

public class AlipayRefundService implements RefundService{
    @Override
    public void refund() {
        System.out.println("使用支付宝退款");
    }
}
