package com.imooc.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PARAM_ERROR(1, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STACK_ERR(11, "商品库存不正确"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13, "订单详情不存在"),
    LACK_OF_PARAMETERS(400, "参数缺失"),
    ORDER_OWNER_ERROR(14, "该订单不属于当前用户"),
    ORDER_STATUS_ERROR(15, "订单状态不正确"),
    ORDER_CART_EMPTY_ERROR(16, "订单购物车列表为空"),
    ORDER_UPDATE_ERR(17, "订单更新失败"),
    ORDER_PAY_STATUS_ERR(18, "订单支付状态异常"),
    WECHAT_ERR(19, "微信错误");
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
