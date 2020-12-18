package com.pandau.cloud.service;

import com.pandau.cloud.entities.PayInfo;

public interface PayService {
    int createPay(PayInfo info);

    PayInfo  getDetail(Long id );
}
