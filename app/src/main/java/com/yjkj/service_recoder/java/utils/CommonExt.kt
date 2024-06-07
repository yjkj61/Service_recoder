package com.yjkj.property_management.java.utils

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.yjkj.property_management.Application
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

/**
 * 身份证号提取生日
 */
@RequiresApi(Build.VERSION_CODES.O)
fun String.extractBirthDate():String{
    val birthDateString = this.substring(6, 14)
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
    val birthDate = LocalDate.parse(birthDateString, formatter)
    return birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}

@SuppressLint("SimpleDateFormat")
fun Date.getTimeFormat(format : String = "yyyy-MM-dd"):String{
    val format = SimpleDateFormat(format)
    return format.format(this)
}

fun toast(msg : String){
    ToastUtil.showToast(msg,Application.context.applicationContext)
}


fun String.log(tag : String = "Okhttp"){
    Log.d(tag, this)
}

/**
 * 防止快速点击
 */
var mLastTime = 0L
fun clickNoRepeat2(interval: Long = 500 , onClick: () -> Unit){
    val currentTime = System.currentTimeMillis()
    if(mLastTime != 0L && (currentTime - mLastTime < interval)){
        return
    }
    mLastTime = currentTime
    onClick()
}

fun String?.ifNull(block : () -> String):String{
    return this ?: block()
}


fun Int.toMedicalStr():String{
   return when(this){
        0->{
            "高血压"
        }
        1->{
            "糖尿病"
        }
        2->{
            "高血脂"
        }
        3->{
            "冠心病"
        }
        4->{
            "脑卒中"
        }
        5->{
            "慢性肺病"
        }
        6->{
            "癌症"
        }
        7->{
            "阿兹海默症"
        }
       else->{
           ""
       }
    }
}

fun List<String>.join2String():String{
    val s = this.joinToString(",")
    return s.ifBlank {
        ""
    }
}