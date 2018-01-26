package com.imooc.sell.service;


import com.imooc.sell.dataoobject.Banner;
import com.imooc.sell.form.BannerAddForm;
import com.imooc.sell.form.BannerForm;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BannerService {

    Banner findBannerById(Integer id);

    Banner addBanner(BannerAddForm bannerForm);

    List<Banner> findAllBanner();

    Boolean deleteBanner(Integer id);

    Banner updateBanner(Banner bannerForm );
}
