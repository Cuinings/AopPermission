package com.cn.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.cn.permission.aop.listener.PermissionListener;
import com.cn.permission.aop.util.PermissionUtil;

/**
 * @Author: 崔宁 github: https://github.com/Cuinings
 * @Email: 1015597172@qq.com
 * @Date: 2020/6/4 1:42 PM
 * @Description:
 */
public class RequestPermission extends Activity {

    public static final String TAG = RequestPermission.class.getSimpleName();

    public static final String PARAM_PERMISSION = "param_permission";
    public static final String PARAM_PERMISSION_CODE = "param_permission_code";
    public static final int PARAM_PERMISSION_DEFAULT_CODE = -1;

    private String[] permissions;//需要申请的权限
    private int requestCode = -1;//权限申请code
    private static PermissionListener listener;//权限申请监听

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        this.permissions = getIntent().getStringArrayExtra(PARAM_PERMISSION);
        this.requestCode = getIntent().getIntExtra(PARAM_PERMISSION_CODE, PARAM_PERMISSION_DEFAULT_CODE);

        if (null == permissions && requestCode < 0 && null == listener) {
            this.finish();
            return;
        }

        if (PermissionUtil.hasPermissionRequest(this, permissions)) {
            listener.success();
            this.finish();
            return;
        }

        ActivityCompat.requestPermissions(this, permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (PermissionUtil.requestPermissionSuccess(grantResults)) {
            listener.success();
        } else if (PermissionUtil.shouldShowRequestPermissionRationale(RequestPermission.this, permissions)) {
            listener.denied();
        } else {
            listener.cancel();
        }
        this.finish();

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);//去除动画
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listener = null;
        permissions = null;
        requestCode = 0;
    }

    public static void requestPermission(Context context, String[] permissions, int requestCode, PermissionListener permissionListener) {
        RequestPermission.listener = permissionListener;
        Intent intent = new Intent();
        intent.setClass(context, RequestPermission.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putStringArray(PARAM_PERMISSION, permissions);
        bundle.putInt(PARAM_PERMISSION_CODE, requestCode);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
