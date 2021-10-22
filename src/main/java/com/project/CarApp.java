package com.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.project.mapper")
@EnableTransactionManagement // 注解事务


public class CarApp {

    public static void main(String[] args) {
        SpringApplication.run(CarApp.class , args);
    }

}
