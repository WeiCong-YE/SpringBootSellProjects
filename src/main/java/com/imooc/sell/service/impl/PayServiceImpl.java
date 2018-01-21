package com.imooc.sell.service.impl;

import com.imooc.sell.config.WechatPayConf;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.service.PayService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private WechatPayConf mWechatPayConf;

    private static final String ORDER_NAME = "给焕爷钱还需要理由吗？";

    @Override
    public PayResponse create(OrderDto orderDto) {
        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setOrderId(orderDto.getOrderId());
        payRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        payRequest.setOpenid(orderDto.getBuyerOpenid());
        return mWechatPayConf.mBestPayService().pay(payRequest);
    }
}
