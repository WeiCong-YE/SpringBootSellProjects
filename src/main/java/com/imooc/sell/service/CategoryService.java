package com.imooc.sell.service;

import com.imooc.sell.dataoobject.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CategoryService {


    /**
     * 根据产品ｉｄ找到产品
     *
     * @param CategoryId
     * @return
     */
    ProductCategory findOne(Integer CategoryId);

    /**
     * 查找所有的产品信息
     *
     * @return
     */
    List<ProductCategory> findAll();


    /**
     * 查找所有的产品信息
     *
     * @return
     */
    Page<ProductCategory> findAll(PageRequest pageRequest);

    /**
     * 查找区间内的产品信息
     *
     * @param CategoryIds
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> CategoryIds);

    /**
     * 保存产品信息
     *
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);

    /**
     * 删除
     * @param productCategoryId
     * @return
     */
    Boolean delete(Integer productCategoryId);
}
