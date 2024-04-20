package com.yjkj.service_recoder.ui.usersecondary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseFragment
import com.yjkj.service_recoder.library.base.nav
import com.yjkj.service_recoder.library.utils.ext.toast

/**
* @Author hxy
* Create at 2024/1/30
* @desc用户二级页面
*/
class UserSecondaryFragment : BaseFragment() {
    private lateinit var viewModel : UserSecondaryVieModel
    override fun initViewModel() {
        viewModel = getFragmentScopeViewModel(UserSecondaryVieModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_user_secondary,BR.viewModel,viewModel).addBindingParam(BR.click,Click())
    }

    inner class Click{
        //返回上一个页面
        fun backup(){
            nav().navigateUp()
        }

        //回首页
        fun home(){
            backup()
        }

        //拨打电话
        fun call(){
            call("10086")
        }

        //护理计划记录
        fun nursePlanRecord(){

        }

        fun toPhListPage(){
            nav().navigate(R.id.phCheckListFragment)
        }

        fun add(){
            toast("")
        }

        fun del(){
            toast("")
        }
    }

}