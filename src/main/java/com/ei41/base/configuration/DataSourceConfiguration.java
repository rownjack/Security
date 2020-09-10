package com.ei41.base.configuration;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.ei41.*.mapper")
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "jdbc.ds")
    public DataSource readDataSource() {
        return new DruidDataSource();
    }
}