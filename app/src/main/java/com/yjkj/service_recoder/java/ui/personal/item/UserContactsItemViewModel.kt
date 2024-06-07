package com.yjkj.property_management.ui.page.personal.item

import android.database.Observable
import androidx.databinding.ObservableField
import com.yjkj.property_management.entity.ContactsUserEntity
import com.yjkj.property_management.ui.page.personal.PersonalViewModel

class UserContactsItemViewModel(val viewModel: PersonalViewModel,data : ContactsUserEntity) {

    val userRelation = ObservableField(data.contactsJobName)
    val userRelationName = ObservableField(data.contactsName)
    val userRelationPhoneNumber = ObservableField(data.contactsPhoneNumber)

}