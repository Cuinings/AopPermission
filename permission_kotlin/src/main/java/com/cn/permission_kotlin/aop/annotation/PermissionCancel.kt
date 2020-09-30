package com.cn.permission_kotlin.aop.annotation

/**
 * @Author:          崔宁 github: https://github.com/Cuinings
 * @Email:          1015597172@qq.com
 * @Date:           2020/6/4 2:52 PM
 * @Description:
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class PermissionCancel(val requestCode: Int)