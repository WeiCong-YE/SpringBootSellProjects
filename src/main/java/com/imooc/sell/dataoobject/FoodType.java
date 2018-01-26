package com.imooc.sell.dataoobject;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

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
    private String name;
    @ApiModelProperty("图片路径")
    private String img;
    @Column(unique = true)
    @ApiModelProperty("排序依据，排序越大，越靠前")
    private Integer sortFlag;

}
