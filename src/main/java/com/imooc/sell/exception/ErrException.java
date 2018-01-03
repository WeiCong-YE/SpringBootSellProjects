package com.imooc.sell.exception;

import lombok.Data;

@Data
public class ErrException extends RuntimeException {
    private Integer code;
    private String msg;
    private Object[] args;

    public ErrException() {
    }

    public ErrException( Integer code,String message) {
        super(message);
        this.code = code;
        this.msg=message;
    }
}
