package io.seata.samples.shardingsphere.modules.mock;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.seata.samples.shardingsphere.modules.entity.OrderEntity;
import io.seata.samples.shardingsphere.modules.mapper.OrderMapper;
import io.seata.samples.shardingsphere.modules.service.IOrderService;
import org.springframework.boot.autoconfigure.session.JdbcSessionDataSourceInitializer;

public class IOrderServiceMockImpl extends ServiceImpl<OrderMapper, OrderEntity> implements IOrderService {
    @Override
    public String insertOrder(OrderEntity orderEntity) {
        System.out.println("OrderService RpcException");
        System.out.println("haha");
        return "fail";
    }
}
