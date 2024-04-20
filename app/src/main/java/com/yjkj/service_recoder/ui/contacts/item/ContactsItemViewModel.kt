package com.yjkj.service_recoder.ui.contacts.item

import androidx.databinding.ObservableField
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.ui.contacts.ContactsViewModel

class ContactsItemViewModel(val viewModel: ContactsViewModel) {

    val avatar = ObservableField(R.mipmap.ic_launcher)
    val roomId = ObservableField("891")
    val personalName = ObservableField("xxx")
    val gender = ObservableField("男")
    val age = ObservableField("${18}岁")

    fun del(){
        viewModel.launch {
            viewModel.contactsItemClickFlow.emit(Unit)
        }
    }
}