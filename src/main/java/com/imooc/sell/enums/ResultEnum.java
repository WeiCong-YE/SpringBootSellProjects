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
    WECHAT_ERR(19, "微信错误"),
    ORDER_AMOUNT_ERR(20,"订单金额错误"),
    ORDER_FINISH_ERR(21,"订单完结错误"),
    THE_ONE_IS_NOT_EXIT(22,"该数据不存在"),
    THE_ONE_IS_ADD_ERROR(23,"添加数据出错"),
    PAGE_SIZE_ERR(24,"分页参数错误"),
    THE_ONE_UPDATE_ERR(25,"数据更新失败"),
    THE_ONE_DELETE_ERROR(26,"数据删除失败"),
    THE_ONE_IS_EXIST(27,"该数据已经存在了"),
    THE_ONE_STATUS_ERR(27,"该数据状态错误");
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
