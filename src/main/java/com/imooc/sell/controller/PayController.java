package com.imooc.sell.controller;


import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Result;

import lombok.extern.slf4j.Slf4j;

import static com.imooc.sell.enums.ResultEnum.LACK_OF_PARAMETERS;
import static com.imooc.sell.enums.ResultEnum.ORDER_NOT_EXIST;

@Controller
@RequestMapping("pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;


    @Autowired
    private PayService mPayService;

    /**
     * 创建订单
     *
     * @param orderId
     * @param returnUrl
     * @return
     */
    @GetMapping("/create")
    public ModelAndView create(@RequestParam(value = "orderId", required = false) String orderId,
                               @RequestParam(value = "returnUrl", required = false) String returnUrl) {
        log.info("【orderId={},returnUrl={}】", orderId, returnUrl);
        if (StringUtils.isEmpty(orderId) || StringUtils.isEmpty(returnUrl)) {
            throw new ErrException(LACK_OF_PARAMETERS.getCode(), LACK_OF_PARAMETERS.getMessage());
        }
        OrderDto orderDto = orderService.findOne(orderId);
        if (orderDto == null) {
            throw new ErrException(ORDER_NOT_EXIST.getCode(), ORDER_NOT_EXIST.getMessage());
        }
        PayResponse payResponse = mPayService.create(orderDto);
        Map<String,Object> map=new HashMap<>();
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        return new ModelAndView("pay/create",map);
    }
}
