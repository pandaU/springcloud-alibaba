package com.pandau.cloud.service.impl;

import com.pandau.cloud.entities.PayInfo;
import com.pandau.cloud.mapper.PayMapper;
import com.pandau.cloud.service.PayService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("payService")
@AllArgsConstructor
public class PayServiceImpl implements PayService {
       private final PayMapper payMapper;
       @Override
       @Transactional
       public int createPay(PayInfo info){
          return payMapper.create(info);
       }

       @Override
       public PayInfo getDetail(Long id) {
          return payMapper.getById(id);
       }
}
