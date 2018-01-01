package com.imooc.sell.repository;


import com.imooc.sell.dataoobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOne() {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        System.out.print(productCategory.toString());
    }

    @Test
    @Transactional
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("11111");
        productCategory.setCategoryType(3);
        ProductCategory res = productCategoryRepository.save(productCategory);
        System.out.print(res.toString());
        Assert.assertNotNull(res);
    }

    @Test
    public void findBy() {
        List<Integer> searchKey = Arrays.asList(6,7);
        List<ProductCategory> list = productCategoryRepository.findByCategoryTypeIn(searchKey);
        System.out.print(list.toString());
        Assert.assertNotEquals(0,list.size());
    }
}