package com.yjkj.service_recoder.ui.userinfo

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.yjkj.service_recoder.MyApplication
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseViewModel
import com.yjkj.service_recoder.ui.userinfo.item.DeviceItemViewModel
import com.yjkj.service_recoder.ui.userinfo.item.MedicalItemTagViewModel
import com.yjkj.service_recoder.ui.userinfo.item.UserBasicItemViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding

class UserInfoViewModel : BaseViewModel() {

    //用户头像
    val userAvatar = ObservableField(R.mipmap.ic_launcher)

    //用户资料列表
    val userBasicItems = ObservableArrayList<UserBasicItemViewModel>()
    val userBasicItemBind = ItemBinding.of<UserBasicItemViewModel>(BR.viewModel,R.layout.user_basic_info_item_layout)

    val userBasicItems2 = ObservableArrayList<UserBasicItemViewModel>()
    val userBasicItemBind2 = ItemBinding.of<UserBasicItemViewModel>(BR.viewModel,R.layout.user_basic_info_item_layout)

    val userBasicItems3 = ObservableArrayList<UserBasicItemViewModel>()
    val userBasicItemBind3 = ItemBinding.of<UserBasicItemViewModel>(BR.viewModel,R.layout.user_basic_info_item_layout)

    //设备列表
    val deviceItemDecoration = ObservableField<RecyclerView.ItemDecoration>()
    val deviceGridLayoutManager = ObservableField<GridLayoutManager>()
    val deviceItems = ObservableArrayList<DeviceItemViewModel>()
    val deviceItemBinding = ItemBinding.of<DeviceItemViewModel>(BR.viewModel, R.layout.device_item_layout)

    //医疗信息列表
    val medicalItems = ObservableArrayList<UserBasicItemViewModel>()
    val medicalItemBind = ItemBinding.of<UserBasicItemViewModel>(BR.viewModel,R.layout.user_basic_info_item_layout)


    //警惕疾病列表
    val alertDiseaseLayoutManager = ObservableField<FlexboxLayoutManager>().apply {
        set(FlexboxLayoutManager(MyApplication.context,FlexDirection.ROW))
    }
    val alertDiseaseItems = ObservableArrayList<MedicalItemTagViewModel>()
    val alertDiseaseItemBinding = ItemBinding.of<MedicalItemTagViewModel>(BR.viewModel,R.layout.alert_disease_item_layout)

    //定期检查列表
    val regularCheckingLayoutManager = ObservableField<FlexboxLayoutManager>().apply {
        set(FlexboxLayoutManager(MyApplication.context,FlexDirection.ROW))
    }
    val regularCheckingItems = ObservableArrayList<MedicalItemTagViewModel>()
    val regularCheckingItemBinding = ItemBinding.of<MedicalItemTagViewModel>(BR.viewModel,R.layout.regular_checking_item_layout)

    //医生建议
    val doctorSuggest = ObservableField("清淡饮食，避免高脂肪、高糖分食物")

    init {
        initUserBasicItems()
        initMedicalItems()
    }

    fun initUserBasicItems(){
        userBasicItems.add(UserBasicItemViewModel(Pair("姓名","xxxx")))
        userBasicItems.add(UserBasicItemViewModel(Pair("年龄","xxxx")))
        userBasicItems.add(UserBasicItemViewModel(Pair("身份证号","xxxx")))
        userBasicItems.add(UserBasicItemViewModel(Pair("入住时间","xxxx")))
        userBasicItems.add(UserBasicItemViewModel(Pair("性别","xxxx")))
        userBasicItems.add(UserBasicItemViewModel(Pair("用户属性","xxxx")))
        userBasicItems.add(UserBasicItemViewModel(Pair("机构名称","芜湖蕾娜范疗养机构")))
        userBasicItems.add(UserBasicItemViewModel(Pair("房间床位","xxxx")))
        ///////
        userBasicItems2.add(UserBasicItemViewModel(Pair("","无")))
        userBasicItems2.add(UserBasicItemViewModel(Pair("","无")))
        userBasicItems2.add(UserBasicItemViewModel(Pair("","无")))
        userBasicItems2.add(UserBasicItemViewModel(Pair("账户余额","¥2345")))
        userBasicItems2.add(UserBasicItemViewModel(Pair("","xxxxx")))
        userBasicItems2.add(UserBasicItemViewModel(Pair("","123 888 8907")))
        userBasicItems2.add(UserBasicItemViewModel(Pair("家属2","无")))
        userBasicItems2.add(UserBasicItemViewModel(Pair("联系电话","123 888 9999")))
        /////
        userBasicItems3.add(UserBasicItemViewModel(Pair("主管护士","无")))
        userBasicItems3.add(UserBasicItemViewModel(Pair("联系电话","无")))
        userBasicItems3.add(UserBasicItemViewModel(Pair("主管医生","无")))
        userBasicItems3.add(UserBasicItemViewModel(Pair("联系电话","无")))
        userBasicItems3.add(UserBasicItemViewModel(Pair("主管护工","无")))
        userBasicItems3.add(UserBasicItemViewModel(Pair("联系电话","无")))
        userBasicItems3.add(UserBasicItemViewModel(Pair("主管领导","无")))
        userBasicItems3.add(UserBasicItemViewModel(Pair("联系电话","无")))

    }

    fun initDeviceItems(){
        deviceItems.add(DeviceItemViewModel(this,R.drawable.group_284,DeviceItemViewModel.STATE_1))
        deviceItems.add(DeviceItemViewModel(this,R.drawable.group_328,DeviceItemViewModel.STATE_0))
        deviceItems.add(DeviceItemViewModel(this,R.drawable.group_299,DeviceItemViewModel.STATE_1))
        deviceItems.add(DeviceItemViewModel(this,R.drawable.group_303,DeviceItemViewModel.STATE_2))
        deviceItems.add(DeviceItemViewModel(this,R.drawable.group_304,DeviceItemViewModel.STATE_2))
        deviceItems.add(DeviceItemViewModel(this,R.drawable.group_329,DeviceItemViewModel.STATE_0))
    }

    fun initMedicalItems(){
        repeat(20){
            medicalItems.add(UserBasicItemViewModel(Pair("阿司匹林","1粒/次，3次/日")))
        }
        repeat(5){
            alertDiseaseItems.add(MedicalItemTagViewModel("高血压"))
        }
        repeat(2){
            regularCheckingItems.add(MedicalItemTagViewModel("脑部CT（一个月内）"))
        }
    }

    /**
     * 这样设置上的layoutManger必须在页面销毁后置空
     */
    fun clear(){
        alertDiseaseLayoutManager.set(null)
        deviceGridLayoutManager.set(null)
        regularCheckingLayoutManager.set(null)
    }
}