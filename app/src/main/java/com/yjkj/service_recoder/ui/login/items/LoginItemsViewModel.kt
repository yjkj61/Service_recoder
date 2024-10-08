package com.yjkj.service_recoder.ui.login.items

import android.view.View
import androidx.databinding.ObservableField
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.dsl.registerTextWatcher
import com.yjkj.service_recoder.ui.login.LoginViewModel

private typealias LoginItemEditCallback = (String)->Unit

class LoginItemsViewModel(val viewModel: LoginViewModel, data : Triple<Int, String,Int>) {

    private var callback : LoginItemEditCallback? = null

    val leftIcon = ObservableField(data.first)
    val type = ObservableField("请输入${data.second}")

    val content = ObservableField("1")

    val inputType = ObservableField(data.third)

    val pwdVisibleIcon = ObservableField(R.drawable.group_115)
    val pwdVisibleIconVisibility = ObservableField<Int>().apply {
        if(data.second == "密码"){
            set(View.VISIBLE)
        }else{
            set(View.GONE)
        }
    }

    fun textChangedCallback(callback : LoginItemEditCallback){
        this.callback = callback
    }

    fun changePwdVisible(){
        if(inputType.get() == 0x00000091){
            inputType.set(0x000000e1)
            pwdVisibleIcon.set(R.drawable.group_115)
        }else{
            inputType.set(0x00000091)
            pwdVisibleIcon.set(R.drawable.password_visibile_icon)
        }
    }

    val textWatcher = registerTextWatcher {
        onTextChanged { s, start, before, count ->
            callback?.invoke(s.toString()) ?: Unit
        }
    }
}