package com.imooc.sell.service.impl;

import com.imooc.sell.config.WechatPayConf;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import com.imooc.sell.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private WechatPayConf mWechatPayConf;

    @Autowired
    private OrderService mOrderService;
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

    @Override
    public PayResponse notify(String notifyData) {
        //1. 验证签名
        //2. 支付的状态
        //3. 支付金额
        //4. 支付人(下单人 == 支付人)


        PayResponse payResponse = mWechatPayConf.mBestPayService().asyncNotify(notifyData);
        OrderDto orderDto = mOrderService.findOne(payResponse.getOrderId());
        if (orderDto == null) {
            throw new ErrException(ResultEnum.ORDER_NOT_EXIST.getCode(),
                    ResultEnum.ORDER_NOT_EXIST.getMessage());
        }
        if (!MathUtil.equals(orderDto.getOrderAmount().doubleValue(),
                payResponse.getOrderAmount())) {
            throw new ErrException(ResultEnum.ORDER_AMOUNT_ERR.getCode(), ResultEnum.ORDER_AMOUNT_ERR.getMessage());
        }
        mOrderService.paid(orderDto);
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDto orderDto) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDto.getOrderId());
        refundRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        return mWechatPayConf.mBestPayService().refund(refundRequest);
    }
}
