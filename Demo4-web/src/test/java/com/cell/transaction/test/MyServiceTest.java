package com.cell.transaction.test;

import com.cell.transaction.bank.config.TransactionConfig;
import com.cell.transaction.bank.service.AccountService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) // 替代 JUnit4 的 @RunWith
@ContextConfiguration(classes = TransactionConfig.class) // 加载配置类
// @ContextConfiguration("classpath:bank.xml") // 加载配置文件
public class MyServiceTest {
    @Resource(name = "accountService")
    private AccountService accountService;

    @Test
    public void testAccountService() {
        accountService.transfer("act-001", "act-002", 10000);
        System.out.println("转账成功");
    }
}
