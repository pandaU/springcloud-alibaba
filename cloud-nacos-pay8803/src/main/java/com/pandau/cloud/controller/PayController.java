package com.pandau.cloud.controller;


import com.pandau.cloud.entities.PayInfo;
import com.pandau.cloud.entities.Result;
import com.pandau.cloud.service.PayService;
import com.pandau.cloud.util.CommResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@Slf4j
@RequestMapping(value = "cloud/privoder/")
public class PayController {
   private static AtomicInteger A = new AtomicInteger(0);
    @Autowired
    private  PayService payService;
    @Value("${server.port}")
    private  String port;
    @Autowired
    private DiscoveryClient client;
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    Result pay(@RequestBody PayInfo info) {
        int result = payService.createPay(info);
        log.info("流水号：{}", info.getSerial());
        if (result > 0) {
            return CommResult.ok(info.getId(),"SUCCESS  port:"+port);
        }
        return CommResult.error(405, "支付失败");
    }

    @RequestMapping(value = "payDetail")
    Result detail(@RequestParam("id") Long id) {
        if (id < 0){
            throw new RuntimeException();
        }
        PayInfo result = payService.getDetail(id);
        if (null != result) {
            return CommResult.ok(result,"SUCCESS  port:"+port);
        }
        return CommResult.error(405, "查询失败");
    }
    @RequestMapping(value = "discovers")
    Result discovers() {
        List<String> services = client.getServices();
        return CommResult.ok(services, "SUCCESS");
    }
    Result  gobalCallback(){
        return CommResult.error(455, "服务降级了呜呜呜");
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(8);
        List<String> strings = Arrays.asList(new String[]{"service1", "service2", "service3"});
        for (int i = 0; i <9 ; i++) {
            pool.submit(()->{System.out.println(round(strings));});
        }



    }
    private static int  round(List list){
        for (;;){
            int current = A.get();
            int index =(current + 1) % list.size();
            if (A.compareAndSet(current,index)){
                return index;
            }
        }
    }
}
