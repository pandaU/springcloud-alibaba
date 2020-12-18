package com.pandau.cloud.controller;

import com.pandau.cloud.entities.PayInfo;
import com.pandau.cloud.entities.Result;
import com.pandau.cloud.service.PayService;
import com.pandau.cloud.util.CommResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping(value = "cloud/privoder/")
public class PayController {
    @Autowired
    private  PayService payService;
    @Value("${server.port}")
    private  String port;

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
    Result detail(Long id) {
        PayInfo result = payService.getDetail(id);
        if (null != result) {
            return CommResult.ok(result,"SUCCESS  port:"+port);
        }
        return CommResult.error(405, "查询失败");
    }
}
