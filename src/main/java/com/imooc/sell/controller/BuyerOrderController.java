package com.imooc.sell.controller;


import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.converter.OrderForm2OrderDTOConverter;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.utils.ResultVoUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/buyer/order")
@Slf4j
@Api(value = "买家接口", description = "订单管理模块")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     *
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @ApiOperation("创建订单")
    @PostMapping("/create")
    public ResultVO create( @Valid OrderForm orderForm, BindingResult bindingResult) {
        log.error("【接口参数】" + orderForm);
        if (bindingResult.hasErrors()) {
            throw new ErrException(ResultEnum.LACK_OF_PARAMETERS.getCode(), ResultEnum.LACK_OF_PARAMETERS.getMessage());
        }
        OrderDto orderDto = OrderForm2OrderDTOConverter.convert(orderForm);
        log.error("【convert后的结果】" + orderDto);
        OrderDto createResult = orderService.create(orderDto);
        log.error("【创建后的结果】" + createResult);
        Map<String, String> map = new HashMap<>(1);
        map.put("orderId", createResult.getOrderId());
        return ResultVoUtils.success(map);
    }

    /**
     * 查询订单接口
     *
     * @param openId
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("查询订单接口")
    @GetMapping("/list")
    public ResultVO list(@ApiParam("openId") @RequestParam(value = "openId", required = false) String openId,
                         @ApiParam("页码") @RequestParam(value = "page", defaultValue = "0") Integer page,
                         @ApiParam("每次多少条") @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openId)) {
            throw new ErrException(ResultEnum.LACK_OF_PARAMETERS.getCode(), ResultEnum.LACK_OF_PARAMETERS.getMessage());
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDto> orderDtoPage = orderService.findList(openId, pageRequest);
        return ResultVoUtils.success(orderDtoPage.getContent());
    }


    /**
     * 获取订单详情
     *
     * @param openid
     * @param orderId
     * @return
     */
    @ApiOperation("获取订单详情")
    @GetMapping("/detail")
    public ResultVO detail(@ApiParam("openId") @RequestParam(value = "openId", required = false) String openid,
                           @ApiParam("订单Id") @RequestParam(value = "orderId", required = false) String orderId) {
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)) {
            throw new ErrException(ResultEnum.LACK_OF_PARAMETERS.getCode(), ResultEnum.LACK_OF_PARAMETERS.getMessage());
        }
        OrderDto orderDto = buyerService.findOrderOne(openid, orderId);
        return ResultVoUtils.success(orderDto);
    }

    /**
     * 取消订单
     *
     * @param openId
     * @param orderId
     * @return
     */
    @ApiOperation("取消订单")
    @PostMapping("/cancel")
    public ResultVO cancel(@ApiParam("openId") @RequestParam(value = "openId", required = false) String openId,
                           @ApiParam("订单Id") @RequestParam(value = "orderId", required = false) String orderId) {
        if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(orderId)) {
            throw new ErrException(ResultEnum.LACK_OF_PARAMETERS.getCode(), ResultEnum.LACK_OF_PARAMETERS.getMessage());
        }
        buyerService.cancelOrder(openId, orderId);
        return ResultVoUtils.success();
    }


    @PostMapping("/finish")
    @ApiIgnore
    public ResultVO finish(@ApiParam("openId") @RequestParam(value = "openId", required = false) String openId,
                           @ApiParam("订单Id") @RequestParam(value = "orderId", required = false) String orderId) {
        if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(orderId)) {
            throw new ErrException(ResultEnum.LACK_OF_PARAMETERS.getCode(), ResultEnum.LACK_OF_PARAMETERS.getMessage());
        }
        OrderDto orderDto = buyerService.finishOrder(openId, orderId);
        return ResultVoUtils.success(orderDto);
    }

    @PostMapping("/pay")
    @ApiIgnore
    public ResultVO pay(@ApiParam("openId") @RequestParam(value = "openId", required = false) String openId,
                        @ApiParam("订单Id") @RequestParam(value = "orderId", required = false) String orderId) {
        if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(orderId)) {
            throw new ErrException(ResultEnum.LACK_OF_PARAMETERS.getCode(), ResultEnum.LACK_OF_PARAMETERS.getMessage());
        }
        OrderDto orderDto = buyerService.payOrder(openId, orderId);
        return ResultVoUtils.success(orderDto);
    }
}
