package com.yjkj.service_recoder.ui.login

import android.view.View
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.google.gson.Gson
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.MyApplication
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.data.UserLoginHelper
import com.yjkj.service_recoder.java.bean.OwnerEntityResultEntity
import com.yjkj.service_recoder.java.data.UserDataHelper
import com.yjkj.service_recoder.java.dataBaseBean.UserBean
import com.yjkj.service_recoder.java.entity.LoginEntity
import com.yjkj.service_recoder.java.entity.OwnerEntity
import com.yjkj.service_recoder.java.http.OkHttpUtil
import com.yjkj.service_recoder.java.http.medicalservice.API
import com.yjkj.service_recoder.library.base.BaseViewModel
import com.yjkj.service_recoder.library.utils.ext.toast
import com.yjkj.service_recoder.ui.login.items.LoginItemsViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class LoginViewModel : BaseViewModel() {

    val loginTipsVisibility = ObservableField(View.GONE)
    val loginTipsText = ObservableField("")

    val loginItems = ObservableArrayList<LoginItemsViewModel>()
    val loginItemBinding =
        ItemBinding.of<LoginItemsViewModel>(BR.viewModel, R.layout.login_item_layout)

    val loginBtnEnable = ObservableField(true)

    val checkedUsername = ObservableField(UserLoginHelper.checkedUsername())
    val checkedPassword = ObservableField(UserLoginHelper.checkedPassword())
    val checkedRoomId = ObservableField(UserLoginHelper.checkedRoomId())

    fun initLoginItems() {
        loginItems.add(LoginItemsViewModel(this, Triple(R.drawable.group_153, "账号", 0x00000001)))
        loginItems.add(LoginItemsViewModel(this, Triple(R.drawable.group_308, "密码", 0x000000e1)))
        loginItems.add(
            LoginItemsViewModel(
                this,
                Triple(R.drawable.group_309, "房间号", 0x00000001)
            )
        )
        loginItems[0].content.set("test0612");
        loginItems[1].content.set("123456");
        loginItems[2].content.set("9-3-666");

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

    val checkedChangeListener = OnCheckedChangeListener { compoundButton, p1 ->
        when (compoundButton.id) {
            R.id.remember_username -> {
                checkedUsername.set(p1)
            }

            R.id.remember_pwd -> {
                checkedPassword.set(p1)
            }

            R.id.remember_roomid -> {
                checkedRoomId.set(p1)
            }
        }
    }

    fun login(success: () -> Unit = {}) {
        val username = loginItems[0].content.get() ?: return
        val password = loginItems[1].content.get() ?: return
        val roomId = loginItems[2].content.get() ?: return
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
                response.body?.let {
                    val data = Gson().fromJson(it.string(), LoginEntity::class.java)
                    if (data.code != null && data.code == 200) {
                        UserDataHelper.token(data.data.access_token)
                        UserDataHelper.username(username)
                        UserDataHelper.password(password)
                        UserDataHelper.roomNumber(roomId)

                        OkHttpUtil.getInstance().doGet(API.userInfo(), object : Callback {
                            override fun onFailure(call: Call, e: IOException) {

                            }

                            override fun onResponse(call: Call, response: Response) {
//                                val userBean: UserBean = MyApplication.getInstance().db.userDao()
//                                    .getLoginStatusTrue(true)
//                                if (userBean != null) {
//                                    userBean.isLoginStatus = false
//                                    MyApplication.getInstance().db.userDao().updateUser(userBean)
//                                }
                                try {
//                                    val data = response.body!!.string()
//                                    val jsonObject2 = JSONObject(data)
                                    val data = Gson().fromJson(response.body!!.string(), OwnerEntityResultEntity::class.java)
                                    if (data.code == 200) {
                                        MyApplication.ownerEntity = data.data
                                        val userData = UserBean()
                                        userData.isLoginStatus = true
                                        userData.password = password
                                        userData.userId = data.data.userId
                                        userData.avatar =
                                            data.data.ownerPic
                                        userData.nickName =
                                            data.data.ownerName
                                        userData.userName = username
                                        userData.ownerAge =
                                            data.data.ownerAge.toString()
                                        userData.phonenumber = data.data.ownerPhone
                                        userData.ownerSex =
                                            data.data.ownerSex
                                        userData.ownerId =
                                            data.data.ownerId.toString()
                                        userData.ownerRoomNum = data.data.ownerRoomNum

                                        if (MyApplication.getInstance().db.userDao().getUserById(userData.userId) == null) {
                                            try {
                                                MyApplication.getInstance().db.userDao()
                                                    .insert(userData)
                                            }catch (e : Exception){
                                                e.printStackTrace();
                                            }
                                        } else {
                                            MyApplication.getInstance().db.userDao()
                                                .updateUser(userData)
                                        }
                                        runOnUiThread {
                                            success.invoke()
                                        }
                                    }
                                }catch (e : JSONException) {
                                    e.printStackTrace();
                                }
                            }
                        })
                    } else {
                        runOnUiThread {
                            toast("errorCode:" + data.status + ", errorMessage:" + data.error)
                        }
                    }
                }
            }
        })

    }

}