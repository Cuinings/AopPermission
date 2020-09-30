package com.cn.permission.aop.util;

/**
 * @Author: 崔宁 github: https://github.com/Cuinings
 * @Email: 1015597172@qq.com
 * @Date: 2020/6/4 10:02 AM
 * @Description:
 */
public class EmptyUtil {

    public static boolean isEmpty(String str) {
        if (null == str || str.length() == 0)
            return true;
        return false;
    }

    public static boolean isEmpty(String... strings) {
        if (null == strings || strings.length == 0)
            return true;
        return false;
    }
}
