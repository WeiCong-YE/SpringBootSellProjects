package com.imooc.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductInfoVO {
    @JsonProperty("id")
    private String productId;
    /**
     * 商品名字
     */
    @JsonProperty("name")
    private String productName;
    /**
     * 商品价格
     */
    @JsonProperty("price")
    private BigDecimal productPrice;

    /**
     * 描述
     */
    @JsonProperty("description")
    private String productDescription;
    /**
     * 图片
     */
    @JsonProperty("icon")
    private String productIcon;
}
