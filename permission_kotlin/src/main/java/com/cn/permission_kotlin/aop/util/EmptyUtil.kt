package com.cn.permission_kotlin.aop.util

/**
 * @Author:          崔宁 github: https://github.com/Cuinings
 * @Email:          1015597172@qq.com
 * @Date:           2020/6/4 3:18 PM
 * @Description:
 */
class EmptyUtil {

    companion object {
        fun isEmpty(str: String?): Boolean {
            if (str?.isEmpty()!!)
                return true
            return false;
        }

        fun isEmpty(strs: Array<String>?): Boolean {
            if (strs.isNullOrEmpty())
                return true;
            return false
        }
    }
}