package com.yjkj.service_recoder.ui.homepage

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.bigkoo.pickerview.utils.LunarCalendar
import com.google.gson.Gson
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.java.bean.NewWeatherBean
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
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Timer
import java.util.TimerTask
import java.util.logging.SimpleFormatter
import kotlin.concurrent.schedule

class HomePageViewModel : BaseViewModel() {

    //联系人列表
    val contactsItems = ObservableArrayList<ContactsItemViewModel>()
    val contactsItemBind = ItemBinding.of<ContactsItemViewModel>(BR.viewModel,R.layout.contacts_item_layout)
    val scrollBarLayout = ObservableField<MotionLayout>()
    val userAvatarClickFlow = MutableSharedFlow<Unit>()
    val userItemClickFlow = MutableSharedFlow<Unit>()

    //天气相关
    val currentTime = ObservableField("")
    val currentDate = ObservableField("")
    val currentWeek = ObservableField("")
    val lunarCalendar = ObservableField("")
    val temperature = ObservableField("")
    val weatherState = ObservableField("")
    val temperatureRange = ObservableField("")
    val city = ObservableField("")
    val weatherStateImage = ObservableField(R.drawable.good_weather)
    val uvValue = ObservableField("")
    val humValue = ObservableField("")
    val aopValue = ObservableField("")
    var roomnumber = ObservableField(UserDataHelper.roomNumber())

    val datFragment = SimpleDateFormat("HH:mm")
    val timer = Timer()

    init {
        getRoomList()
        getWeather()
        ShowLocalTime()
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

    fun ShowLocalTime(){
        timer.schedule(0L, 1000L){
            currentTime.set(datFragment.format(Date()))
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
                            contactsItems.clear()
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

    //定位和天气
    fun getWeather(){
        OkHttpUtil.getInstance().doGet(API.weather_new, object :
            Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    response.body?.let {
                        val data = Gson().fromJson(it.string(), NewWeatherBean::class.java)
                        if (data.code == 200){
                            var formatter = SimpleDateFormat("MM月dd日")
                            currentDate.set(formatter.format(Date()))

                            val weekdays = arrayOf("日", "一", "二", "三", "四", "五", "六")
                            val cal = Calendar.getInstance();
                            cal.time = Date();
                            var w = cal[Calendar.DAY_OF_WEEK] - 1;
                            if (w < 0){
                                w= 0;
                            }
                            currentWeek.set("  周" + weekdays[w])
//                            lunarCalendar.set(LunarCalendar)
                            if (data.data.lives != null && data.data.lives.size != 0){
                                temperature.set(data.data.lives.get(0).temperature + "℃")
                                weatherState.set(data.data.lives.get(0).weather)
                                city.set(data.data.lives.get(0).province + " " + data.data.lives.get(0).city)
                                uvValue.set(data.data.lives.get(0).winddirection)
                                humValue.set(data.data.lives.get(0).windpower)
                                aopValue.set(data.data.lives.get(0).humidity + "%")
                            }
                        }else{
                            toast(data.msg)
                        }
                    }
                }
            }
        })
    }

    fun updateRoomId(id : String){
        roomnumber.set(id)
        getRoomList()
    }

}