package com.pandau.cloud.service;

//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
//@FeignClient(value = "seata-order")
public interface OrderService {
    @RequestMapping("create")
    void create(String userId, String commodityCode, int orderCount);
}
