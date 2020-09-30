package com.cn.permission_kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.cn.permission_kotlin.aop.listener.PermissionListener

/**
 * @Author:          崔宁 github: https://github.com/Cuinings
 * @Email:          1015597172@qq.com
 * @Date:           2020/6/4 2:21 PM
 * @Description:
 */
class RequestPermission : Activity() {

    companion object {
        private val PARAM_PERMISSION: String = "param_permission"
        private val PARAM_PERMISSION_CODE: String = "param_permission_code"
        private val PARAM_PERMISSION_DEFAULT_CODE: Int = -1
        private var listener: PermissionListener? = null

        fun requestPermission(context: Context, permissions: Array<out String>?, requestCode: Int, listener: PermissionListener) {
            RequestPermission.listener = listener

            var bundle: Bundle = Bundle()
            bundle.putStringArray(PARAM_PERMISSION, permissions)
            bundle.putInt(PARAM_PERMISSION_CODE, requestCode)

            var intent: Intent = Intent()
                    .setClass(context, RequestPermission.javaClass)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .putExtras(bundle)

            context.startActivity(intent)
        }
    }
    var permissions: Array<String>? = null
    var requestCode: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissions = intent.getStringArrayExtra(PARAM_PERMISSION)
        requestCode = intent.getIntExtra(PARAM_PERMISSION_CODE, PARAM_PERMISSION_DEFAULT_CODE)
        if (permissions.isNullOrEmpty() || requestCode < 0) {
            this.finish()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        listener = null
        permissions = null
        requestCode = 0
    }
}