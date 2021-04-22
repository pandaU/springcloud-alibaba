package com.pandau.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*@EnableFeignClients
@EnableDiscoveryClient*/
public class SeataApp {
    public static void main(String[] args) {
        SpringApplication.run(SeataApp.class,args);
    }
}
