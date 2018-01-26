package com.imooc.sell.repository;

import com.imooc.sell.dataoobject.Banner;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner,Integer>{

    Banner findBannerById (Integer id);
    void deleteBannerById(Integer id);
}
