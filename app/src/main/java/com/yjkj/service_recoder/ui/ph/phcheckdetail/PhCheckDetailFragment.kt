package com.yjkj.service_recoder.ui.ph.phcheckdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseFragment
import com.yjkj.service_recoder.library.base.BaseViewModel
import com.yjkj.service_recoder.library.base.nav


class PhCheckDetailFragment : BaseFragment() {
   private lateinit var viewModel: PhCheckDetailViewModel

    override fun initViewModel() {
        viewModel = getFragmentScopeViewModel(PhCheckDetailViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_ph_check_detail,BR.viewModel,viewModel).addBindingParam(BR.click,Click())
    }

    inner class Click{
        fun backup(){
            nav().navigateUp()
        }

        //回首页
        fun home(){
            backup()
        }
    }

}