package com.imooc.sell.controller;


import com.imooc.sell.VO.ProductInfoVO;
import com.imooc.sell.VO.ProductVo;
import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.converter.OrderForm2OrderDTOConverter;
import com.imooc.sell.dataoobject.ProductCategory;
import com.imooc.sell.dataoobject.ProductInfo;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.service.impl.CategoryServiceImpl;
import com.imooc.sell.service.impl.OrderServiceImpl;
import com.imooc.sell.service.impl.ProductServiceImpl;
import com.imooc.sell.utils.ResultVoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/list")
    public ResultVO list() {
//        1.查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
//        2.查询类目（一次性查询）
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .distinct()
                .collect(Collectors.toList());
        log.error("【找到的产品列表id列表】"+categoryTypeList.toString());
        List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(categoryTypeList);
//        3.数据拼装
        List<ProductVo> productVoList = new ArrayList<>();

        log.error("【找到的产品列表ProductVo列表】"+productCategories.toString());
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


    @PostMapping("/buy")
    public ResultVO create(@RequestBody @Valid OrderForm orderForm, BindingResult bindingResult){
        log.error("【接口参数】"+orderForm);
        if (bindingResult.hasErrors()){
            throw new ErrException(ResultEnum.LACK_OF_PARAMETERS.getCode(),ResultEnum.LACK_OF_PARAMETERS.getMessage());
        }
        OrderDto orderDto=OrderForm2OrderDTOConverter.convert(orderForm);
        log.error("【convert后的结果】"+orderDto);
        OrderDto createResult =   orderService.create(orderDto);
        log.error("【创建后的结果】"+createResult);
       return ResultVoUtils.success(orderForm);
    }
}
