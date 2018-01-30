package com.imooc.sell.form;

import lombok.Data;

@Data
public class ProductCategoryAddForm {
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
}
