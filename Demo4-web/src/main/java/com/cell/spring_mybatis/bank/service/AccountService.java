package com.cell.spring_mybatis.bank.service;

import com.cell.spring_mybatis.bank.pojo.Account;

import java.util.List;

public interface AccountService {
    int save(Account act);

    int deleteByActno(String actno);

    int update(Account act);

    Account getByActno(String actno);

    List<Account> getAll();

    void transfer(String fromActno, String toActno, double money);
}
