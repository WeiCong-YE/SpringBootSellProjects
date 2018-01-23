package com.imooc.sell.controller;

import com.imooc.sell.exception.ErrException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URLEncoder;

import static com.imooc.sell.enums.ResultEnum.WECHAT_ERR;

@Controller
@RequestMapping("/wechat")
@Slf4j
@Api(value = "微信模块")
public class WeChatController {
    @Autowired
    private WxMpService wxMpService;


    @ApiOperation("微信授权认证")
    @GetMapping("/authorize")
    public String authorize(@RequestParam(value = "returnUrl", required = false) String returnUrl) {
        // 配置
        String url = "http://linly.nat300.top/sell/wechat/userInfo";
        // 调用方法
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        log.error("【返回信息=】" + redirectUrl);

        return "redirect:" + redirectUrl;

    }


    @GetMapping("/userInfo")
    @ApiIgnore
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            throw new ErrException(WECHAT_ERR.getCode(), WECHAT_ERR.getMessage());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
