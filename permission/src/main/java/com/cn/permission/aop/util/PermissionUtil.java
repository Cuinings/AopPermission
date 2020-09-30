package com.cn.permission.aop.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.cn.permission.aop.menu.DefaultStartSettings;
import com.cn.permission.aop.menu.OPPOStartSettings;
import com.cn.permission.aop.menu.SettingPageMenu;
import com.cn.permission.aop.menu.VIVOStartSettings;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: 崔宁 github: https://github.com/Cuinings
 * @Email: 1015597172@qq.com
 * @Date: 2020/6/4 9:45 AM
 * @Description:
 */
public class PermissionUtil {

    private static final String TAG = PermissionUtil.class.getSimpleName();

    public static ArrayMap<String, Class<? extends SettingPageMenu>> settingPageMenu = new ArrayMap<>();

    private static final String MANUFACTURER_DEFAULT = "Default";//默认

    public static final String MANUFACTURER_HUAWEI = "huawei";//华为
    public static final String MANUFACTURER_MEIZU = "meizu";//魅族
    public static final String MANUFACTURER_XIAOMI = "xiaomi";//小米
    public static final String MANUFACTURER_SONY = "sony";//索尼
    public static final String MANUFACTURER_OPPO = "oppo";
    public static final String MANUFACTURER_LG = "lg";
    public static final String MANUFACTURER_VIVO = "vivo";
    public static final String MANUFACTURER_SAMSUNG = "samsung";//三星
    public static final String MANUFACTURER_LETV = "letv";//乐视
    public static final String MANUFACTURER_ZTE = "zte";//中兴
    public static final String MANUFACTURER_YULONG = "yulong";//酷派
    public static final String MANUFACTURER_LENOVO = "lenovo";//联想

    static {
        settingPageMenu.put(MANUFACTURER_DEFAULT, DefaultStartSettings.class);
        settingPageMenu.put(MANUFACTURER_OPPO, OPPOStartSettings.class);
        settingPageMenu.put(MANUFACTURER_VIVO, VIVOStartSettings.class);
    }

    public static boolean hasPermissionRequest(Context context, String... permissions) {
        if (EmptyUtil.isEmpty(permissions))
            return false;
        for (String permission : permissions) {
            if (EmptyUtil.isEmpty(permission))
                return false;
            if (!isPermissionRequest(context, permission)) {
                return false;
            }
        }
        return true;
    }

    private static boolean permissionExits(String permission) {
        if (EmptyUtil.isEmpty(permission)) return false;
        return false;
    }

    private static boolean isPermissionRequest(Context context, String permission) {
        if (EmptyUtil.isEmpty(permission)) return false;
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 检查权限是否申请成功
     * @return
     */
    public static boolean requestPermissionSuccess(int... grantResults) {
        if (null == grantResults || grantResults.length == 0)
            return false;
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证权限是否被拒绝与勾选不在提示
     */
    public static boolean shouldShowRequestPermissionRationale (Activity activity, @NonNull String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断object对象类中方式是否持有clazz注解并且执行
     * @param object
     * @param clazz 注解类字节码
     */
    public static void invokeAnnotation(Object object, Class clazz) {
        Class<?> objectClass = object.getClass();
        Method[] declaredMethods = objectClass.getDeclaredMethods();
        for (Method method: declaredMethods) {
            Log.e(TAG, "invokeAnnotation: " + method.getName());
            method.setAccessible(true);
            boolean isAnnotationPresent = method.isAnnotationPresent(clazz);
            if (isAnnotationPresent) {
                try {
                    method.invoke(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
