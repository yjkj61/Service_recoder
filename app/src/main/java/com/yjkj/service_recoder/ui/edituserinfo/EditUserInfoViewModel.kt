package com.yjkj.service_recoder.ui.edituserinfo

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseViewModel
import com.yjkj.service_recoder.ui.edituserinfo.item.UserBasicEditItemViewModel
import com.yjkj.service_recoder.ui.userinfo.item.UserBasicItemViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind

class EditUserInfoViewModel : BaseViewModel() {
    //用户头像
    val userAvatar = ObservableField(R.mipmap.ic_launcher)

    //用户资料列表
    val userBasicItems = ObservableArrayList<UserBasicEditItemViewModel>()
    val userBasicItemBind = OnItemBind<UserBasicEditItemViewModel>{itemBinding, position, item ->
        when(item.data.first){
            "性别" ->{
                itemBinding.set(BR.viewModel,R.layout.user_basic_info_item_edit_layout2)
            }
            "身高","体重"->{
                itemBinding.set(BR.viewModel,R.layout.user_basic_info_item_edit_layout4)
            }
            "房间床位"->{
                itemBinding.set(BR.viewModel,R.layout.user_basic_info_item_edit_layout3)
            }
            else ->{
                itemBinding.set(BR.viewModel,R.layout.user_basic_info_item_edit_layout)
            }
        }
    }

    val userBasicItems2 = ObservableArrayList<UserBasicEditItemViewModel>()
    //val userBasicItemBind2 = ItemBinding.of<UserBasicEditItemViewModel>(BR.viewModel,R.layout.user_basic_info_item_edit_layout)
    val userBasicItemBind2 = OnItemBind<UserBasicEditItemViewModel>{itemBinding, position, item ->  
        when(item.data.first){
            "住户属性"->{
                itemBinding.set(BR.viewModel,R.layout.user_basic_info_item_edit_layout3)
            }
            "自理评估"->{
                itemBinding.set(BR.viewModel,R.layout.user_basic_info_item_edit_layout2)
            }
            else ->{
                itemBinding.set(BR.viewModel,R.layout.user_basic_info_item_edit_layout)
            }
        }
    }

    val userBasicItems3 = ObservableArrayList<UserBasicEditItemViewModel>()
    val userBasicItemBind3 = ItemBinding.of<UserBasicEditItemViewModel>(BR.viewModel,R.layout.user_basic_info_item_edit_layout)

    val userBasicItems4 = ObservableArrayList<UserBasicEditItemViewModel>()
    val userBasicItemBind4 = ItemBinding.of<UserBasicEditItemViewModel>(BR.viewModel,R.layout.user_basic_info_item_edit_layout2)


    init {
        initUserBasicInfo()
    }

    fun initUserBasicInfo(){
        userBasicItems.add(UserBasicEditItemViewModel(Pair("姓名",""),"点击输入姓名"))
        userBasicItems.add(UserBasicEditItemViewModel(Pair("年龄",""),"点击输入年龄"))
        userBasicItems.add(UserBasicEditItemViewModel(Pair("身份证号",""),"点击输入身份证号"))
        userBasicItems.add(UserBasicEditItemViewModel(Pair("入住时间",""),"0000-00-00 00:00"))
        userBasicItems.add(UserBasicEditItemViewModel(Pair("性别",""),"选择性别"))
        userBasicItems.add(UserBasicEditItemViewModel(Pair("身高",""),"000","cm"))
        userBasicItems.add(UserBasicEditItemViewModel(Pair("体重",""),"000","kg"))
        userBasicItems.add(UserBasicEditItemViewModel(Pair("房间床位",""),""))

        ////////
        userBasicItems2.add(UserBasicEditItemViewModel(Pair("机构名称","芜湖蕾娜范疗养机构"),""))
        userBasicItems2.add(UserBasicEditItemViewModel(Pair("住户属性","无"),""))
        userBasicItems2.add(UserBasicEditItemViewModel(Pair("自理评估","完全自理"),""))
        userBasicItems2.add(UserBasicEditItemViewModel(Pair("账户余额","¥2345"),""))
        userBasicItems2.add(UserBasicEditItemViewModel(Pair("家属1",""),"点击输入姓名"))
        userBasicItems2.add(UserBasicEditItemViewModel(Pair("联系电话",""),"点击输入手机号"))
        userBasicItems2.add(UserBasicEditItemViewModel(Pair("家属2","无"),"点击输入姓名"))
        userBasicItems2.add(UserBasicEditItemViewModel(Pair("联系电话",""),"点击输入手机号"))
        ////////
        userBasicItems3.add(UserBasicEditItemViewModel(Pair("主管护士",""),"点击输入姓名"))
        userBasicItems3.add(UserBasicEditItemViewModel(Pair("联系电话",""),"点击输入手机号"))
        userBasicItems3.add(UserBasicEditItemViewModel(Pair("主管医生",""),"点击输入姓名"))
        userBasicItems3.add(UserBasicEditItemViewModel(Pair("联系电话",""),"点击输入手机号"))
        userBasicItems3.add(UserBasicEditItemViewModel(Pair("主管护工",""),"点击输入姓名"))
        userBasicItems3.add(UserBasicEditItemViewModel(Pair("联系电话",""),"点击输入手机号"))
        userBasicItems3.add(UserBasicEditItemViewModel(Pair("主管领导",""),"点击输入姓名"))
        userBasicItems3.add(UserBasicEditItemViewModel(Pair("联系电话",""),"点击输入手机号"))
        ///////
        userBasicItems4.add(UserBasicEditItemViewModel(Pair("病史",""),""))
        userBasicItems4.add(UserBasicEditItemViewModel(Pair("有无患癌史",""),""))
        userBasicItems4.add(UserBasicEditItemViewModel(Pair("生活中有无以下情况",""),""))
        userBasicItems4.add(UserBasicEditItemViewModel(Pair("有无家族病史",""),""))
        userBasicItems4.add(UserBasicEditItemViewModel(Pair("有无家族病史",""),""))
        userBasicItems4.add(UserBasicEditItemViewModel(Pair("有无长期需要服用的药物",""),""))
        userBasicItems4.add(UserBasicEditItemViewModel(Pair("有无长期需要定期体检",""),""))
    }
}