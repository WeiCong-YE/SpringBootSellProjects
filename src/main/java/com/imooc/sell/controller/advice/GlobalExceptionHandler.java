package com.imooc.sell.controller.advice;


import com.imooc.sell.exception.ErrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ErrException.class)
    public Map errorHandler(ErrException ex) {
        Map<String,Object> map = new HashMap<>(3);
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        map.put("data",null);
        return map;
    }
}
