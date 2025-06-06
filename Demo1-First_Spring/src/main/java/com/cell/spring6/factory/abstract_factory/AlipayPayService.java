package com.cell.spring6.factory.abstract_factory;

public class AlipayPayService implements PayService{
    @Override
    public void pay() {
        System.out.println("使用支付宝支付");
    }
}
