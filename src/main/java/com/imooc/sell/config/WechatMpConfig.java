package com.imooc.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class WechatMpConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;
    @Bean
    public WxMpService wxMpService() {
        WxMpService mpService = new WxMpServiceImpl();
        mpService.setWxMpConfigStorage(wxMpConfigStorage());
        return mpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(wechatAccountConfig.getMaAppId());
        wxMpConfigStorage.setSecret(wechatAccountConfig.getMaAppSecret());
        return wxMpConfigStorage;
    }
}
