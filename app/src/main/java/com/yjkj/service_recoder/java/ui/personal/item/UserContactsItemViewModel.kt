package com.yjkj.service_recoder.java.ui.personal.item

import android.content.Intent
import android.database.Observable
import android.net.Uri
import androidx.databinding.ObservableField
import com.yjkj.service_recoder.java.entity.ContactsUserEntity
import com.yjkj.service_recoder.java.ui.personal.PersonalViewModel

class UserContactsItemViewModel(val viewModel: PersonalViewModel, data : ContactsUserEntity) {

    val userRelation = ObservableField(data.contactsJobName)
    val userRelationName = ObservableField(data.contactsName)
    val userRelationPhoneNumber = ObservableField(data.contactsPhoneNumber)

    fun callphone(){
        viewModel.launch {
            viewModel.userContactsItemClickFlow.emit(userRelationPhoneNumber.get().toString())
        }
    }

    companion object{
        const val CLICK_TYPE_CALLPHONE = "click_type_callphone"
    }

}