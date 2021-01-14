package com.pandau.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
//@RibbonClient(name = "PAYMENT-SERVICE",configuration = MyRule.class) ribbon负载均衡
public class Order8801Application {
    public static void main(String[] args) {
       SpringApplication.run(Order8801Application.class, args);
    }
}
