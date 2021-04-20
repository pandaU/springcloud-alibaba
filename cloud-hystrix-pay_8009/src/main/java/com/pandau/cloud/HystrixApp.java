package com.pandau.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class HystrixApp {
    public static void main(String[] args) {
        SpringApplication.run(HystrixApp.class,args);
    }
}
