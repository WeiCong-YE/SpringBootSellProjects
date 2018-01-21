package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDto;
import com.lly835.bestpay.model.PayResponse;

public interface PayService {

        PayResponse create(OrderDto orderDto);
}
