package com.imooc.sell.service.impl;

import com.imooc.sell.dataoobject.Banner;
import com.imooc.sell.form.BannerForm;
import com.imooc.sell.service.BannerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BannerServiceImplTest {

    @Autowired
    private BannerService bannerService;

    @Test
    public void findBannerById() throws Exception {
        Banner banner = bannerService.findBannerById(1);
        Assert.assertNotNull(banner);
    }

    @Test
    public void addBanner() throws Exception {

    }

    @Test
    public void findAllBanner() throws Exception {
        List<Banner> list = bannerService.findAllBanner();
        Assert.assertNotNull(list);
    }

    @Test
    public void deleteBanner() throws Exception {
        Boolean reuslt = bannerService.deleteBanner(2);
        Assert.assertTrue(reuslt);
    }

    @Test
    public void updateBanner() throws Exception {
//        BannerForm bannerForm = new BannerForm();
//        bannerForm.setImg("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1760638054,1127251917&fm=27&gp=0.jpg");
//        bannerForm.setTitle("初暖如歌");
//        Banner banner = bannerService.updateBanner(bannerForm);
//        Assert.assertNotNull(banner);
    }

}