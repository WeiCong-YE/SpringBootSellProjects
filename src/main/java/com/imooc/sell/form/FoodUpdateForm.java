package com.imooc.sell.form;


import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 食物类型
 */
@Data
public class FoodUpdateForm {
    @NotNull(message = "类型ID不可以为空")
    @ApiModelProperty("类型ID")
    private Integer id;
    @NotNull(message = "类型名字不可以少")
    @ApiModelProperty("类型名字")
    private String name;
    @NotNull(message = "图片路径不可以少")
    @ApiModelProperty("图片路径")
    private String img;
    @NotNull(message = "排序标识不可以少")
    @ApiModelProperty("排序")
    private Integer sortFlag;

}
