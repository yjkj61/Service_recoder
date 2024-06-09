package com.yjkj.service_recoder.java.ui.personal.item

import android.database.Observable
import androidx.databinding.ObservableField
import com.yjkj.service_recoder.java.entity.ContactsUserEntity
import com.yjkj.service_recoder.java.ui.personal.PersonalViewModel

class UserContactsItemViewModel(val viewModel: PersonalViewModel, data : ContactsUserEntity) {

    val userRelation = ObservableField(data.contactsJobName)
    val userRelationName = ObservableField(data.contactsName)
    val userRelationPhoneNumber = ObservableField(data.contactsPhoneNumber)

}