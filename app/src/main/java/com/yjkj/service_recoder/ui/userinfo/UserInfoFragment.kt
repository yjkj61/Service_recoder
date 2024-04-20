package com.yjkj.service_recoder.ui.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseFragment
import com.yjkj.service_recoder.library.base.nav
import com.yjkj.service_recoder.library.utils.ext.dpToPx
import com.yjkj.service_recoder.library.view.GridSpaceItemDecoration

/**
* @Author hxy
* Create at 2024/1/30
* @desc 用户资料详情页
*/
class UserInfoFragment : BaseFragment() {
    private lateinit var viewModel: UserInfoViewModel
    override fun initViewModel() {
        viewModel = getFragmentScopeViewModel(UserInfoViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_user_info,BR.viewModel,viewModel).addBindingParam(BR.click,Click())
    }

    override fun lazyInit() {
        super.lazyInit()
        initDeviceItems()
    }

    private fun initDeviceItems(){
        val space0 = requireContext().dpToPx(50)
        val space1 = requireContext().dpToPx(13)
        viewModel.deviceGridLayoutManager.set(GridLayoutManager(requireContext(),3))
        viewModel.deviceItemDecoration.set(GridSpaceItemDecoration(space0,space1))
        viewModel.initDeviceItems()
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

        /**
         * 修改头像
         */
        fun changeUserAvatar(){

        }

        /**
         * 编辑用户资料
         */
        fun editUserInfo(){
            nav().navigate(R.id.editUserInfoFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }

}