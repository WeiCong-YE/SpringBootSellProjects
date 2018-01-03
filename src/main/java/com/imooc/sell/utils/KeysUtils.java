package com.imooc.sell.utils;

import java.util.Random;

/**
 * 关于KEY的工具类
 */
public class KeysUtils {
    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     *
     * @return 随机数
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
