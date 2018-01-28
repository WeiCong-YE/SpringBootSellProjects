package com.imooc.sell.service.impl;

import com.imooc.sell.dataoobject.FoodType;
import com.imooc.sell.form.FoodAddForm;
import com.imooc.sell.form.FoodUpdateForm;
import com.imooc.sell.service.FoodTypeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodTypeServiceImplTest {

    @Autowired
    private FoodTypeService foodTypeService;

    @Test
    public void findAll() throws Exception {
        List<FoodType> resultList = foodTypeService.findAll();
        Assert.assertNotNull(resultList);
    }

    @Test
    public void addFoodType() throws Exception {
        FoodAddForm foodAddForm = new FoodAddForm();
        foodAddForm.setImg("http://img.blog.csdn.net/20150311143851689?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc3VuaWFuX2xvdmUyMDE1/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center");
        foodAddForm.setName("测试");
        foodAddForm.setSortFlag(1);
        FoodType result = foodTypeService.addFoodType(foodAddForm);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateFoodType() throws Exception {
        FoodUpdateForm foodUpdateForm = new FoodUpdateForm();
        foodUpdateForm.setId(1);
        foodUpdateForm.setName("测试11111");
        foodUpdateForm.setSortFlag(1);
        FoodType result = foodTypeService.updateFoodType(foodUpdateForm);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteFoodType() throws Exception {
        boolean isSuccess=foodTypeService.deleteFoodType(2);
        Assert.assertTrue(isSuccess);
    }

}