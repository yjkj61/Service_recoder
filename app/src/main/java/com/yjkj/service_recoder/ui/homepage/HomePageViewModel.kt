package com.yjkj.service_recoder.ui.homepage

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseViewModel
import com.yjkj.service_recoder.ui.homepage.items.ContactsItemViewModel

import kotlinx.coroutines.flow.MutableSharedFlow
import me.tatarka.bindingcollectionadapter2.ItemBinding

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

    init {
        initContactItems()
        scrollBarLayout.get()?.let {

        }
    }

    fun initContactItems(){
        contactsItems.add(ContactsItemViewModel(this,ContactsItemViewModel.SOS_STATE))
        contactsItems.add(ContactsItemViewModel(this,ContactsItemViewModel.SOS_STATE))
        contactsItems.add(ContactsItemViewModel(this,ContactsItemViewModel.CALLING_STATE))
        contactsItems.add(ContactsItemViewModel(this,ContactsItemViewModel.CALLING_STATE))
        contactsItems.add(ContactsItemViewModel(this,ContactsItemViewModel.CALLING_STATE))
        contactsItems.add(ContactsItemViewModel(this,ContactsItemViewModel.CALLING_STATE))
        contactsItems.add(ContactsItemViewModel(this,ContactsItemViewModel.CALLING_STATE))
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
}