package com.imooc.sell.exception;

import com.imooc.sell.enums.ResultEnum;

public class SellException extends RuntimeException {

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public SellException( Integer code,String message) {
        super(message);
        this.code = code;
    }

    private Integer code;

}
