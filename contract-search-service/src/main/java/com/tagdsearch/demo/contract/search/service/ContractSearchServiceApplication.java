package com.tagdsearch.demo.contract.search.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tagdsearch.demo")
public class ContractSearchServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContractSearchServiceApplication.class, args);
    }
}
