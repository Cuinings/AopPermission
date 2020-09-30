package com.cn.permission_kotlin.aop.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.ArrayMap
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.cn.permission_kotlin.aop.menu.*

/**
 * @Author:          崔宁 github: https://github.com/Cuinings
 * @Email:          1015597172@qq.com
 * @Date:           2020/6/4 3:09 PM
 * @Description:
 */
class PermissionUtil {

    companion object {

        var settingPageMenu= ArrayMap<String, Class<out SettingPageMenu>>()

        val DEFAULT_SEATTING_PAGE: String = "defalut"
        val HUAWEI_SETTING_PAGE:String = "huawei"
        val XIAOMI_SETTING_PAGE:String = "xiaomi"
        val OPPO_SETTING_PAGE:String = "oppo"
        val VIVO_SETTING_PAGE:String = "vivo"

        init {
            settingPageMenu[DEFAULT_SEATTING_PAGE] = DefaultSettingPage::class.java
//            settingPageMenu[HUAWEI_SETTING_PAGE] = HUAWEISettingPage::class.java
//            settingPageMenu[XIAOMI_SETTING_PAGE] = XIAOMISettingPage::class.java
//            settingPageMenu[OPPO_SETTING_PAGE] = OPPOSettingPage::class.java
//            settingPageMenu[VIVO_SETTING_PAGE] = VIVOSettingPage::class.java
        }

        /**
         *
         */
        fun hasPermissionRequest(context: Context, permissions: Array<String>?):Boolean {
            if (EmptyUtil.isEmpty(permissions))
                return false;
            for (permission in permissions!!) {
                if (EmptyUtil.isEmpty(permission)) return false
                if (!isPermissionRequest(context, permission)) {
                    return false
                }
            }
            return true
        }

        /**
         *
         */
        private fun isPermissionRequest(context: Context, permission: String): Boolean {
            return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        }

        /**
         * 检查权限是否申请成功
         */
        fun requestPermissionSuccess(grantResults: Array<Int>): Boolean {
            if (grantResults.isNullOrEmpty()) return false
            for (grantResult in grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED)
                    return false;
            }
            return true;
        }

        fun shouldShowRequestPermissionRationale(activity: Activity,@NonNull permissions: Array<String>): Boolean {
            for (permission in permissions) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    return true
                }
            }
            return false
        }

        /**
         * 判断any对象类中芳芳是否持有clazz注解并且执行
         */
        fun invokeAnnotation(any: Any, clazz: Class<out Annotation>) {
            var objectClass = any.javaClass
            var declaredMehtods = objectClass.declaredMethods
            for (method in declaredMehtods) {
                method.isAccessible = true
                if (method.isAnnotationPresent(clazz)) {
                    method.invoke(any)
                }
            }
        }
    }
}