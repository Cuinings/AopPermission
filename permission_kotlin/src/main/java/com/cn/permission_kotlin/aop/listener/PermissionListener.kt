package com.cn.permission_kotlin.aop.listener

/**
 * @Author:          崔宁 github: https://github.com/Cuinings
 * @Email:          1015597172@qq.com
 * @Date:           2020/6/4 2:29 PM
 * @Description:
 */
interface PermissionListener {

    fun success()

    fun cancel()

    fun denied()
}