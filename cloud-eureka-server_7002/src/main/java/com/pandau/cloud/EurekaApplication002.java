package com.pandau.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication002.class,args);
    }
}
