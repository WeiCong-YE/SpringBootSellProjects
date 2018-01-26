package com.imooc.sell.service.impl;

import com.imooc.sell.dataoobject.Banner;
import com.imooc.sell.form.BannerAddForm;
import com.imooc.sell.form.BannerForm;
import com.imooc.sell.repository.BannerRepository;
import com.imooc.sell.service.BannerService;
import com.imooc.sell.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public Banner findBannerById(Integer id) {
        return bannerRepository.findBannerById(id);
    }

    @Override
    public Banner addBanner(BannerAddForm bannerForm) {
        Banner banner = new Banner();
        banner.setImg(bannerForm.getImg());
        banner.setTitle(bannerForm.getTitle());
        return bannerRepository.save(banner);
    }

    @Override
    public List<Banner> findAllBanner() {
        return bannerRepository.findAll();
    }

    @Override
    public Boolean deleteBanner(Integer id) {
        bannerRepository.delete(id);
        Banner result = findBannerById(id);
        return result == null;
    }

    @Override
    public Banner updateBanner(Banner banner) {
        return bannerRepository.save(banner);
    }

}
