package com.imooc.sell.dataoobject;

import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.utils.EnumUtils;

import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
/**
 * 产品信息表
 */
public class ProductInfo {


    @Id
    private String productId;
    /**
     * 商品名字
     */
    private String productName;
    /**
     * 商品价格
     */
    private BigDecimal productPrice;
    /**
     * 库存
     */
    private Integer productStock;
    /**
     * 描述
     */
    private String productDescription;
    /**
     * 图片
     */
    private String productIcon;
    /**
     * 状态 0 正常 1 下架
     */
    private Integer productStatus;
    /**
     * 类目编码
     */
    private Integer categoryType;

    private Date createTime;
    private Date updateTime;

    public ProductInfo() {
    }

    public ProductInfo(String productId, String productName, BigDecimal productPrice, Integer productStock, String productDescription, String productIcon, Integer productStatus, Integer categoryType) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.productStatus = productStatus;
        this.categoryType = categoryType;
    }

    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtils.getByCode(productStatus,ProductStatusEnum.class);
    }
    @ApiIgnore
    public String getProductStatus() {
        if (productStatus == 1) {
            return "上架";
        } else {
            return "下架";
        }
    }
}
