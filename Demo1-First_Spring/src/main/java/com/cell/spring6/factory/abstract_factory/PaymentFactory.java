package com.cell.spring6.factory.abstract_factory;

public interface PaymentFactory {
    PayService createPayService();
    RefundService createRefundService();
}
