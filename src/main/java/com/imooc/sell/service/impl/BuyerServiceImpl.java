package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOrderOne(String openId, String orderId) {
        return doCheckOrderOwner(openId, orderId);
    }

    @Override
    public OrderDto cancelOrder(String openId, String orderId) {
        OrderDto orderDto = doCheckOrderOwner(openId, orderId);
        return orderService.cancel(orderDto);
    }

    @Override
    public OrderDto finishOrder(String openId, String orderId) {
        OrderDto orderDto = doCheckOrderOwner(openId, orderId);
        return orderService.finish(orderDto);
    }

    @Override
    public OrderDto payOrder(String openId, String orderId) {
        OrderDto orderDto = doCheckOrderOwner(openId, orderId);
        return orderService.paid(orderDto);
    }


    /**
     * 确认订单的所属
     *
     * @param openId
     * @param orderId
     * @return
     */
    private OrderDto doCheckOrderOwner(String openId, String orderId) {
        OrderDto orderDto = orderService.findOne(orderId);
        if (orderDto == null) {
            return null;
        }
        if (!orderDto.getBuyerOpenid().equals(openId)) {
            throw new ErrException(ResultEnum.ORDER_OWNER_ERROR.getCode(),
                    ResultEnum.ORDER_OWNER_ERROR.getMessage());
        }
        return orderDto;
    }
}
