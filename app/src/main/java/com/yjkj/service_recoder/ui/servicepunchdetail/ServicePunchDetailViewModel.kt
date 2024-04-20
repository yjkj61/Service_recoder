package com.yjkj.service_recoder.ui.servicepunchdetail

import androidx.databinding.ObservableField
import com.yjkj.service_recoder.library.base.BaseViewModel

class ServicePunchDetailViewModel : BaseViewModel() {

    val title = ObservableField<String>()

    //val serviceStateText = ObservableField<String>("开始录制")
    val serviceStateText = ObservableField<String>("完成服务")
}