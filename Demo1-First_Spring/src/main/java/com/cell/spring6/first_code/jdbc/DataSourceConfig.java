package com.cell.spring6.first_code.jdbc;

import java.util.Properties;

public class DataSourceConfig {
    private Properties config;

    public void setConfig(Properties config) {
        this.config = config;
    }

    public void print() {
        System.out.println(config.getProperty("url"));
        System.out.println(config.getProperty("username"));
        System.out.println(config.getProperty("password"));
    }
}
