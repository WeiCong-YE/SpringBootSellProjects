package com.imooc.sell.utils;

import com.imooc.sell.VO.ResultVO;

/**
 * 成功结果的返回封装
 */
public class ResultVoUtils {

    /**
     * 成功封装
     *
     * @param o
     * @return ResultVO
     */
    public static <T>ResultVO success(T o) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setData(o);
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        return resultVO;
    }

    /**
     * 成功 但是data没有返回值的方法
     * @return ResultVO
     */
    public static ResultVO success() {
        return success(null);
    }

    /**
     * 失败的方法
     * @param code
     * @param msg
     * @return
     */
    public static ResultVO error(Integer code,String msg){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMessage(msg);
        return resultVO;
    }
    public static ResultVO error(String code,String msg){
        return error(Integer.valueOf(code),msg);
    }
}
