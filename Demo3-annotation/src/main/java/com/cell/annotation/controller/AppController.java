package com.cell.annotation.controller;

import com.cell.annotation.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;

@Controller("appController")
public class AppController {
    // 使用 @Resource 注入 Service 层 Bean（也可以用 @Autowired + @Qualifier）
    @Resource(name = "userService")
    private UserService userService;

    public String getUserInfo() {
        return userService.getUserInfo();
    }
}
