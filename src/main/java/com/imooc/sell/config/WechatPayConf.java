package com.imooc.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WechatPayConf {
    @Autowired
    private WechatAccountConfig mWechatAccountConfig;


    @Bean
    public BestPayServiceImpl mBestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(mWechatAccountConfig.getMpAppId());
        wxPayH5Config.setAppSecret(mWechatAccountConfig.getMpAppSecret());
        wxPayH5Config.setMchKey(mWechatAccountConfig.getMchKey());
        wxPayH5Config.setMchId(mWechatAccountConfig.getMchId());
        wxPayH5Config.setKeyPath(mWechatAccountConfig.getKeyPath());
        wxPayH5Config.setNotifyUrl(mWechatAccountConfig.getNotifyUrl());
        log.error("【wxPayH5Config的信息={}】",wxPayH5Config.toString());
        return wxPayH5Config;
    }

}
