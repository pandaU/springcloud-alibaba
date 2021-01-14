package com.pandau.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;


@SpringBootApplication
@EnableZipkinServer
public class Application10081 {
    public static void main(String[] args) {
        SpringApplication.run(Application10081.class,args);
    }
}
