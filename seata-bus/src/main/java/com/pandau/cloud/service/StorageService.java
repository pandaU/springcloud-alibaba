package com.pandau.cloud.service;

//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
//@FeignClient(value = "seata-storage")
public interface StorageService {
    @RequestMapping("deduct")
    void deduct(String commodityCode, int orderCount);
}
