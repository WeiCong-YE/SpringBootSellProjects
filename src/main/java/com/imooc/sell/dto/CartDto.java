package com.imooc.sell.dto;

import lombok.Data;

/**
 * 购物车对象
 */
@Data
public class CartDto {

    private String productId;
    private Integer productQuantity;

    public CartDto() {
    }

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
