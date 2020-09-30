package com.cn.permission_kotlin.aop

import android.content.Context
import androidx.fragment.app.Fragment
import com.cn.permission_kotlin.RequestPermission
import com.cn.permission_kotlin.aop.annotation.Permission
import com.cn.permission_kotlin.aop.annotation.PermissionCancel
import com.cn.permission_kotlin.aop.annotation.PermissionDenied
import com.cn.permission_kotlin.aop.listener.PermissionListener
import com.cn.permission_kotlin.aop.util.PermissionUtil
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

/**
 * @Author:          崔宁 github: https://github.com/Cuinings
 * @Email:          1015597172@qq.com
 * @Date:           2020/6/4 2:53 PM
 * @Description:
 */
@Aspect
class PermissionAspectJ {
    @Pointcut("execution(@com.cn.permission_kotlin.aop.annotation.Permission * *(..)) && @annotation(permission)")
    fun methodPointPermission(permission: Permission) {

    }

    @Around("methodPointPermission(permission)")
    fun aProceedingJoinPoint(point: ProceedingJoinPoint, permission: Permission?) {
        var context: Context? = null
        var anyContext: Any = point.`this`
        if (anyContext is Context) {
            context = anyContext
        } else if (anyContext is Fragment) {
            context = anyContext.activity!!
        }
        if (null != context)
            RequestPermission.requestPermission(context, permission?.value, permission!!.requestCode, object: PermissionListener {
                override fun success() {
                    point.proceed();
                }

                override fun cancel() {
                    PermissionUtil.invokeAnnotation(anyContext, PermissionCancel::class.java)
                }

                override fun denied() {
                    PermissionUtil.invokeAnnotation(anyContext, PermissionDenied::class.java)
                }

            })

    }
}