package com.yjkj.service_recoder.ui.ph.phchecklist

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseViewModel
import com.yjkj.service_recoder.ui.contacts.item.ContactsItemViewModel
import com.yjkj.service_recoder.ui.ph.phchecklist.item.PhListItemViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import me.tatarka.bindingcollectionadapter2.ItemBinding

class PhCheckListViewModel : BaseViewModel() {

    //联系人员列表
    val items = ObservableArrayList<PhListItemViewModel>()
    val itemBinding = ItemBinding.of<PhListItemViewModel>(BR.viewModel, R.layout.ph_list_item_layout)
    val gridLayoutManager = ObservableField<GridLayoutManager>()
    val itemDecoration = ObservableField<RecyclerView.ItemDecoration>()
    val itemClickFlow = MutableSharedFlow<String>()

    fun initItems(){

        items.add(PhListItemViewModel(this,"血压"))
        items.add(PhListItemViewModel(this,"体温"))
        items.add(PhListItemViewModel(this,"血氧"))
        items.add(PhListItemViewModel(this,"脉搏"))
        items.add(PhListItemViewModel(this,"血糖"))
        items.add(PhListItemViewModel(this,"尿酸"))
        items.add(PhListItemViewModel(this,"胆固醇"))
        items.add(PhListItemViewModel(this,"血脂四项"))
    }

}