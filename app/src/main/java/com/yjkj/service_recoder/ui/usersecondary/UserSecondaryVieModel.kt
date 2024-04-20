package com.yjkj.service_recoder.ui.usersecondary

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseViewModel
import com.yjkj.service_recoder.ui.usersecondary.items.CallLogItemViewModel
import com.yjkj.service_recoder.ui.usersecondary.items.ContactsItemViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import me.tatarka.bindingcollectionadapter2.ItemBinding

class UserSecondaryVieModel : BaseViewModel() {
    val contactsItems = ObservableArrayList<ContactsItemViewModel>()
    val contactsItemBind = ItemBinding.of<ContactsItemViewModel>(BR.viewModel, R.layout.contacts_item_layout2)
    val scrollBarLayout = ObservableField<MotionLayout>()
    val userAvatarClickFlow = MutableSharedFlow<Unit>()
    val userItemClickFlow = MutableSharedFlow<Unit>()

    //用户个人信息
    val userAvatarSrc = ObservableField<Int>(R.mipmap.ic_launcher)
    val userPhoneNumber = ObservableField("13686462131")
    val userName = ObservableField("用户名")
    val userGender = ObservableField("男")
    val userAge = ObservableField("37")
    val bedNUmber = ObservableField("6-1-901")
    val bedStatus = ObservableField("在床")
    val phStatus = ObservableField("好")
    val serviceStatus = ObservableField("待服务")
    val nurseLevel = ObservableField("一级")

    //呼叫服务记录列表
    val callLogItems = ObservableArrayList<CallLogItemViewModel>()
    val callLogItemBinding = ItemBinding.of<CallLogItemViewModel>(BR.viewModel,R.layout.call_log_item_layout)
    val callLogItemClickFlow = MutableSharedFlow <Pair<String,String>>()

    init {
        initContactItems()
        initCallLogItems()
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

    fun initCallLogItems(){
        callLogItems.add(CallLogItemViewModel(this))
        callLogItems.add(CallLogItemViewModel(this))
        callLogItems.add(CallLogItemViewModel(this))
        callLogItems.add(CallLogItemViewModel(this))
        callLogItems.add(CallLogItemViewModel(this))
        callLogItems.add(CallLogItemViewModel(this))
        callLogItems.add(CallLogItemViewModel(this))
        callLogItems.add(CallLogItemViewModel(this))
        callLogItems.add(CallLogItemViewModel(this))
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
                it.progress = progress
            }



        }
    }
}