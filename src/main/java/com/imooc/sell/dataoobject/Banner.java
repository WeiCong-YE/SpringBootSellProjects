package com.imooc.sell.dataoobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 轮播图广告
 */
@Entity
@Data
@ApiModel("广告参数类")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty("广告id")
    private Integer id;
    @ApiModelProperty("广告文案")
    private String title;
    @ApiModelProperty("广告图片地址")
    private String img;
}
