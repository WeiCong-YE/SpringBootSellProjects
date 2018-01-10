package com.imooc.sell.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 测试controller
 */
@RestController
@Slf4j
public class Test {
    private static final String TOKEN = "good";
    @RequestMapping(value = "/sell/test", method = RequestMethod.GET)
    public String test(@RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam("echostr") String echostr) {
        log.info("【被调用了=】signature={},timestamp={},nonce={},echostr={}", signature, timestamp, nonce, echostr);
//        排序
        String sortString = sort(TOKEN, timestamp, nonce);
//        加密
        String myString = sha1(sortString);
        log.info("【myString=】" + myString);
        if (!StringUtils.isEmpty(myString) && myString.equals(signature)) {
            log.info("【签名校验通过】");
            return echostr;
        } else {
            log.info("【签名校验失败】");
            return echostr;
        }
    }

//    /**
//     * 微信Token验证
//     *
//     * @param signature 微信加密签名
//     * @param timestamp 时间戳
//     * @param nonce     随机数
//     * @param echostr   随机字符串
//     * @return
//     * @throws NoSuchAlgorithmException
//     * @throws IOException
//     */
//    @RequestMapping("/sell/test")
//    public String getToken(String signature, String timestamp, String nonce, String echostr) throws NoSuchAlgorithmException, IOException {
//        // 将token、timestamp、nonce三个参数进行字典序排序
//        log.error("fuck--"+echostr);
//        System.out.println("signature:" + signature);
//        System.out.println("timestamp:" + timestamp);
//        System.out.println("nonce:" + nonce);
//        System.out.println("echostr:" + echostr);
//        System.out.println("TOKEN:" + TOKEN);
//        String[] params = new String[]{TOKEN, timestamp, nonce};
//        Arrays.sort(params);
//        // 将三个参数字符串拼接成一个字符串进行sha1加密
//        String clearText = params[0] + params[1] + params[2];
//        String algorithm = "SHA-1";
//        String sign = new String(
//                org.apache.commons.codec.binary.Hex.encodeHex(MessageDigest.getInstance(algorithm).digest((clearText).getBytes()), true));
//        // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
//        if (signature.equals(sign)) {
//            log.info("【签名校验通过】");
//        }
//        return echostr;
//    }



    /**
     * 排序
     *
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     */
    private String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 加密
     *
     * @param str
     * @return
     */
    private String sha1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为 十六进制 数
            for (byte aMessageDigest : messageDigest) {
                String shaHex = Integer.toHexString(aMessageDigest & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
