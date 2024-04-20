package com.yjkj.service_recoder.ui.servicepunch

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yjkj.service_recoder.Application
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding

class ServicePunchViewModel : BaseViewModel() {

    val servicePunchDecoration = ObservableField<RecyclerView.ItemDecoration>()
    val servicePunchGridLayoutManager = ObservableField<GridLayoutManager>()
    val items = ObservableArrayList<ServicePunchItemViewModel>()
    val itemBinding = ItemBinding.of<ServicePunchItemViewModel>(BR.viewModel,R.layout.service_punch_item_layout)
    val servicePunchItemFlow = MutableSharedFlow<Int>()

    init {
        loadData()
    }

    private fun loadData(){
        items.add(ServicePunchItemViewModel(this,R.drawable.group_311,0))
        items.add(ServicePunchItemViewModel(this,R.drawable.group_312,1))
        items.add(ServicePunchItemViewModel(this,R.drawable.group_313,2))
        items.add(ServicePunchItemViewModel(this,R.drawable.group_314,3))
        items.add(ServicePunchItemViewModel(this,R.drawable.group_315,4))
        items.add(ServicePunchItemViewModel(this,R.drawable.group_316,5))
        items.add(ServicePunchItemViewModel(this,R.drawable.group_317,6))
        items.add(ServicePunchItemViewModel(this,R.drawable.group_318,7))
        items.add(ServicePunchItemViewModel(this,R.drawable.group_319,8))
        items.add(ServicePunchItemViewModel(this,R.drawable.group_320,9))
        items.add(ServicePunchItemViewModel(this,R.drawable.group_321,10))
        items.add(ServicePunchItemViewModel(this,R.drawable.group_322,11))
        items.add(ServicePunchItemViewModel(this,R.drawable.group_323,12))
    }
}

class ServicePunchItemViewModel(val viewModel: ServicePunchViewModel,val icon : Int,val position : Int){
    val imageIcon = ObservableField<Int>().apply {
        set(icon)
    }


    fun itemClick(){
        viewModel.viewModelScope.launch {
            viewModel.servicePunchItemFlow.emit(position)
        }
    }
}