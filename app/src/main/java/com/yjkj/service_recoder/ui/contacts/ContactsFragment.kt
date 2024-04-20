package com.yjkj.service_recoder.ui.contacts

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseFragment
import com.yjkj.service_recoder.library.base.nav
import com.yjkj.service_recoder.library.utils.ext.dpToPx
import com.yjkj.service_recoder.library.view.GridSpaceItemDecoration
import com.yjkj.service_recoder.library.view.dialog.confirmDelDialog
import com.yjkj.service_recoder.library.view.dialog.inputJobNumberDialog
import kotlinx.coroutines.launch


class ContactsFragment : BaseFragment() {
    private lateinit var viewModel: ContactsViewModel

    override fun initViewModel() {
        viewModel = getFragmentScopeViewModel(ContactsViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_contacts,BR.viewModel,viewModel).addBindingParam(BR.click,Click())
    }

    override fun lazyInit() {
        super.lazyInit()
        initPaymentItems()
    }

    private fun initPaymentItems(){
        val space0 = requireContext().dpToPx(20)
        val space1 = requireContext().dpToPx(10)
        viewModel.contactsGridLayoutManager.set(GridLayoutManager(requireContext(),5))
        viewModel.contactsItemDecoration.set(GridSpaceItemDecoration(space0,space1))
        viewModel.initPaymentItems()
    }

    override fun observer() {
        super.observer()
        lifecycleScope.launch {
            viewModel.contactsItemClickFlow.collect{
                activity?.confirmDelDialog(confirm = {

                })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clear()
    }

    inner class Click{
        fun backup(){
            nav().navigateUp()
        }

        //回首页
        fun home(){
            backup()
        }

        //重置搜索
        fun reset(){
            viewModel.searchReset()
        }

        //搜索
        fun search(){
            viewModel.launchSearch()
        }
    }


}