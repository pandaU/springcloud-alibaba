import io.seata.samples.shardingsphere.ShardingsphereSeataOederApplication;
import io.seata.samples.shardingsphere.modules.entity.OrderEntity;
import io.seata.samples.shardingsphere.modules.service.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingsphereSeataOederApplication.class)
public class Tests {
    @Autowired
    IOrderService iOrderService;
    @Test
    public void contextLoads() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(123);
        orderEntity.setStatus("seata");
        orderEntity.setUserId(123);
        iOrderService.insertOrder(orderEntity);
    }

}