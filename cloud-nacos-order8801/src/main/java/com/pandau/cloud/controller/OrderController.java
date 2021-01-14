package com.pandau.cloud.controller;


import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.pandau.cloud.entities.Result;
import com.pandau.cloud.handler.CustomerBlockHandler;
import com.pandau.cloud.service.impl.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;


@RestController
@Slf4j
@RequestMapping(value = "cloud/cousmer/")
@RefreshScope
public class OrderController {
    @Value("${user.name}")
    private String data;
   // private final OrderService payService;
    @Autowired
    private  RestTemplate restTemplate;
    @Autowired
    private PayService payService;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    private static final String PAY_URL="http://payment-service/";

    @RequestMapping(value = "getPay")
    Object pay(Long id) {
        Result result= restTemplate.getForObject(PAY_URL+"cloud/privoder/payDetail?id="+id,Result.class);
        return result;
    }

    @RequestMapping(value = "getByFeight")
    Object getByFeight(Long id) {
        return payService.detail(id);
    }
    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {
        ServiceInstance order_8801 = loadBalancerClient.choose("order_8801");
        return "Hello Nacos Discovery " + data +order_8801.getServiceId()+order_8801.getPort();
    }
    @GetMapping(value = "/test")
    @SentinelResource(value = "test",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "myBlockHandler1")
    public String test() {
        UrlCleaner cleaner = WebCallbackManager.getUrlCleaner();
        return cleaner.clean("http://172.16.8.74:8801/cloud/cousmer/echo/1213");
    }
    public  String execptionHander1(BlockException exception){
        return "test 报错啦  哈哈哈哈哈哈";
    }
}
