package com.imooc.sell.converter;

import com.alibaba.fastjson.JSON;
import com.imooc.sell.dataoobject.OrderDetail;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDto convert(OrderForm orderForm){
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> list= JSON.parseArray(orderForm.getItems(),OrderDetail.class);
        orderDto.setOrderDetailList(list);
        log.error("【list】"+list.toString());
        return orderDto;
    }
}
