package com.imooc.sell.VO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductVo {

    /**
     * 类目名字
     */
    @JsonProperty("name")
    private String categoryName;
    /**
     * 类别
     */
    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOS;
}
