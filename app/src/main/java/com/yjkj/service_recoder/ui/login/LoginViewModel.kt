package com.yjkj.service_recoder.ui.login

import android.text.InputType
import android.view.View
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.google.gson.Gson
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.data.UserLoginHelper
import com.yjkj.service_recoder.java.bean.ApplyActiveEntity
import com.yjkj.service_recoder.java.data.UserDataHelper
import com.yjkj.service_recoder.java.entity.LoginEntity
import com.yjkj.service_recoder.java.http.OkHttpUtil
import com.yjkj.service_recoder.java.http.medicalservice.API
import com.yjkj.service_recoder.library.base.BaseViewModel
import com.yjkj.service_recoder.library.utils.ext.toast
import com.yjkj.service_recoder.ui.login.items.LoginItemsViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class LoginViewModel : BaseViewModel() {

    val loginTipsVisibility = ObservableField(View.GONE)
    val loginTipsText = ObservableField("")

    val loginItems = ObservableArrayList<LoginItemsViewModel>()
    val loginItemBinding = ItemBinding.of<LoginItemsViewModel>(BR.viewModel,R.layout.login_item_layout)

    val loginBtnEnable = ObservableField(true)

    val checkedUsername = ObservableField(UserLoginHelper.checkedUsername())
    val checkedPassword = ObservableField(UserLoginHelper.checkedPassword())
    val checkedRoomId = ObservableField(UserLoginHelper.checkedRoomId())

    fun initLoginItems(){
        loginItems.add(LoginItemsViewModel(this, Triple(R.drawable.group_153,"账号",0x00000001)))
        loginItems.add(LoginItemsViewModel(this,Triple(R.drawable.group_308,"密码",0x000000e1)))
        loginItems.add(LoginItemsViewModel(this,Triple(R.drawable.group_309,"房间号",0x00000001)))
        loginItems[0].content.set("zsm");
        loginItems[1].content.set("123456");
        loginItems[2].content.set("1-1-2106");

//        loginItems[0].apply {
//            content.set(UserLoginHelper.username())
//            textChangedCallback {
//                val item1 = loginItems[1]
//                val item2 = loginItems[2]
//                if(it.isNotEmpty() && item1.content.get().toString().isNotEmpty() && item2.content.get().toString().isNotEmpty()){
//                    loginBtnEnable.set(true)
//                }else{
//                    loginBtnEnable.set(false)
//                }
//            }
//        }
//        loginItems[1].apply {
//            content.set(UserLoginHelper.userpwd())
//            textChangedCallback {
//                val item0 = loginItems[0]
//                val item2 = loginItems[2]
//                if(it.isNotEmpty() && item0.content.get().toString().isNotEmpty() && item2.content.get().toString().isNotEmpty()){
//                    loginBtnEnable.set(true)
//                }else{
//                    loginBtnEnable.set(false)
//                }
//            }
//        }
//        loginItems[2].apply {
//            content.set(UserLoginHelper.userroomid())
//            textChangedCallback {
//                val item0 = loginItems[0]
//                val item1 = loginItems[1]
//                if(it.isNotEmpty() && item0.content.get().toString().isNotEmpty() && item1.content.get().toString().isNotEmpty()){
//                    loginBtnEnable.set(true)
//                }else{
//                    loginBtnEnable.set(false)
//                }
//            }
//        }
    }

    val checkedChangeListener = OnCheckedChangeListener { compoundButton , p1 ->
        when(compoundButton.id){
            R.id.remember_username ->{
                checkedUsername.set(p1)
            }
            R.id.remember_pwd->{
                checkedPassword.set(p1)
            }
            R.id.remember_roomid ->{
                checkedRoomId.set(p1)
            }
        }
    }

    fun login(success : ()->Unit = {}){
        val username =  loginItems[0].content.get() ?: return
        val password =  loginItems[1].content.get() ?: return
        val roomId =  loginItems[2].content.get() ?: return
//
//        if(checkedUsername.get() == true){
//            UserLoginHelper.checkedUsername(true)
//            UserLoginHelper.username(username)
//        }else{
//            UserLoginHelper.username("")
//            UserLoginHelper.checkedUsername(false)
//        }
//        if(checkedPassword.get() == true){
//            UserLoginHelper.checkedPassword(true)
//            UserLoginHelper.userpwd(password)
//        }else{
//            UserLoginHelper.userpwd("")
//            UserLoginHelper.checkedPassword(false)
//        }
//        if(checkedPassword.get() == true){
//            UserLoginHelper.checkedRoomId(true)
//            UserLoginHelper.userroomid(roomId)
//        }else{
//            UserLoginHelper.userroomid("")
//            UserLoginHelper.checkedRoomId(false)
//        }

        val jsonObject = JSONObject()
        jsonObject.put("username", username)
        jsonObject.put("password", password)
        jsonObject.put("roomnumber", roomId)

        OkHttpUtil.getInstance().doPost(API.login, jsonObject.toString(), object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    response.body?.let {
                        val data = Gson().fromJson(it.string(), LoginEntity::class.java)
                        if (data.code == 200){
                            UserDataHelper.token(data.data.access_token)
                            success.invoke()
                        }else{
                            toast(data.msg)
                        }
                    }
                }
            }
        })

    }

}