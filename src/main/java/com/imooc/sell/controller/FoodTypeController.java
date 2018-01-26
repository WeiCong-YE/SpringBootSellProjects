package com.imooc.sell.controller;

import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.dataoobject.FoodType;
import com.imooc.sell.service.FoodTypeService;
import com.imooc.sell.utils.ResultVoUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foodType")
@Api(value = "食品类型模块", description = "首页模块")
public class FoodTypeController {

    @Autowired
    private FoodTypeService foodTypeService;


    @GetMapping("findAll")
    @ApiModelProperty("查找所有的食品类型")
    public ResultVO findAll() {
        List<FoodType> foodTypeList = foodTypeService.findAll();
        return ResultVoUtils.success(foodTypeList);
    }
}
