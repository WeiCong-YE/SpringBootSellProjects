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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
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
    @PostMapping("/create")
    public ResultVO create(@RequestBody @Valid OrderForm orderForm, BindingResult bindingResult) {
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
     * 查询订单
     *
     * @param openId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultVO list(@RequestParam(value = "openId", required = false) String openId,
                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
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
    @GetMapping("/detail")
    public ResultVO detail(@RequestParam(value = "openid", required = false) String openid,
                           @RequestParam(value = "orderId", required = false) String orderId) {
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)) {
            throw new ErrException(ResultEnum.LACK_OF_PARAMETERS.getCode(), ResultEnum.LACK_OF_PARAMETERS.getMessage());
        }
        OrderDto orderDto = buyerService.findOrderOne(openid, orderId);
        return ResultVoUtils.success(orderDto);
    }

    /**
     * 取消订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam(value = "openid", required = false) String openid,
                           @RequestParam(value = "orderId", required = false) String orderId) {
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)) {
            throw new ErrException(ResultEnum.LACK_OF_PARAMETERS.getCode(), ResultEnum.LACK_OF_PARAMETERS.getMessage());
        }
        buyerService.cancelOrder(openid, orderId);
        return ResultVoUtils.success();
    }

}
