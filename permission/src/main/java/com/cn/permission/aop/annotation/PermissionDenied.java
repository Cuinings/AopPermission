package com.cn.permission.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: 崔宁 github: https://github.com/Cuinings
 * @Email: 1015597172@qq.com
 * @Date: 2020/6/4 11:00 AM
 * @Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionDenied {
    int requestCode() default 0;
}
