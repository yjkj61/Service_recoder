package com.yjkj.service_recoder.java.ui.personal

import android.content.Intent
import android.net.Uri
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField

import com.yjkj.service_recoder.java.entity.ContactsUserEntity
import com.yjkj.service_recoder.java.entity.OwnerEntity
import com.yjkj.service_recoder.java.utils.ifNull
import com.yjkj.service_recoder.java.ui.personal.repo.PersonalRepo
import com.yjkj.service_recoder.java.ui.personal.item.CallLogItemViewModel
import com.yjkj.service_recoder.java.ui.personal.item.UserContactsItemViewModel
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import me.tatarka.bindingcollectionadapter2.ItemBinding
import com.yjkj.service_recoder.BR

class PersonalViewModel : BaseViewModel() {
    private val repo by lazy {
        PersonalRepo()
    }

    val userAvatarSrc = ObservableField<Any>(R.mipmap.ic_launcher)
    val userPhoneNumber = ObservableField("13686462131")
    val userName = ObservableField("用户名")
    val userGender = ObservableField("男")
    val userAge = ObservableField("37")
    val bedNUmber = ObservableField("6-1-901")
    val bedStatus = ObservableField("在床")
    val phStatus = ObservableField("好")
    val serviceStatus = ObservableField("待服务")
    val nurseLevel = ObservableField("一级")

    //用户联系人列表
    val userContactsItems = ObservableArrayList<UserContactsItemViewModel>()
    val userContactsItemBinding = ItemBinding.of<UserContactsItemViewModel>(BR.viewModel,R.layout.user_contacts_item_layout)
    val userContactsItemClickFlow = MutableSharedFlow <String>()

    //呼叫服务记录列表
    val callLogItems = ObservableArrayList<CallLogItemViewModel>()
    val callLogItemBinding = ItemBinding.of<CallLogItemViewModel>(BR.viewModel,R.layout.call_log_item_layout)
    val callLogItemClickFlow = MutableSharedFlow <Pair<String,String>>()

    var currOwner: OwnerEntity? = null;

    fun initUserInfo(ownerEntity: OwnerEntity){
        currOwner = ownerEntity;
        userAvatarSrc.set(ownerEntity.ownerPic)
        userPhoneNumber.set(ownerEntity.ownerPhone)
        userName.set(ownerEntity.ownerName)
        userGender.set(if(ownerEntity.ownerSex == "0") "女" else "男")
        userAge.set(ownerEntity.ownerAge.toString())
        bedNUmber.set(ownerEntity.ownerBedNum)
//        bedStatus.set(when(ownerEntity.dangerType?.ifNull { -1 }){
//            "0"->"离床"
//            "1"->"心率异常"
//            "2"->"呼吸异常"
//            else->""
//        })
//
//        phStatus.set(ownerEntity.physiologicalState?.ifNull { "" })
//        serviceStatus.set("")
//        nurseLevel.set(ownerEntity.ownerNurseAssess)
    }

    fun initUserContactsItems(){
        userContactsItems.clear()
        if (currOwner?.ownerNursingName != null){
            userContactsItems.add(UserContactsItemViewModel(this, ContactsUserEntity("护工",
                currOwner?.ownerNursingName.toString(), currOwner?.ownerNursingPhone.toString())))
        }


        if (currOwner?.ownerNursingNameTow != null){
            userContactsItems.add(UserContactsItemViewModel(this,ContactsUserEntity("护工",
                currOwner?.ownerNursingNameTow.toString(), currOwner?.ownerNursingPhoneTow.toString())))
        }

        if (currOwner?.ownerDoctorName !=null){
            userContactsItems.add(UserContactsItemViewModel(this,ContactsUserEntity("医生",
                currOwner?.ownerDoctorName.toString(), currOwner?.ownerDoctorPhone.toString())))
        }

        if (currOwner?.ownerSupervisorDoctorName!=null){
            userContactsItems.add(UserContactsItemViewModel(this,ContactsUserEntity("主督",
                currOwner?.ownerSupervisorDoctorName.toString(), currOwner?.ownerSupervisorDoctorPhone.toString())))
        }

        if (currOwner?.ownerNurse !=null){
            userContactsItems.add(UserContactsItemViewModel(this,ContactsUserEntity("护士",
                currOwner?.ownerNurse.toString(), currOwner?.ownerNursePhone.toString())))
        }

        if (currOwner?.ownerNurseTow !=null){
            userContactsItems.add(UserContactsItemViewModel(this,ContactsUserEntity("护士",
                currOwner?.ownerNurseTow.toString(), currOwner?.ownerNursePhoneTow.toString())))
        }



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

    fun getInfo(id : Int) {
        launch({repo.requestInfo(id)}, onSuccess = {
            bedStatus.set(when(it.dangerType?.ifNull { "0" }){
                "0"->"离床"
                "1"->"心率异常"
                "2"->"呼吸异常"
                else->""
            })

            phStatus.set(it.physiologicalState?.ifNull { "" })
            serviceStatus.set("")
            nurseLevel.set(it.ownerNurseAssess)
        })

    }


}