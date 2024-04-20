package com.yjkj.service_recoder.ui.usersecondary.items

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.ui.usersecondary.UserSecondaryVieModel
import kotlinx.coroutines.launch

class ContactsItemViewModel(val viewModel: UserSecondaryVieModel,val callState : Int) {

    val stateColor = ObservableField(R.color.color_4580FF)
    val sosStateVisibility = ObservableField<Int>()
    val callingStateVisibility = ObservableField<Int>()

    val userName = ObservableField("姓名")
    val bedNumber = ObservableField("001")

    val userAvatarImg = ObservableField(R.mipmap.ic_launcher)

    init {
        setState()
    }

    fun setState(){
        when(callState){
            SOS_STATE ->{
                //sos
                sosStateVisibility.set(View.VISIBLE)
                callingStateVisibility.set(View.INVISIBLE)
                stateColor.set(R.color.color_FFAF1C)
            }
            CALLING_STATE ->{
                //呼叫中
                sosStateVisibility.set(View.INVISIBLE)
                callingStateVisibility.set(View.VISIBLE)
                stateColor.set(R.color.color_4580FF)
            }
        }
    }

    /**
     * 点击用户头像
     */
    fun clickUserAvatar(){
        viewModel.viewModelScope.launch {
            viewModel.userAvatarClickFlow.emit(Unit)
        }
    }

    fun clickUserItem(){
        viewModel.viewModelScope.launch {
            viewModel.userItemClickFlow.emit(Unit)
        }
    }

    companion object{
        const val SOS_STATE = 0
        const val CALLING_STATE = 1
    }

}