package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {

    @Autowired
    private OrderService mOrderService;

    @Autowired
    private PayService mPayService;

    @Test
    public void create() {
        OrderDto orderDto = mOrderService.findOne("1515329812924951949");
        mPayService.create(orderDto);
    }
}