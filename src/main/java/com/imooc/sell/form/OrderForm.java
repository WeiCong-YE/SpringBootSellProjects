package com.imooc.sell.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;

@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    @ApiModelProperty("买家姓名")
    private String name;
    /**
     * 买家手机号码
     */
    @NotEmpty(message = "手机号码必填")
    @ApiModelProperty("买家手机号码")
    private String phone;
    /**
     * 买家地址
     */
    @NotEmpty(message = "买家地址必填")
    @ApiModelProperty("买家地址必填")
    private String address;
    /**
     * 买家微信openId
     */
    @NotEmpty(message = "微信openId必填")
    @ApiModelProperty("买家微信openId")
    private String openid;
    /**
     * 购物车
     */
    @NotEmpty(message = "购物车必填")
    @ApiModelProperty("购物车")
    private String items;
}
