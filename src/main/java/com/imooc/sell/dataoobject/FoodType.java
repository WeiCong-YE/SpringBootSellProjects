package com.imooc.sell.dataoobject;


import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 食物类型
 */
@Data
@Entity
public class FoodType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ApiModelProperty("类型名字")
    @NotEmpty(message = "类型名字不能为空")
    private String name;
    @NotEmpty(message = "图片路径")
    @ApiModelProperty("图片路径")
    private String img;
    @NotNull(message = "排序不能为空")
    @Column(unique = true)
    @ApiModelProperty("排序依据，排序越大，越靠前")
    private Integer sortFlag;

}
