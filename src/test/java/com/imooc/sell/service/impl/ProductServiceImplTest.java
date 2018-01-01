package com.imooc.sell.service.impl;

import com.imooc.sell.dataoobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {


    @Autowired
    private ProductServiceImpl productInfoService;
    @Test
    public void findOne() {
      ProductInfo productInfo= productInfoService.findOne("11");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findAll() {
        PageRequest pageRequest=new PageRequest(0,2);
        Page<ProductInfo> list=productInfoService.findAll(pageRequest);
       System.out.print(list.getTotalElements());
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> list=productInfoService.findByProductStatus(1);
        Assert.assertNotEquals(list.size(),0);
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo(
               "132", "测试添加", new BigDecimal(96.4),
                12,"描述","http://s0.2mdn.net/5585042/17_728x90_PS_CN_2017_2.jpg",
                1,2);
        ProductInfo res = productInfoService.save(productInfo);
        Assert.assertNotNull(res);
    }
}