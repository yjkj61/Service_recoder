package com.yjkj.service_recoder.ui.login

import android.Manifest
import android.os.Build
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.java.utils.ToolUtils
import com.yjkj.service_recoder.library.base.BaseFragment
import com.yjkj.service_recoder.library.base.nav
import com.yjkj.service_recoder.library.base.navTo
import com.yjkj.service_recoder.library.utils.ext.toast

/**
* @Author hxy
* Create at 2024/1/17
* @desc 登录
*/
class LoginFragment : BaseFragment() {
    private lateinit var viewModel: LoginViewModel
    override fun initViewModel() {
        viewModel = getFragmentScopeViewModel(LoginViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_login,BR.viewModel,viewModel).addBindingParam(BR.click,Click())
    }

    override fun lazyInit() {
        super.lazyInit()
        viewModel.initLoginItems()
        ToolUtils.initPermission(this.activity)
    }

    inner class Click{
        fun login(){
            viewModel.login(){
                toast("登陆成功")
                navTo(R.id.homeFragment,R.id.loginFragment)
            }
        }
    }
}