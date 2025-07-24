package com.eorionsolution.camunda.plugin.enhancedjobexecutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@SpringBootApplication
@Configuration
public class EnhancedJobExecutorTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnhancedJobExecutorTestApplication.class, args);
    }

    @Bean
    @Primary
    @DependsOn("liquibase")
    public PlatformTransactionManager primaryTransactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

}
