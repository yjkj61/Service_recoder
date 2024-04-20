package com.yjkj.service_recoder.ui.userinfo.item

import androidx.databinding.ObservableField
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.ui.userinfo.UserInfoViewModel

class DeviceItemViewModel(val viewModel: UserInfoViewModel,val iconRes : Int,val state : Int) {

    val icon = ObservableField(iconRes)

    val stateText = ObservableField("")
    val stateTextColor = ObservableField(R.color.white)
    val stateTextBg = ObservableField(R.drawable.blue_bg_5_dp_corner_shape)

    val onlineText = ObservableField("")
    val onlineTextColor = ObservableField(R.color.color_333333)
    val onlineTextBg = ObservableField(R.drawable.gray_bg_5_dp_corner_shape)

    init {
        when(state){
            STATE_0->{
                stateText.set("有")
                stateTextColor.set(R.color.white)
                stateTextBg.set(R.drawable.blue_bg_5_dp_corner_shape)
                onlineText.set("在线")
                onlineTextColor.set(R.color.white)
                onlineTextBg.set(R.drawable.blue_bg_5_dp_corner_shape)
            }
            STATE_1->{
                stateText.set("有")
                stateTextColor.set(R.color.white)
                stateTextBg.set(R.drawable.blue_bg_5_dp_corner_shape)
                onlineText.set("离线")
                onlineTextColor.set(R.color.color_333333)
                onlineTextBg.set(R.drawable.gray_bg_5_dp_corner_shape)
            }
            STATE_2->{
                stateText.set("无")
                stateTextColor.set(R.color.color_333333)
                stateTextBg.set(R.drawable.gray_bg_5_dp_corner_shape)
                onlineText.set("离线")
                onlineTextColor.set(R.color.color_333333)
                onlineTextBg.set(R.drawable.gray_bg_5_dp_corner_shape)
            }
        }
    }

    companion object{
        //有且在线
        const val STATE_0 = 0
        //有且离线
        const val STATE_1 = 1
        //无
        const val STATE_2 = 2
    }

}