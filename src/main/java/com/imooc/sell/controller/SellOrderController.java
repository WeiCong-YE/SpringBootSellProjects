package com.imooc.sell.controller;

import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import static com.imooc.sell.enums.ResultEnum.PAGE_SIZE_ERR;

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
}
