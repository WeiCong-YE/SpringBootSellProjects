package com.imooc.sell.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum {
    CANCEL(2, "取消"),
    FINISH(1, "完结"),
    NEW(0, "新订单");


    private Integer code;
    private String message;

    OrderStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
