package com.imooc.sell.controller;


import com.imooc.sell.VO.ProductInfoVO;
import com.imooc.sell.VO.ProductVo;
import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.dataoobject.ProductCategory;
import com.imooc.sell.dataoobject.ProductInfo;
import com.imooc.sell.service.impl.CategoryServiceImpl;
import com.imooc.sell.service.impl.ProductServiceImpl;
import com.imooc.sell.utils.ResultVoUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
@Slf4j
@Api( value = "买家接口",description = "商品管理模块")
public class BuyerProductController {


    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     * 商品列表
     *
     * @return
     */
    @ApiOperation("查询商品列表")
    @GetMapping("/list")
    public ResultVO list() {
//        1.查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
//        2.查询类目（一次性查询）
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .distinct()
                .collect(Collectors.toList());
        log.error("【找到的产品列表id列表】" + categoryTypeList.toString());
        List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(categoryTypeList);
//        3.数据拼装
        List<ProductVo> productVoList = new ArrayList<>();

        log.error("【找到的产品列表ProductVo列表】" + productCategories.toString());
        for (ProductCategory productCategory : productCategories) {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(productCategory, productVo);

            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                // 根据每个产品类别找到对应的产品列表
                if (productCategory.getCategoryId().equals(productInfo.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            }
            productVo.setProductInfoVOS(productInfoVOS);
            productVoList.add(productVo);

        }
        return ResultVoUtils.success(productVoList);
    }


}
