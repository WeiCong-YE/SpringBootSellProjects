package com.imooc.sell.service.impl;

import com.imooc.sell.dataoobject.OrderDetail;
import com.imooc.sell.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    private static final String sBugyerOpenId = "21312312", sBuyerId = "1514907566672405361";

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("Ly");
        orderDto.setBuyerAddress("学院");
        orderDto.setBuyerPhone("23423423423");
        orderDto.setBuyerOpenid(sBugyerOpenId);

        // 购物车
        List<OrderDetail> list = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("11");
        o1.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductQuantity(10);
        o2.setProductId("132");
        list.add(o1);
        list.add(o2);
        orderDto.setOrderDetailList(list);
        OrderDto result = orderService.create(orderDto);
        log.info("创建订单结果---" + result);
        Assert.assertNotNull(result);
    }


    @Test
    public void findOne() throws Exception {
        OrderDto orderDto = orderService.findOne(sBuyerId);
        log.info("[订单查询的结果]--"+ orderDto);
        Assert.assertNotNull(orderDto);
    }
}