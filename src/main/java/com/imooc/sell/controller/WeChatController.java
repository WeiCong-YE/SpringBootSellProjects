package com.imooc.sell.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

@RestController
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {

//    @Autowired
//    private WxMpService wxMpService;
//
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        // 配置
        String url = "http://sell.natapp4.cc/sell/wechat";
        log.error("【接口信息=】"+returnUrl);
        return returnUrl;
        // 调用方法
//        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
//        log.error("【返回信息=】" + redirectUrl);

//        return "redirect:" + redirectUrl;

    }
//
//
//    @GetMapping("/userInfo")
//    public String userInfo(@RequestParam("code") String code,
//                           @RequestParam("state") String returnUrl) {
//        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
//        try {
//            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
//        } catch (WxErrorException e) {
//            e.printStackTrace();
//        }
//        String openId = wxMpOAuth2AccessToken.getOpenId();
//        return "redirect:" + returnUrl + "?openid=" + openId;
//    }
}
