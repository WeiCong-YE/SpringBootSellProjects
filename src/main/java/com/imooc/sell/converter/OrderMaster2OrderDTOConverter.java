package com.imooc.sell.converter;


import com.imooc.sell.dataoobject.OrderMaster;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class OrderMaster2OrderDTOConverter {

    /**
     * orderMaster 转 OrderDto
     *
     * @param orderMaster
     * @return
     */
    private static OrderDto convert(OrderMaster orderMaster) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyNonNullProperties(orderMaster, orderDto);
        return orderDto;
    }

    /**
     * orderMaster 转 orderDto  list
     *
     * @param orderMasters
     * @return
     */
    public static List<OrderDto> convert(List<OrderMaster> orderMasters) {
        return orderMasters
                .stream()
                .map(OrderMaster2OrderDTOConverter::convert)
                .collect(Collectors.toList());
    }
}
