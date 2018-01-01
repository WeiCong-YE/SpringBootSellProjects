package com.imooc.sell.repository;

import com.imooc.sell.dataoobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12");
        orderDetail.setOrderId("o_242342");
        orderDetail.setProductIcon("https://imgsa.baidu.com/news/q%3D100/sign=8ed40ec242540923ac69677ea259d1dc/8694a4c27d1ed21b0d91de04a66eddc450da3ff2.jpg");
        orderDetail.setProductId("11");
        orderDetail.setProductPrice(new BigDecimal(232));
        orderDetail.setProductQuantity(121);
        orderDetail.setProductName("测试添加");
        OrderDetail res = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(res);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> resultList = orderDetailRepository.findByOrderId("o_242342");
        Assert.assertNotEquals(resultList.size(),0);
    }
}