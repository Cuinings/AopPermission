package com.cn.aoppermission;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

//import com.cn.permission_kotlin.aop.annotation.Permission;
//import com.cn.permission_kotlin.aop.annotation.PermissionCancel;
//import com.cn.permission_kotlin.aop.annotation.PermissionDenied;

import com.cn.permission.aop.annotation.Permission;
import com.cn.permission.aop.annotation.PermissionCancel;
import com.cn.permission.aop.annotation.PermissionDenied;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
    }

    public void requestPermission(View view) {
        request();
    }

    @Permission(value = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE
    }, requestCode = 200)
    private void request() {
        Toast.makeText(this, "申请成功", Toast.LENGTH_SHORT).show();
    }

    @PermissionCancel(requestCode = 200)
    public void cancel() {
        Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(requestCode = 200)
    public void denied() {
        Toast.makeText(this, "已拒绝", Toast.LENGTH_SHORT).show();
    }
}
