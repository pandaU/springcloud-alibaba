package com.pandau.cloud;


import org.springframework.boot.Banner;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.PrintStream;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
//@RibbonClient(name = "PAYMENT-SERVICE",configuration = MyRule.class) ribbon负载均衡
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(OrderApplication.class);
        application.setBannerMode(Mode.CONSOLE);
        application.setBanner(new ResourceBanner(new ClassPathResource("banner.txt")));
        application.run(args);
    }
}
