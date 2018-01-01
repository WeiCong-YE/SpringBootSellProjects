package com.imooc.sell.repository;

import com.imooc.sell.dataoobject.OrderMaster;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("揭阳");
        orderMaster.setBuyerName("Ly");
        orderMaster.setBuyerOpenid("o_242342");
        orderMaster.setOrderAmount(new BigDecimal(9898));
        orderMaster.setOrderId("2323");
        orderMaster.setBuyerPhone("18819450507");
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster res = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(res);

    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid("o_1223213", pageRequest);
        Assert.assertNotEquals(result.getTotalElements(), 0);
    }
}