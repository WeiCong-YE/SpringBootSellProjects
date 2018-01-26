package com.imooc.sell.form;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 食物类型
 */
@Data
public class FoodUpdateForm {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("类型名字")
    private String name;
    @ApiModelProperty("图片路径")
    private String img;
    @ApiModelProperty("排序")
    private Integer sortFlag;

}
