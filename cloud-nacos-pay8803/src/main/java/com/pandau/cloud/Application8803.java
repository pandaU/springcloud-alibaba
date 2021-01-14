package com.pandau.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.pandau.cloud.mapper")
@EnableDiscoveryClient
public class Application8803 {
    public static void main(String[] args) {
        SpringApplication.run(Application8803.class,args);
    }
}
