package com.pandau.cloud.service.impl;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.pandau.cloud.entities.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "PAYMENT-SERVICE",fallback = PayServiceCallback.class)
public interface PayService {

    @RequestMapping(value = "cloud/privoder/payDetail")
    Result detail(@RequestParam("id") Long id);

}
