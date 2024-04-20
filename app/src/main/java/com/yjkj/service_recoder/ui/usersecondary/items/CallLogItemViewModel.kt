package com.yjkj.service_recoder.ui.usersecondary.items

import androidx.databinding.ObservableField
import com.yjkj.service_recoder.ui.usersecondary.UserSecondaryVieModel

class CallLogItemViewModel(val viewModel: UserSecondaryVieModel) {

    val serviceDate = ObservableField("18:45")
    val serviceType = ObservableField("SOS服务")

    fun agree(){
        viewModel.launch {
            viewModel.callLogItemClickFlow.emit(Pair(CLICK_TYPE_AGREE,""))
        }
    }

    fun disagree(){
        viewModel.launch {
            viewModel.callLogItemClickFlow.emit(Pair(CLICK_TYPE_DISAGREE,""))
        }
    }

    companion object{
        const val CLICK_TYPE_AGREE = "click_type_agree"
        const val CLICK_TYPE_DISAGREE = "click_type_disagree"
    }
}