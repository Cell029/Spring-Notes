package com.cell.transaction.test;

import com.cell.spring_mybatis.bank.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:Spring-MyBatis.xml") // 加载配置文件
public class SpringMybatisTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void test1() {
        accountService.getAll();
    }
}
