package com.imooc.sell.controller;

import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.service.OrderService;

import org.hibernate.criterion.Order;
import org.hibernate.dialect.HANAColumnStoreDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import static com.imooc.sell.enums.ResultEnum.*;

/**
 * 卖家信息
 */
@Controller
@RequestMapping("/seller")
@Api(value = "卖家接口", description = "商品管理模块")
@Slf4j
public class SellOrderController {
    @Autowired
    private OrderService mOrderService;

    @GetMapping("list")
    @ApiOperation("查询所有订单列表")
    public ModelAndView list(@ApiParam("页码") @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @ApiParam("条数") @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (page - 1 < 0 || size <= 0) {
            throw new ErrException(PAGE_SIZE_ERR.getCode(), PAGE_SIZE_ERR.getMessage());
        }
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<OrderDto> pageResult = mOrderService.findList(pageRequest);
        Map<String, Object> map = new HashMap<>();
        map.put("pageResult", pageResult);
        map.put("currPage", page);
        map.put("size", size);
        pageResult.getContent().forEach(orderDto -> log.info("[orderDto的枚举信息是{}]", orderDto.getOrderStatusEnum().getMessage()));

        return new ModelAndView("sell/list", map);
    }

    @GetMapping("cancel")
    @ApiOperation("取消订单")
    public ModelAndView cancel(@ApiParam("订单ID") @RequestParam(value = "orderId", required = false) String orderId, HashMap<String, Object> hashMap) {
        String returnUrl = "list";
        if (StringUtils.isEmpty(orderId)) {
            hashMap.put("msg", LACK_OF_PARAMETERS.getMessage());
            hashMap.put("url", returnUrl);
            return new ModelAndView("comment/error", hashMap);
        }
        OrderDto orderDto = mOrderService.findOne(orderId);
        if (orderDto == null) {
            hashMap.put("msg", ORDER_NOT_EXIST.getMessage());
            hashMap.put("url", returnUrl);
            return new ModelAndView("comment/error", hashMap);
        }
        OrderDto cancelResult = mOrderService.cancel(orderDto);
        if (cancelResult.getOrderStatusEnum().equals(OrderStatusEnum.CANCEL)) {
            // 取消成功
            hashMap.put("msg", "取消成功");
            hashMap.put("url", returnUrl);
            return new ModelAndView("comment/success", hashMap);
        } else {
            // 取消失败
            hashMap.put("msg", ORDER_NOT_EXIST.getMessage());
            hashMap.put("url", returnUrl);
            return new ModelAndView("comment/error", hashMap);
        }
    }

    /**
     * 查看订单详情
     *
     * @param orderId
     * @param hashMap
     * @return
     */
    @GetMapping("detail")
    @ApiOperation("订单详情")
    public ModelAndView detail(@ApiParam("订单id") @RequestParam(value = "orderId", required = false) String orderId, HashMap<String, Object> hashMap) {
        String returnUrl = "list";
        if (StringUtils.isEmpty(orderId)) {
            hashMap.put("msg", LACK_OF_PARAMETERS.getMessage());
            hashMap.put("url", returnUrl);
            return new ModelAndView("comment/error", hashMap);
        }
        OrderDto orderDto = mOrderService.findOne(orderId);
        if (orderDto == null) {
            hashMap.put("msg", ORDER_NOT_EXIST.getMessage());
            hashMap.put("url", returnUrl);
            return new ModelAndView("comment/error", hashMap);
        }
        hashMap.put("orderDTO", orderDto);
        return new ModelAndView("sell/detail", hashMap);
    }


    @GetMapping("finish")
    @ApiOperation("完结订单")
    public ModelAndView finish(@ApiParam("订单id") @RequestParam(value = "orderId", required = false) String orderId,
                               HashMap<String, Object> hashMap) {
        String returnUrl = "list";
        if (StringUtils.isEmpty(orderId)) {
            hashMap.put("msg", LACK_OF_PARAMETERS.getMessage());
            hashMap.put("url", returnUrl);
            return new ModelAndView("comment/error", hashMap);
        }
        OrderDto orderDto = mOrderService.findOne(orderId);
        if (orderDto == null) {
            hashMap.put("msg", ORDER_NOT_EXIST.getMessage());
            hashMap.put("url", returnUrl);
            return new ModelAndView("comment/error", hashMap);
        }
        OrderDto result;
        try {
            result = mOrderService.finish(orderDto);
        } catch (ErrException e) {
            hashMap.put("msg", e.getMsg());
            hashMap.put("url", returnUrl);
            return new ModelAndView("comment/error", hashMap);
        }
        if (!Objects.equals(result.getOrderStatus(), OrderStatusEnum.FINISH.getCode())) {
            hashMap.put("msg", ORDER_FINISH_ERR.getMessage());
            hashMap.put("url", returnUrl);
            return new ModelAndView("comment/error", hashMap);
        }
        hashMap.put("msg", "订单完结成功");
        hashMap.put("url", returnUrl);
        return new ModelAndView("comment/success", hashMap);
    }
}
