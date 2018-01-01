package com.imooc.sell.service;

import com.imooc.sell.dataoobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有在架产品
     */
    List<ProductInfo> findUpAll();

    ProductInfo findOne(String productId);

    Page<ProductInfo> findAll(Pageable pageable);

    List<ProductInfo> findByProductStatus(Integer productsStatus);

    ProductInfo save(ProductInfo productInfo);

}
