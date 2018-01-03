package com.imooc.sell.service.impl;

import com.imooc.sell.dataoobject.OrderDetail;
import com.imooc.sell.dataoobject.OrderMaster;
import com.imooc.sell.dataoobject.ProductInfo;
import com.imooc.sell.dto.CartDto;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.KeysUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

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
                throw new ErrException(ResultEnum.PRODUCT_NOT_EXIST.getCode(),ResultEnum.PRODUCT_NOT_EXIST.getMessage());
            }
            //        2.计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            // 订单详情入库
            orderDetail.setDetailId(KeysUtils.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }

//        3.写入订单数据库（orderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        log.info("【复制前的orderMaster】"+orderMaster.toString());
        log.info("【复制前的orderDto】"+orderDto.toString());
        com.imooc.sell.utils.BeanUtils.copyNonNullProperties(orderDto, orderMaster);
        log.info("【复制后的bean】"+orderMaster.toString());
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
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetails);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}
