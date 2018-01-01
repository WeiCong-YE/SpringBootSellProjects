package com.imooc.sell.service;

import com.imooc.sell.dataoobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    ProductCategory findOne(Integer CategoryId);

    List<ProductCategory> findAll();


    List<ProductCategory> findByCategoryTypeIn(List<Integer> CategoryIds);

    ProductCategory save(ProductCategory productCategory);


}
