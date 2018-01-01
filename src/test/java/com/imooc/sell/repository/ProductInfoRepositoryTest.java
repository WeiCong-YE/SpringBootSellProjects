package com.imooc.sell.repository;

import com.imooc.sell.dataoobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductDescription("山坡描述");
        productInfo.setProductIcon("http://s0.2mdn.net/5585042/17_728x90_PS_CN_2017_2.jpg");
        productInfo.setProductId("11");
        productInfo.setProductName("测试");
        productInfo.setProductStock(1);
        productInfo.setProductPrice(new BigDecimal(121));
        productInfo.setCategoryType(1);
        productInfo.setProductStatus(1);
        ProductInfo res = productInfoRepository.save(productInfo);
        Assert.assertNotNull(res);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfos = productInfoRepository.findByProductStatus(1);
        System.out.print(productInfos.toString());
        Assert.assertNotEquals(productInfos.size(), 0);
    }
}