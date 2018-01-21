package com.imooc.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import javax.net.ssl.SSLContext;
@Component
@Data
@ConfigurationProperties("wechat")
public class WechatAccountConfig {
    /**
     * 公众号appId
     */
    private String mpAppId;
    /**
     * 公众号appSecret
     *
     */
    private String mpAppSecret;
    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 异步通知地址
     */
    private String notifyUrl;



}
