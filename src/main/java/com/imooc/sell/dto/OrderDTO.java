package com.imooc.sell.dto;

import com.imooc.sell.dataoobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private int orderStatus;
    private int payStatus;
    private Date createTime;
    private Date updateTime;
    private List<OrderDetail> orderDetailList;
}
