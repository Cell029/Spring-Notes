package com.cell.transaction.bank.service;

public interface AccountService {
    void transfer(String fromActno, String toActno, double money);
}
