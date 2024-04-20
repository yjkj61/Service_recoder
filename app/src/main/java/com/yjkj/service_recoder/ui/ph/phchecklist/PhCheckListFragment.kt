package com.yjkj.service_recoder.ui.ph.phchecklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseFragment
import com.yjkj.service_recoder.library.base.nav
import com.yjkj.service_recoder.library.utils.ext.dpToPx
import com.yjkj.service_recoder.library.utils.ext.toast
import com.yjkj.service_recoder.library.view.GridSpaceItemDecoration
import kotlinx.coroutines.launch


class PhCheckListFragment : BaseFragment() {

    private lateinit var viewModel : PhCheckListViewModel

    override fun initViewModel() {
        viewModel = getFragmentScopeViewModel(PhCheckListViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_ph_check_list,BR.viewModel,viewModel).addBindingParam(BR.click,Click())
    }

    override fun lazyInit() {
        super.lazyInit()
        initPaymentItems()
    }

    private fun initPaymentItems(){
        val space0 = requireContext().dpToPx(20)
        val space1 = requireContext().dpToPx(10)
        viewModel.gridLayoutManager.set(GridLayoutManager(requireContext(),3))
        viewModel.itemDecoration.set(GridSpaceItemDecoration(space0,space1))
        viewModel.initItems()

    }

    override fun observer() {
        super.observer()
        viewModel.viewModelScope.launch{
            viewModel.itemClickFlow.collect{
                nav().navigate(R.id.phCheckDetailFragment)
                when(it){
                    "血压"->{

                    }
                    "体温"->{

                    }
                    "血氧"->{

                    }
                    "脉搏"->{

                    }
                    "血糖"->{

                    }
                    "尿酸"->{

                    }
                    "胆固醇"->{

                    }
                    "血脂四项"->{

                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.gridLayoutManager.set(null)
    }

    inner class Click{
        fun backup(){
            nav().navigateUp()
        }

        //回首页
        fun home(){
            backup()
        }

        fun add(){

        }
    }

}