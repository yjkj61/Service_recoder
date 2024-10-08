package com.yjkj.service_recoder.java.ui.personal

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.yjkj.service_recoder.java.entity.OwnerEntity
import com.yjkj.service_recoder.java.utils.toast
import com.yjkj.service_recoder.java.ui.personal.item.CallLogItemViewModel.Companion.CLICK_TYPE_AGREE
import com.yjkj.service_recoder.java.ui.personal.item.CallLogItemViewModel.Companion.CLICK_TYPE_DISAGREE
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.java.base.BaseFragment
import com.yjkj.service_recoder.java.base.nav
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.java.bean.UserListBean
import com.yjkj.service_recoder.java.kotbase.BaseActivity
import com.yjkj.service_recoder.java.ui.CfbgActivity
import com.yjkj.service_recoder.java.ui.HardSoftDeviceActivity
import com.yjkj.service_recoder.java.ui.HealthCardActivity
import com.yjkj.service_recoder.java.ui.HlListActivity
import com.yjkj.service_recoder.java.ui.UserInfoActivity
import com.yjkj.service_recoder.java.ui.WebView

class PersonalFragment : BaseActivity() {
    private lateinit var viewModel: PersonalViewModel

    private var currOwner: OwnerEntity? = null;

    private var ownerid: Int = 0;

    override fun initViewModel() {
        viewModel = getActivityScopeViewModel(PersonalViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.fragment_personal,
            BR.viewModel,
            viewModel
        ).addBindingParam(BR.click, Click())
    }

    override fun initData() {
        super.initData()
//        arguments?.let {
//            val ownerEntity =
//                it.getSerializable(SecondHomePageFragment.OWNER_ENTITY_PARAM) as OwnerEntity
            val ownerEntity = intent.getSerializableExtra("ownerEntity") as OwnerEntity
            currOwner = ownerEntity
            ownerEntity.ownerId?.let { ownerid = it }
            viewModel.initUserInfo(ownerEntity)
            ownerEntity.ownerId?.let { it1 ->
                viewModel.getInfo(it1)
            }
//        }
        viewModel.initCallLogItems()
        viewModel.initUserContactsItems()
    }

    override fun observer() {
        super.observer()
        lifecycleScope.launch {
            viewModel.callLogItemClickFlow.collect {
                when (it.first) {
                    CLICK_TYPE_AGREE -> {
                        toast("接受")
                    }

                    CLICK_TYPE_DISAGREE -> {
                        toast("拒绝")
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.userContactsItemClickFlow.collect {
                Log.i("userContactsItemClickFlow", it.toString())
                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${it.toString()}"))
                startActivity(dialIntent)
            }
        }
    }

    inner class Click {
        //返回上一个页面
        fun backup() {
            finish()
        }

        //回首页
        fun home() {
//            backup()
//            lifecycleScope.launch {
//                mainViewModel.toHomeClickFlow.emit(Unit)
//            }
        }

        //拨打电话
        fun call() {
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${currOwner?.ownerPhone ?: "10086"}"))
            startActivity(dialIntent)
        }

        //护理计划记录
        fun nursePlanRecord() {
            var intent = Intent(this@PersonalFragment, HlListActivity::class.java)
            intent.putExtra("id", ownerid)
            startActivity(intent)
        }

        //用户详情
        fun toUserInfo() {
            var intent = Intent(this@PersonalFragment, UserInfoActivity::class.java)
            intent.putExtra("id", ownerid)
            startActivity(intent)
        }

        fun toAIDoctor(){
            var intent = Intent(this@PersonalFragment, WebView::class.java)
            intent.putExtra("msg", "https://robot-lib-achieve.zuoshouyisheng.com/?app_id=586232fc0bdf3f6784d211bb")
            startActivity(intent)
        }

        //查房报告
        fun toReport() {
            var intent = Intent(this@PersonalFragment, CfbgActivity::class.java)
            startActivity(intent)
        }

        //健康监测
        fun toHealthCard() {
            var intent = Intent(this@PersonalFragment, HealthCardActivity::class.java)
            intent.putExtra("id", ownerid)
            startActivity(intent)
        }

        //物联网
        fun toWulianwang() {
            var intent = Intent(this@PersonalFragment, HardSoftDeviceActivity::class.java)
            intent.putExtra("id", ownerid)
            startActivity(intent)
        }
    }
}