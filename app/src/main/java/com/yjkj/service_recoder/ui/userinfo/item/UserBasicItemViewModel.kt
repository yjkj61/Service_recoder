package com.yjkj.service_recoder.ui.userinfo.item

import androidx.databinding.ObservableField

class UserBasicItemViewModel(val data : Pair<String,String>) {

    val itemTitle = ObservableField(data.first)
    val itemValue = ObservableField(data.second)


}