package com.cn.permission.aop.listener;

/**
 * @Author: 崔宁 github: https://github.com/Cuinings
 * @Email: 1015597172@qq.com
 * @Date: 2020/6/3 5:59 PM
 * @Description:
 */
public interface PermissionListener {

    void success();//成功

    void cancel();//拒绝

    void denied();//拒绝（不在提示）
}
