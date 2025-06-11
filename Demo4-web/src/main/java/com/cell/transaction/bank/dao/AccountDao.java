package com.cell.transaction.bank.dao;

import com.cell.transaction.bank.pojo.Account;

public interface AccountDao {
    Account selectByActno(String actno);

    int update(Account act);
}
