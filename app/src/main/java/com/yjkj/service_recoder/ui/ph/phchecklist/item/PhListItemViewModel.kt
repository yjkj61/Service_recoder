package com.yjkj.service_recoder.ui.ph.phchecklist.item

import android.database.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.yjkj.service_recoder.library.base.BaseViewModel
import com.yjkj.service_recoder.ui.ph.phchecklist.PhCheckListViewModel
import kotlinx.coroutines.launch

class PhListItemViewModel(val viewModel: PhCheckListViewModel,val text :String) {

    val phNameText = ObservableField<String>(text)


    fun itemClick(){
        viewModel.viewModelScope.launch{
            viewModel.itemClickFlow.emit(text)
        }
    }

}