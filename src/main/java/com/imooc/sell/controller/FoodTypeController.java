package com.imooc.sell.controller;

import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.dataoobject.FoodType;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.form.FoodAddForm;
import com.imooc.sell.form.FoodUpdateForm;
import com.imooc.sell.service.FoodTypeService;
import com.imooc.sell.utils.ResultVoUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import static com.imooc.sell.enums.ResultEnum.THE_ONE_DELETE_ERROR;
import static com.imooc.sell.enums.ResultEnum.THE_ONE_IS_ADD_ERROR;
import static com.imooc.sell.enums.ResultEnum.LACK_OF_PARAMETERS;

@RestController
@RequestMapping("/foodType")
@Api(value = "食品类型模块", description = "首页模块")
public class FoodTypeController {

    @Autowired
    private FoodTypeService foodTypeService;


    @GetMapping("findAll")
    @ApiOperation("查找所有的食品类型")
    public ResultVO findAll() {
        List<FoodType> foodTypeList = foodTypeService.findAll();
        return ResultVoUtils.success(foodTypeList);
    }

    @PostMapping("addFoodType")
    @ApiOperation("添加食品类型")
    public ResultVO addFoodType(@RequestBody(required = false) @Valid FoodAddForm foodAddForm,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ErrException(LACK_OF_PARAMETERS.getCode(), LACK_OF_PARAMETERS.getMessage());
        }
        FoodType foodType = foodTypeService.addFoodType(foodAddForm);
        if (foodType == null) {
            throw new ErrException(THE_ONE_IS_ADD_ERROR.getCode(), THE_ONE_IS_ADD_ERROR.getMessage());
        }
        return ResultVoUtils.success(foodType);
    }

    @PutMapping("updateFoodType")
    @ApiOperation("更新食品类型")
    public ResultVO updateFoodType(@RequestBody(required = false) @Valid FoodUpdateForm foodUpdateForm,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ErrException(LACK_OF_PARAMETERS.getCode(), LACK_OF_PARAMETERS.getMessage());
        }
        FoodType result = foodTypeService.updateFoodType(foodUpdateForm);
        return ResultVoUtils.success(result);
    }

    @DeleteMapping("delete")
    @ApiOperation("删除食品类型")
    public ResultVO deleteFoodType(@ApiParam("食品类型ID") @RequestParam Integer id) {
        if (foodTypeService.deleteFoodType(id)) {
            return ResultVoUtils.success();
        } else {
            return ResultVoUtils.error(THE_ONE_DELETE_ERROR.getCode(), THE_ONE_DELETE_ERROR.getMessage());
        }
    }
}
