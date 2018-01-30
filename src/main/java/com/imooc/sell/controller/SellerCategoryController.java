package com.imooc.sell.controller;


import com.imooc.sell.dataoobject.ProductCategory;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.form.ProductCategoryAddForm;
import com.imooc.sell.service.CategoryService;
import com.imooc.sell.utils.BeanUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

import static com.imooc.sell.enums.ResultEnum.LACK_OF_PARAMETERS;
import static com.imooc.sell.enums.ResultEnum.PAGE_SIZE_ERR;

@Controller
@RequestMapping("category")
@Slf4j
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("list")
    @ApiOperation("获取所有类目")
    public ModelAndView getAllCategory(@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "1") Integer page, Map<String, Object> map) {
        map.put("url", "list");
        if (page < -1 || size < 0) {
            throw new ErrException(PAGE_SIZE_ERR.getCode(), PAGE_SIZE_ERR.getMessage());
        }
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<ProductCategory> productCategories = categoryService.findAll(pageRequest);
        map.put("productCategories", productCategories);
        map.put("currPage", page);
        map.put("size", size);
        return new ModelAndView("category/list", map);
    }


    @GetMapping("delete")
    @ApiOperation("删除类目")
    public ModelAndView deleteCategory(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                                       Map<String, Object> map) {
        map.put("url", "list");
        if (categoryId == null) {
            // 抛参数缺少异常，错误页面
            map.put("msg", LACK_OF_PARAMETERS.getMessage());
            return new ModelAndView("comment/error", map);
        }
        try {
            boolean isDeleteSuc = categoryService.delete(categoryId);
            if (isDeleteSuc) {
                // 删除成功
                return new ModelAndView("list", map);
            } else {
                // 删除错误
                return new ModelAndView("comment/error", map);
            }
        } catch (Exception e) {
            // 抛找不到该数据异常，错误页面
            map.put("msg", e.getMessage());
            return new ModelAndView("comment/error", map);
        }
    }

    @GetMapping("categoryMsg")
    @ApiOperation("类目详情")
    public ModelAndView categoryInfo(@RequestParam(value = "categoryId", required = false) Integer categoryId, Map<String, Object> map) {
        map.put("url", "list");
        try {
            if (categoryId != null) {
                ProductCategory productCategory = categoryService.findOne(categoryId);
                map.put("productCategory", productCategory);
            }
            return new ModelAndView("category/categoryMsg", map);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            return new ModelAndView("comment/error", map);
        }
    }

    @PostMapping("updateOrSave")
    @ApiOperation("更新/插入类目")
    public ModelAndView updateOrSave(@Valid ProductCategoryAddForm productCategory,
                                     BindingResult bindingResult,
                                     Map<String, Object> map) {
        log.error("【传进来的数据是 {}】", productCategory.toString());
        map.put("url", "list");
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("comment/error", map);
        }
        try {
            ProductCategory target = new ProductCategory();
            if (productCategory.getCategoryId() != null) {
                target = categoryService.findOne(productCategory.getCategoryId());
            }
            BeanUtils.copyNonNullProperties(productCategory, target);
            log.error("【复制后的数据是 {}】", target.toString());
            categoryService.save(target);
            map.put("msg", "操作成功");
            return new ModelAndView("comment/success", map);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            return new ModelAndView("comment/error", map);
        }

    }
}
