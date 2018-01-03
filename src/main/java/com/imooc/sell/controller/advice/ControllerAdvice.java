package com.imooc.sell.controller.advice;

import com.imooc.sell.exception.ErrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.ControllerAdvice
@Slf4j
public class ControllerAdvice {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "Magical Sam");
    }

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(ErrException ex) {
        log.info("【错误异常全局捕获】"+ex.toString());
        Map<String,Object> map = new HashMap<>(3);
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        map.put("data",null);
        return map;
    }
}
