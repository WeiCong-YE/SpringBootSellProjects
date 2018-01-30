package com.imooc.sell.controller;

import com.imooc.sell.dataoobject.ProductCategory;
import com.imooc.sell.dataoobject.ProductInfo;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.form.ProductAddForm;
import com.imooc.sell.service.CategoryService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.BeanUtils;
import com.imooc.sell.utils.KeysUtils;
import com.sun.xml.internal.bind.v2.model.core.ID;

import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.validation.Valid;
import javax.ws.rs.GET;

import io.swagger.annotations.ApiOperation;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import static com.imooc.sell.enums.ResultEnum.LACK_OF_PARAMETERS;
import static com.imooc.sell.enums.ResultEnum.PAGE_SIZE_ERR;
import static com.imooc.sell.enums.ResultEnum.THE_ONE_IS_NOT_EXIT;

@Controller
@RequestMapping("/products")
@Slf4j
public class SellProductsController {

    @Autowired
    private ProductService mProductService;

    @Autowired
    private CategoryService mCategoryService;

    @GetMapping("list")
    @ApiOperation("获取所有的商品列表")
    public ModelAndView list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        if (page < -1 || size < 0) {
            throw new ErrException(PAGE_SIZE_ERR.getCode(), PAGE_SIZE_ERR.getMessage());
        }
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = mProductService.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        map.put("currPage", page);
        map.put("size", size);
        return new ModelAndView("products/list", map);
    }

    @GetMapping("detail")
    @ApiOperation("获取商品详情")
    public ModelAndView detail(@RequestParam("id") String id,
                               Map<String, Object> map) {
        if (StringUtils.isEmpty(id)) {
            throw new ErrException(LACK_OF_PARAMETERS.getCode(), LACK_OF_PARAMETERS.getMessage());
        }
        ProductInfo productInfo = mProductService.findOne(id);
        map.put("productInfo", productInfo);
        return new ModelAndView("products/detail", map);
    }

    @GetMapping("on_sale")
    @ApiOperation("上架")
    public ModelAndView onSale(@RequestParam("id") String id,
                               Map<String, Object> map) {
        if (StringUtils.isEmpty(id)) {
            throw new ErrException(LACK_OF_PARAMETERS.getCode(), LACK_OF_PARAMETERS.getMessage());
        }
        try {
            mProductService.onSale(id);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "list");
            return new ModelAndView("comment/error");
        }
        map.put("msg", "上架成功");
        map.put("url", "list");
        return new ModelAndView("comment/success");
    }

    @GetMapping("off_sale")
    @ApiOperation("下架")
    public ModelAndView offSale(@RequestParam("id") String id,
                                Map<String, Object> map) {
        if (StringUtils.isEmpty(id)) {
            throw new ErrException(LACK_OF_PARAMETERS.getCode(), LACK_OF_PARAMETERS.getMessage());
        }
        try {
            mProductService.offSale(id);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "list");
            return new ModelAndView("comment/error");
        }
        map.put("msg", "下架成功");
        map.put("url", "list");
        return new ModelAndView("comment/success");
    }


    @GetMapping("index")
    @ApiOperation("详情")
    public ModelAndView index(@RequestParam(required = false)
                                      String productId, Map<String, Object> map) {
        if (!TextUtils.isEmpty(productId)) {
            ProductInfo productInfo = mProductService.findOne(productId);
            if (productInfo == null) {
                map.put("msg", THE_ONE_IS_NOT_EXIT.getMessage());
                map.put("url", "list");
                return new ModelAndView("comment/error");
            }
            map.put("productInfo", productInfo);
        }
        List<ProductCategory> productCategories = mCategoryService.findAll();
        map.put("categoryList", productCategories);
        return new ModelAndView("products/index", map);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    public ModelAndView save(@Valid ProductAddForm productAddForm, BindingResult bindingResult,
                             Map<String, Object> map) {
        map.put("url", "list");
        if (bindingResult.hasErrors()) {
            map.put("msg", THE_ONE_IS_NOT_EXIT.getMessage());

            return new ModelAndView("comment/error");
        }
        ProductInfo target = new ProductInfo();
        if (!StringUtils.isEmpty(productAddForm.getProductId())) {
            try {
                target = mProductService.findOne(productAddForm.getProductId());
                BeanUtils.copyNonNullProperties(productAddForm, target);
                map.put("msg", "修改成功");
            } catch (Exception e) {
                map.put("msg", e.getMessage());
                return new ModelAndView("comment/error");
            }
        } else {
            BeanUtils.copyNonNullProperties(productAddForm, target);
            target.setProductId(KeysUtils.getUniqueKey());
            target.setProductStatus(ProductStatusEnum.UP.getCode());
            map.put("msg", "添加成功");
        }
        mProductService.save(target);
        return new ModelAndView("comment/success");
    }
}
