package com.cell.aop.security_service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.cell.aop.security_service")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SecurityConfiguration {
}
