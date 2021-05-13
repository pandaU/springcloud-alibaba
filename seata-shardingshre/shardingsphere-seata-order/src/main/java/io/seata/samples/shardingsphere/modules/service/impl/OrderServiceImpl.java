package io.seata.samples.shardingsphere.modules.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import io.seata.samples.shardingsphere.modules.entity.OrderEntity;
import io.seata.samples.shardingsphere.modules.mapper.OrderMapper;
import io.seata.samples.shardingsphere.modules.service.IOrderService;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements IOrderService {

    @Override
    public String insertOrder(OrderEntity orderEntity) {
        System.out.println("XID:"+ RootContext.getXID());
        baseMapper.insert(orderEntity);
        return "ok";
    }
}
