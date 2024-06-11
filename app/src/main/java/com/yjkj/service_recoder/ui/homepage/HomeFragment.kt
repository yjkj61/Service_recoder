package com.yjkj.service_recoder.ui.homepage

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.java.ui.CateringServices
import com.yjkj.service_recoder.java.ui.property.PropertyFiveBlessings
import com.yjkj.service_recoder.java.utils.ToolUtils
import com.yjkj.service_recoder.library.base.BaseFragment
import com.yjkj.service_recoder.library.base.nav
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
        ToolUtils.initPermission(activity)
        //点击用户头像
        lifecycleScope.launch {
            viewModel.userAvatarClickFlow.collect{
                nav().navigate(R.id.userInfoFragment)
            }
        }
        //点击用户item
        lifecycleScope.launch {
            viewModel.userItemClickFlow.collect{
//                nav().navigate(R.id.userSecondaryFragment)
            }
        }
    }

    inner class Click{

        //设置
        fun setting(){
            ToolUtils.toSystemSetting(activity);
        }
        //升级
        fun update(){
            ToolUtils.checkUpdate(activity, parentFragment)
        }
        //一键加速
        fun speedUp(){
            ToolUtils.speedUp(activity)
        }
        //音量
        fun volume(){
            ToolUtils.showSeekBarVolume(activity)
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
            var intent = Intent(activity, CateringServices::class.java)
            startActivity(intent)
        }

        //购物
        fun shopping(){

        }

        //智慧服务
        fun wisdomService(){
            var intent = Intent(activity, PropertyFiveBlessings::class.java)
            startActivity(intent)
        }

        //联系人列表
        fun addressList(){
            nav().navigate(R.id.contactsFragment)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ToolUtils.mRequestCode == requestCode) {
            for (grantResult in grantResults) {
                if (grantResult == -1) {
                    break
                }
            }
        }
    }

}