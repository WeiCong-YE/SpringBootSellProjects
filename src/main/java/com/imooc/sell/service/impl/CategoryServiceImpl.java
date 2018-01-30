package com.imooc.sell.service.impl;

import com.imooc.sell.dataoobject.ProductCategory;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.repository.ProductCategoryRepository;
import com.imooc.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory findOne(Integer CategoryId) {
        ProductCategory result = productCategoryRepository.findOne(CategoryId);
        if (result == null) {
            throw new ErrException(ResultEnum.THE_ONE_IS_NOT_EXIT.getCode(), ResultEnum.THE_ONE_IS_NOT_EXIT.getMessage());
        }
        return result;
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public Page<ProductCategory> findAll(PageRequest pageRequest) {
        return productCategoryRepository.findAll(pageRequest);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> CategoryIds) {
        return productCategoryRepository.findByCategoryTypeIn(CategoryIds);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        ProductCategory result = productCategoryRepository.save(productCategory);
        if (result == null) {
            throw new ErrException(ResultEnum.THE_ONE_UPDATE_ERR.getCode(), ResultEnum.THE_ONE_UPDATE_ERR.getMessage());
        }
        return result;
    }

    @Override
    public Boolean delete(Integer productCategoryId) {
//        1.  先找到数据库中有没有这个数据
//        2.  进行删除
//        3.  判断是否删除成功
        ProductCategory productCategory = findOne(productCategoryId);
        if (productCategory == null) {
            throw new ErrException(ResultEnum.THE_ONE_IS_NOT_EXIT.getCode(),
                    ResultEnum.THE_ONE_IS_NOT_EXIT.getMessage());
        }
        productCategoryRepository.delete(productCategoryId);
        ProductCategory result = findOne(productCategoryId);
        return result == null;
    }
}
