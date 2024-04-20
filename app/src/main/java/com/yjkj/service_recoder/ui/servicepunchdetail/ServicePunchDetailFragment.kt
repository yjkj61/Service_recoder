package com.yjkj.service_recoder.ui.servicepunchdetail

import android.os.Bundle
import android.provider.ContactsContract.RawContacts.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.yjkj.service_recoder.BR

import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseFragment
import com.yjkj.service_recoder.library.base.nav
import com.yjkj.service_recoder.library.view.dialog.serviceCompleted


class ServicePunchDetailFragment : BaseFragment() {

    private lateinit var viewModel: ServicePunchDetailViewModel


    override fun initViewModel() {
        viewModel = getFragmentScopeViewModel(ServicePunchDetailViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_service_punch_detail,BR.viewModel,viewModel).addBindingParam(BR.click,Click())
    }

    override fun lazyInit() {
        super.lazyInit()
        arguments?.let {
            val string = it.getString("param")
            viewModel.title.set(string)

        }
    }

    inner class Click{
        //返回上一个页面
        fun backup(){
            nav().navigateUp()
        }

        fun clickStateButton(){
            viewModel.serviceStateText.get()?.let {
                when(it){
                    "开始录制"->{

                    }
                    "完成服务"->{
                        activity?.serviceCompleted(confirmBlock = {text, star ->  

                        })
                    }
                    else->{}
                }
            }
        }

        //回首页
        fun home(){
            backup()
        }
    }

}