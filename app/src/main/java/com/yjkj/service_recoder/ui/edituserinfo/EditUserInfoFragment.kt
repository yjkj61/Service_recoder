package com.yjkj.service_recoder.ui.edituserinfo

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
import com.yjkj.service_recoder.library.base.navTo
import com.yjkj.service_recoder.library.base.navToThenPopAll

/**
* @Author hxy
* Create at 2024/1/21
* @desc 编辑用户信息
*/
class EditUserInfoFragment : BaseFragment() {
    private lateinit var viewModel: EditUserInfoViewModel
    override fun initViewModel() {
        viewModel = getFragmentScopeViewModel(EditUserInfoViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_edit_user_info,BR.viewModel,viewModel).addBindingParam(BR.click,Click())
    }

    inner class Click{
        //返回上一个页面
        fun backup(){
            nav().navigateUp()
        }

        //回首页
        fun home(){
            navToThenPopAll(R.id.homeFragment)
        }

        //提交修改
        fun commit(){

        }

        /**
         * 保存编辑
         */
        fun saveEdit(){

        }

        /**
         * 取消编辑
         */
        fun cancelEdit(){

        }
    }

}