package com.imooc.sell.controller;


import com.imooc.sell.VO.ProductInfoVO;
import com.imooc.sell.VO.ProductVo;
import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.dataoobject.ProductCategory;
import com.imooc.sell.dataoobject.ProductInfo;
import com.imooc.sell.service.impl.CategoryServiceImpl;
import com.imooc.sell.service.impl.ProductServiceImpl;
import com.imooc.sell.utils.ResultVoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
@Slf4j
public class BuyerProductController {


    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryServiceImpl categoryService;


    @GetMapping("/list")
    public ResultVO list() {
//        1.查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
//        2.查询类目（一次性查询）
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .distinct()
                .collect(Collectors.toList());
        log.error(categoryTypeList.toString());
        List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(categoryTypeList);
//        3.数据拼装
        List<ProductVo> productVoList = new ArrayList<>();

        log.debug(productCategories.toString());
        for (ProductCategory productCategory : productCategories) {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(productCategory, productVo);

            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                ProductInfoVO productInfoVO = new ProductInfoVO();
                BeanUtils.copyProperties(productInfo, productInfoVO);
                productInfoVOS.add(productInfoVO);
            }
            productVo.setProductInfoVOS(productInfoVOS);
            productVoList.add(productVo);

        }
        return ResultVoUtils.success(productVoList);
    }
}
