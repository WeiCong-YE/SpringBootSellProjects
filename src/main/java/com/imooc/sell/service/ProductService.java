package com.imooc.sell.service;

import com.imooc.sell.dataoobject.ProductInfo;
import com.imooc.sell.dto.CartDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品的service
 */
public interface ProductService {
    /**
     * 查询所有在架产品
     */
    List<ProductInfo> findUpAll();

    ProductInfo findOne(String productId);

    Page<ProductInfo> findAll(Pageable pageable);

    List<ProductInfo> findByProductStatus(Integer productsStatus);

    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     *
     * @param cartDtoList
     */
    void increaseStock(List<CartDto> cartDtoList);

    /**
     * 减库存
     *
     * @param cartDtoList
     */
    void decreaseStock(List<CartDto> cartDtoList);

    /**
     * 下架商品
     *
     * @param id
     * @return
     */
    ProductInfo offSale(String id);

    /**
     * 上架商品
     *
     * @param id
     * @return
     */
    ProductInfo onSale(String id);
}
