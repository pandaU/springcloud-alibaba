package com.pandau.cloud.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.pandau.cloud.entities.Result;
import com.pandau.cloud.service.impl.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@Slf4j
@RequestMapping(value = "cloud/cousmer/")
public class OrderController {

   // private final OrderService payService;
    @Autowired
    private  RestTemplate restTemplate;
    @Autowired
    private PayService payService;
    private static final String PAY_URL="http://PAYMENT-SERVICE/";

    @RequestMapping(value = "getPay")
    Object pay(Long id) {
        Result result= restTemplate.getForObject(PAY_URL+"cloud/privoder/payDetail?id="+id,Result.class);
        return result;
    }

    @RequestMapping(value = "getByFeight")
    Object getByFeight(Long id) {
        return payService.detail(id);
    }
}
