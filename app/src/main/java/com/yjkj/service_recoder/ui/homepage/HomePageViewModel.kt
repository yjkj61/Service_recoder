package com.yjkj.service_recoder.ui.homepage

import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.java.bean.RoomListEntity
import com.yjkj.service_recoder.java.data.UserDataHelper
import com.yjkj.service_recoder.java.entity.LoginEntity
import com.yjkj.service_recoder.java.http.OkHttpUtil
import com.yjkj.service_recoder.java.http.medicalservice.API
import com.yjkj.service_recoder.java.utils.ToolUtils
import com.yjkj.service_recoder.library.base.BaseViewModel
import com.yjkj.service_recoder.library.utils.ext.toast
import com.yjkj.service_recoder.ui.homepage.items.ContactsItemViewModel

import kotlinx.coroutines.flow.MutableSharedFlow
import me.tatarka.bindingcollectionadapter2.ItemBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class HomePageViewModel : BaseViewModel() {

    //联系人列表
    val contactsItems = ObservableArrayList<ContactsItemViewModel>()
    val contactsItemBind = ItemBinding.of<ContactsItemViewModel>(BR.viewModel,R.layout.contacts_item_layout)
    val scrollBarLayout = ObservableField<MotionLayout>()
    val userAvatarClickFlow = MutableSharedFlow<Unit>()
    val userItemClickFlow = MutableSharedFlow<Unit>()

    //天气相关
    val currentTime = ObservableField("06:35")
    val currentDate = ObservableField("9月8日")
    val currentWeek = ObservableField("周五")
    val lunarCalendar = ObservableField("庚子年七月廿四")
    val temperature = ObservableField("22℃")
    val weatherState = ObservableField("小雨转晴")
    val temperatureRange = ObservableField("22℃~28℃")
    val city = ObservableField("安徽·芜湖")
    val weatherStateImage = ObservableField(R.mipmap.ic_launcher)
    val uvValue = ObservableField("低")
    val humValue = ObservableField("61%")
    val aopValue = ObservableField("0毫米")
    val roomnumber = ObservableField(UserDataHelper.roomNumber())

    init {
        getRoomList()
        scrollBarLayout.get()?.let {

        }
    }

    val recyclerViewScrollChangeListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val offset = recyclerView.computeVerticalScrollOffset()
            val extent = recyclerView.computeVerticalScrollExtent()
            val range = recyclerView.computeVerticalScrollRange()

            val percentage = 100.0f * offset / (range - extent).toFloat()
            val progress = percentage/100f
            scrollBarLayout.get()?.let {
                it?.progress = progress
            }
        }
    }

    fun getRoomList(){
        OkHttpUtil.getInstance().doGet(API.OwnerRoomsList + UserDataHelper.roomNumber(), object :
            Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    response.body?.let {
                        val data = Gson().fromJson(it.string(), RoomListEntity::class.java)
                        if (data.code == 200){
                            data.data.forEach {
                                contactsItems.add(ContactsItemViewModel(this@HomePageViewModel,it))
                            }
                        }else{
                            toast(data.msg)
                        }
                    }
                }
            }
        })
    }
}