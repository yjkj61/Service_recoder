package com.yjkj.service_recoder.ui.contacts

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseViewModel
import com.yjkj.service_recoder.ui.contacts.item.ContactsItemViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import me.tatarka.bindingcollectionadapter2.ItemBinding

class ContactsViewModel : BaseViewModel() {

    val userName = ObservableField("")
    val buildingNumber = ObservableField("")
    val roomNumber = ObservableField("")

    //联系人员列表
    val contactsItems = ObservableArrayList<ContactsItemViewModel>()
    val contactsItemBinding = ItemBinding.of<ContactsItemViewModel>(BR.viewModel, R.layout.contacts_list_item_layout)
    val contactsGridLayoutManager = ObservableField<GridLayoutManager>()
    val contactsItemDecoration = ObservableField<RecyclerView.ItemDecoration>()
    val contactsItemClickFlow = MutableSharedFlow<Unit>()

    fun initPaymentItems(){
        repeat(30){
            contactsItems.add(ContactsItemViewModel(this))
        }
    }

    fun launchSearch(){
        if(userName.get().isNullOrEmpty() && buildingNumber.get().isNullOrEmpty() && roomNumber.get().isNullOrEmpty()){
            return
        }
    }

    fun searchReset(){
        userName.set("")
        buildingNumber.set("")
        roomNumber.set("")
    }


    fun clear(){
        contactsGridLayoutManager.set(null)
        contactsItemDecoration.set(null)
    }

}