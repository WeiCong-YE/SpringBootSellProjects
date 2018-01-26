package com.imooc.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


@Data
public class BannerForm {
    @NotEmpty(message = "请输入文本信息")
    private String title;
    @NotEmpty(message = "请输入图片信息")
    private String img;
    @NotNull(message = "请输入Id")
    private Integer bannerId;
}
