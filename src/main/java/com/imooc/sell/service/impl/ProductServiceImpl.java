package com.imooc.sell.service.impl;

import com.imooc.sell.dataoobject.ProductInfo;
import com.imooc.sell.dto.CartDto;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }


    @Override
    public List<ProductInfo> findByProductStatus(Integer productStatus) {
        return productInfoRepository.findByProductStatus(productStatus);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = productInfoRepository.findOne(cartDto.getProductId());
            if (productInfo == null) {
                throw new ErrException(ResultEnum.PRODUCT_NOT_EXIST.getCode(),
                        ResultEnum.PRODUCT_NOT_EXIST.getMessage());
            }
            int result = productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = productInfoRepository.findOne(cartDto.getProductId());
            if (productInfo == null) {
                throw new ErrException(ResultEnum.PRODUCT_NOT_EXIST.getCode(),
                        ResultEnum.PRODUCT_NOT_EXIST.getMessage());
            }
            int result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (result < 0) {
                throw new ErrException(ResultEnum.PRODUCT_STACK_ERR.getCode(),
                        ResultEnum.PRODUCT_STACK_ERR.getMessage());
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
