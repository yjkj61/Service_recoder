package com.yjkj.service_recoder.java.ui.personal.item

import android.view.View
import androidx.databinding.ObservableField
import com.yjkj.service_recoder.java.ui.personal.PersonalViewModel

class CallLogItemViewModel(val viewModel: PersonalViewModel) {

    val serviceDate = ObservableField("18:45")
    val serviceType = ObservableField("SOS服务")
    var btnagree = ObservableField("接受")
    var btndisagree = ObservableField("拒绝")
    var btnagreevisiable = ObservableField(View.VISIBLE);
    var btndisagreevisiable = ObservableField(View.VISIBLE);

    fun agree(){
        viewModel.launch {
            btnagreevisiable.set(View.GONE)
            btndisagreevisiable.set(View.VISIBLE)
            btndisagree.set("已接受")
//            viewModel.callLogItemClickFlow.emit(Pair(CLICK_TYPE_AGREE,""))
        }
    }

    fun disagree(){
        viewModel.launch {
            btnagreevisiable.set(View.GONE)
            btndisagreevisiable.set(View.VISIBLE)
            btndisagree.set("已拒绝")
//            viewModel.callLogItemClickFlow.emit(Pair(CLICK_TYPE_DISAGREE,""))
        }
    }

    companion object{
        const val CLICK_TYPE_AGREE = "click_type_agree"
        const val CLICK_TYPE_DISAGREE = "click_type_disagree"
    }
}