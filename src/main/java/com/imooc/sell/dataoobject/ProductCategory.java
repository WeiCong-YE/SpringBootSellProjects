package com.imooc.sell.dataoobject;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
/**
 * 卖家信息表
 */
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer categoryId;
    private String categoryName;
    @Column(unique = true)
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;
}
