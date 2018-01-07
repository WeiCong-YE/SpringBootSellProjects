package com.imooc.sell.service.impl;

import com.imooc.sell.converter.OrderMaster2OrderDTOConverter;
import com.imooc.sell.dataoobject.OrderDetail;
import com.imooc.sell.dataoobject.OrderMaster;
import com.imooc.sell.dataoobject.ProductInfo;
import com.imooc.sell.dto.CartDto;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.BeanUtils;
import com.imooc.sell.utils.KeysUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static com.imooc.sell.enums.ResultEnum.ORDER_CART_EMPTY_ERROR;
import static com.imooc.sell.enums.ResultEnum.ORDER_UPDATE_ERR;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
//        总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        String orderId = KeysUtils.getUniqueKey();
//        1. 查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            log.error("【查找商品信息---】" + orderDetail.getProductId());
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new ErrException(ResultEnum.PRODUCT_NOT_EXIST.getCode(), ResultEnum.PRODUCT_NOT_EXIST.getMessage());
            }
            //        2.计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            // 订单详情入库
            orderDetail.setDetailId(KeysUtils.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyNonNullProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }

//        3.写入订单数据库（orderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        log.info("【复制前的orderMaster】" + orderMaster.toString());
        log.info("【复制前的orderDto】" + orderDto.toString());
        BeanUtils.copyNonNullProperties(orderDto, orderMaster);
        log.info("【复制后的bean】" + orderMaster.toString());
        orderMasterRepository.save(orderMaster);
//        4.扣库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(),
                e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDtoList);
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new ErrException(ResultEnum.PRODUCT_NOT_EXIST.getCode(),
                    ResultEnum.PRODUCT_NOT_EXIST.getMessage());
        }
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetails)) {
            throw new ErrException(ResultEnum.ORDER_DETAIL_NOT_EXIST.getCode(),
                    ResultEnum.ORDER_DETAIL_NOT_EXIST.getMessage());
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyNonNullProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetails);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDto> orderDtoList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<>(orderDtoList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new ErrException(ResultEnum.ORDER_STATUS_ERROR.getCode(), ResultEnum.ORDER_STATUS_ERROR.getMessage());
        }
        // 修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyNonNullProperties(orderDto, orderMaster);
        log.error("【取消订单 复制后的】=" + orderMaster.toString());
        OrderMaster orderMasterResult = orderMasterRepository.save(orderMaster);
        log.error("【取消订单 保存后的】=" + orderMasterResult.toString());

        // 返回库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            throw new ErrException(ORDER_CART_EMPTY_ERROR.getCode(), ORDER_CART_EMPTY_ERROR.getMessage());
        }
        List<CartDto> cartDtos = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDtos);

        // 如果已经付款了 那么要退款
        if (orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            // TODO: 2018/1/7   退款
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //  判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new ErrException(ResultEnum.ORDER_STATUS_ERROR.getCode(), ResultEnum.ORDER_STATUS_ERROR.getMessage());
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        BeanUtils.copyNonNullProperties(orderDto, orderMaster);
        OrderMaster updateOrderMaster = orderMasterRepository.save(orderMaster);
        if (updateOrderMaster == null) {
            throw new ErrException(ResultEnum.ORDER_UPDATE_ERR.getCode(), ORDER_UPDATE_ERR.getMessage());
        }
        return orderDto;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        // 判断订单状态
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new ErrException(ResultEnum.ORDER_STATUS_ERROR.getCode(), ResultEnum.ORDER_STATUS_ERROR.getMessage());
        }
        // 判断支付状态
        if (!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            throw new ErrException(ResultEnum.ORDER_PAY_STATUS_ERR.getCode(), ResultEnum.ORDER_PAY_STATUS_ERR.getMessage());
        }
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyNonNullProperties(orderDto, orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result == null) {
            throw new ErrException(ResultEnum.ORDER_UPDATE_ERR.getCode(), ORDER_UPDATE_ERR.getMessage());
        }
        // 修改支付状态
        return orderDto;
    }
}
