package com.yjkj.service_recoder.ui.edituserinfo.item

import androidx.databinding.ObservableField

class UserBasicEditItemViewModel(val data : Pair<String,String>,val hint : String,val unit : String = "") {

    val itemTitle = ObservableField(data.first)
    val itemHint = ObservableField(hint)
    val itemValue = ObservableField(data.second)

    val itemUnit = ObservableField(unit)


}