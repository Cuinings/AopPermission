package com.cn.permission.aop.aspectJ;

import android.content.Context;

import androidx.fragment.app.Fragment;


import com.cn.permission.RequestPermission;
import com.cn.permission.aop.annotation.Permission;
import com.cn.permission.aop.annotation.PermissionCancel;
import com.cn.permission.aop.annotation.PermissionDenied;
import com.cn.permission.aop.listener.PermissionListener;
import com.cn.permission.aop.util.PermissionUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jetbrains.annotations.NotNull;

/**
 * @Author: 崔宁 github: https://github.com/Cuinings
 * @Email: 1015597172@qq.com
 * @Date: 2020/6/3 4:45 PM
 * @Description:
 */
@Aspect
public class PermissionAspectJ {

    @Pointcut("execution(@com.cn.permission.aop.annotation.Permission * *(..)) && @annotation(permission)")
    public void methodPointPermission(Permission permission){

    }

    @Around("methodPointPermission(permission)")
    public void aProceedingJoinPoint(final ProceedingJoinPoint point, final Permission permission) throws Throwable {
        Context context = null;
        final Object thisObject = point.getThis();
        if (thisObject instanceof  Context) {
            context = (Context) thisObject;
        } else if (thisObject instanceof Fragment) {
            context = ((Fragment) thisObject).getActivity();
        }

        if (null == context || null == permission) {
            throw new IllegalAccessException("Context is null or permission is null");
        }

        RequestPermission.requestPermission(context, permission.value(), permission.requestCode(), getListener(point, thisObject));
    }

    @NotNull
    private PermissionListener getListener(final ProceedingJoinPoint point, final Object thisObject) {
        return new PermissionListener() {

            @Override
            public void success() {
                try {
                    point.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }

            @Override
            public void cancel() {
                PermissionUtil.invokeAnnotation(thisObject, PermissionCancel.class);
            }

            @Override
            public void denied() {
                PermissionUtil.invokeAnnotation(thisObject, PermissionDenied.class);
            }

        };
    }
}
