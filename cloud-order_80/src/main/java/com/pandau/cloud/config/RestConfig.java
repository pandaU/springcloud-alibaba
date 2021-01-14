package com.pandau.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class RestConfig {
    @Bean
    @LoadBalanced//rest负载均衡
    public RestTemplate getRest(){
        return new RestTemplate();
    }

}
