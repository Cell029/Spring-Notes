package org.myspringframwork.core;

public interface ApplicationContext {

    // 根据 Bean 的名称获取对应的 Bean 对象
    Object getBean(String beanName);

}
