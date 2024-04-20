package com.yjkj.service_recoder.ui.servicepunch

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseFragment
import com.yjkj.service_recoder.library.base.nav
import com.yjkj.service_recoder.library.utils.ext.dpToPx
import com.yjkj.service_recoder.library.view.GridSpaceItemDecoration
import com.yjkj.service_recoder.library.view.dialog.inputJobNumberDialog
import kotlinx.coroutines.launch


class ServicePunchFragment : BaseFragment() {

    private lateinit var viewModel: ServicePunchViewModel


    override fun initViewModel() {
        viewModel = getFragmentScopeViewModel(ServicePunchViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_service_punch,BR.viewModel,viewModel).addBindingParam(BR.click,Click())
    }

    override fun lazyInit() {
        super.lazyInit()
        val space0 = requireContext().dpToPx(10)
        val space1 = requireContext().dpToPx(5)
        viewModel.servicePunchGridLayoutManager.set(GridLayoutManager(requireContext(),6))
        viewModel.servicePunchDecoration.set(GridSpaceItemDecoration(space0,space1))
    }

    override fun observer() {
        super.observer()
        viewModel.viewModelScope.launch {
            viewModel.servicePunchItemFlow.collect{
                val bundle = Bundle()
                when(it){
                    0->{
                       bundle.putString("param","翻身清洁")
                    }
                    1->{
                        bundle.putString("param","喝水服务")
                    }
                    2->{
                        bundle.putString("param","按摩服务")
                    }
                    3->{
                        bundle.putString("param","吃饭服务")
                    }
                    4->{
                        bundle.putString("param","更换尿垫")
                    }
                    5->{
                        bundle.putString("param","更换床品")
                    }
                    6->{
                        bundle.putString("param","洗澡服务")
                    }
                    7->{
                        bundle.putString("param","外出活动")
                    }
                    8->{
                        bundle.putString("param","室内保洁")
                    }
                    9->{
                        bundle.putString("param","如厕服务")
                    }
                    10->{
                        bundle.putString("param","理发服务")
                    }
                    11->{
                        bundle.putString("param","换衣服务")
                    }
                    12->{
                        bundle.putString("param","衣物清洗")
                    }
                }

                activity?.inputJobNumberDialog(confirmBlock = {
                    nav().navigate(R.id.servicePunchDetailFragment,bundle)
                })


            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.servicePunchGridLayoutManager.set(null)
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
    }

}