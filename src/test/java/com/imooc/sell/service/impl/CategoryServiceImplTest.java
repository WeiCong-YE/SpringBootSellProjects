package com.imooc.sell.service.impl;

import com.imooc.sell.dataoobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(productCategory.getCategoryId(),new Integer(1));
    }

    @Test
    public void findAll() {
        List<ProductCategory>resList =categoryService.findAll();
        Assert.assertNotEquals(Integer.valueOf(resList.size()),Integer.valueOf(0));
    }

    @Test
    public void findByCategoryTypeIn() {
    }

    @Test
    public void save() {
    }
}