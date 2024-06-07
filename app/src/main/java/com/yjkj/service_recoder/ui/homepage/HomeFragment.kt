package com.yjkj.service_recoder.ui.homepage

import android.os.Bundle
import android.view.ActionProvider.VisibilityListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.yjkj.service_recoder.BR

import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseFragment
import com.yjkj.service_recoder.library.base.nav
import com.yjkj.service_recoder.library.utils.ext.toast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
* @Author hxy
* Create at 2024/1/19
* @desc 首页
*/
class HomeFragment : BaseFragment() {
    private lateinit var viewModel: HomePageViewModel
    override fun initViewModel() {
        viewModel = getFragmentScopeViewModel(HomePageViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_home,BR.viewModel,viewModel).addBindingParam(BR.click,Click())
    }

    override fun observer() {
        super.observer()
        //点击用户头像
        lifecycleScope.launch {
            viewModel.userAvatarClickFlow.collect{
                nav().navigate(R.id.userInfoFragment)
            }
        }
        //点击用户item
        lifecycleScope.launch {
            viewModel.userItemClickFlow.collect{
                nav().navigate(R.id.userSecondaryFragment)
            }
        }
    }

    inner class Click{

        //设置
        fun setting(){

        }
        //升级
        fun update(){

        }
        //一键加速
        fun speedUp(){

        }
        //音量
        fun volume(){

        }

        //立即打卡
        fun sign(){
            nav().navigate(R.id.servicePunchFragment)
        }
        //通知
        fun notice(){

        }
        //可视化
        fun visual(){

        }

        //餐饮
        fun catering(){

        }

        //购物
        fun shopping(){

        }

        //智慧服务
        fun wisdomService(){

        }

        //联系人列表
        fun addressList(){
            nav().navigate(R.id.contactsFragment)
        }

    }

}