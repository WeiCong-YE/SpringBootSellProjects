package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDto;
import org.springframework.stereotype.Service;

/**
 * 买家
 */
public interface BuyerService {

    OrderDto findOrderOne(String openId, String orderId);

    OrderDto cancelOrder(String openId, String orderId);

    OrderDto finishOrder(String openId, String orderId);

    OrderDto payOrder(String openId, String orderId);
}
