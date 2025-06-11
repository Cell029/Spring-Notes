package com.cell.transaction.bank.service;

import com.cell.transaction.bank.dao.AccountDao;
import com.cell.transaction.bank.pojo.Account;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Transactional(timeout = 2)
public class AccountServiceImpl implements AccountService{

    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Override
    public void transfer(String fromActno, String toActno, double money) {

        /*try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        // 查询账户余额是否充足
        Account fromAct = accountDao.selectByActno(fromActno);
        if (fromAct.getBalance() < money) {
            throw new RuntimeException("账户余额不足");
        }
        // 余额充足，开始转账
        Account toAct = accountDao.selectByActno(toActno);
        fromAct.setBalance(fromAct.getBalance() - money);
        toAct.setBalance(toAct.getBalance() + money);
        int count = accountDao.update(fromAct);



        // 模拟异常
        /*String s = null;
        s.toString();*/

        count += accountDao.update(toAct);


        if (count != 2) {
            throw new RuntimeException("转账失败，请联系银行");
        }
    }
}
