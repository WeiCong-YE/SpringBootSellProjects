package com.imooc.sell.form;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductAddForm {
    private String productId;
    /**
     * 商品名字
     */
    private String productName;
    /**
     * 商品价格
     */
    private BigDecimal productPrice;
    /**
     * 库存
     */
    private Integer productStock;
    /**
     * 描述
     */
    private String productDescription;
    /**
     * 图片
     */
    private String productIcon;
    /**
     * 类目编码
     */
    private Integer categoryType;
}
