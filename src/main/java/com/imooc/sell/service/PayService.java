package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDto;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

public interface PayService {
    /**
     * 创建订单
     *
     * @param orderDto
     * @return
     */
    PayResponse create(OrderDto orderDto);

    /**
     * 异步回调支付信息
     *
     * @param notifyData
     * @return
     */
    PayResponse notify(String notifyData);

    /**
     * 退款
     *
     * @param orderDto
     */
    RefundResponse refund(OrderDto orderDto);
}
