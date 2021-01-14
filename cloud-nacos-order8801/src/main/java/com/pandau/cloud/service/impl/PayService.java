package com.pandau.cloud.service.impl;



import com.pandau.cloud.entities.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "payment-service")
public interface PayService {

    @RequestMapping(value = "cloud/privoder/payDetail")
    Result detail(@RequestParam("id") Long id);

}
