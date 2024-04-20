package com.yjkj.service_recoder.library.utils.ext

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.StringRes
import com.blankj.utilcode.util.ToastUtils

/**
 * des Toast工具类
 * @date 2020/5/14
 * @author hxy
 */
enum class ToastExt{
    BOTTOM,
    CENTER,
    TOP
}

fun Context.toast(content: String, duration: Int = Toast.LENGTH_SHORT,gravity : ToastExt = ToastExt.CENTER) {
    if (TextUtils.isEmpty(content))return

    ToastUtils.make().setGravity(Gravity.CENTER,0,0).show(content)
//    Toast.makeText(Utils.getApp(), content, duration).apply {
//        when (gravity) {
//            ToastExt.BOTTOM -> {
//
//            }
//            ToastExt.CENTER -> {
//                setGravity(Gravity.CENTER, 0, 0)
//            }
//            ToastExt.TOP -> {
//                setGravity(Gravity.TOP, 0, 0)
//            }
//        }
//        show()
//
//    }
}

fun Context.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT,gravity : ToastExt = ToastExt.CENTER) {
    ToastUtils.make().setGravity(Gravity.CENTER,0,0).show(getString(id))
}


fun toast(content: String, duration: Int = Toast.LENGTH_SHORT,gravity : Int = Gravity.CENTER) {
    if (TextUtils.isEmpty(content))return
    ToastUtils.make().setGravity(gravity,0,0).show(content)
}



fun longToast(content: String,duration: Int= Toast.LENGTH_LONG,gravity : ToastExt = ToastExt.BOTTOM) {
    if (TextUtils.isEmpty(content))return
    //Utils.getApp().toast(content,duration,gravity)
    ToastUtils.make().setGravity(Gravity.CENTER,0,0).show(content)
}





