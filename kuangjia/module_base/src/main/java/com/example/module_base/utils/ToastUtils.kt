package com.example.module_base.utils

import android.view.Gravity
import android.widget.Toast
import com.example.module_base.common.BaseApplication

object ToastUtils {

    var toast: Toast? = null

    fun success(msg: String) {
        toast = Toast.makeText(BaseApplication.context, msg, Toast.LENGTH_SHORT)
        toast?.setGravity(Gravity.CENTER, 0, 0)
        toast?.show()
//        Toasty.success(BaseApplication.context, msg, Toast.LENGTH_SHORT, true).show()
    }

    fun error(msg: String) {
        toast = Toast.makeText(BaseApplication.context, msg, Toast.LENGTH_SHORT)
        toast?.setGravity(Gravity.CENTER, 0, 0)
        toast?.show()
//        Toasty.error(BaseApplication.context, msg, Toast.LENGTH_SHORT, true).show()
    }

    fun info(msg: String) {
        toast = Toast.makeText(BaseApplication.context, msg, Toast.LENGTH_SHORT)
        toast?.setGravity(Gravity.CENTER, 0, 0)
        toast?.show()
//        Toasty.info(BaseApplication.context, msg, Toast.LENGTH_SHORT, true).show()
    }

    fun warning(msg: String) {
        toast = Toast.makeText(BaseApplication.context, msg, Toast.LENGTH_SHORT)
        toast?.setGravity(Gravity.CENTER, 0, 0)
        toast?.show()
//        Toasty.warning(BaseApplication.context, msg, Toast.LENGTH_SHORT, true).show()
    }
}