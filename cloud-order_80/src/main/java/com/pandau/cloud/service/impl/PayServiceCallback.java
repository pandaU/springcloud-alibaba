package com.pandau.cloud.service.impl;

import com.pandau.cloud.entities.Result;
import com.pandau.cloud.util.CommResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class PayServiceCallback implements PayService{
    @Override
    public Result detail(Long id) {
        return CommResult.error(300,"服务降级了");
    }
}
