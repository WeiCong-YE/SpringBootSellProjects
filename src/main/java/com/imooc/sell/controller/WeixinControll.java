package com.imooc.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Slf4j
@RequestMapping("/weixin")
@ApiIgnore
public class WeixinControll {


    @GetMapping("/auth")
    public void auth(@RequestParam("code")String code){
        log.info("【进入了auth接口】"+code);
    }
}
