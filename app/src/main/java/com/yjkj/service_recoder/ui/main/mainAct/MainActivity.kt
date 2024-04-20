package com.yjkj.service_recoder.ui.main.mainAct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.yjkj.service_recoder.BR
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.base.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var viewModel: MainActViewModel

    override fun initViewModel() {
        viewModel = getActivityScopeViewModel(MainActViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_main,BR.viewModel,viewModel)
    }

    override fun initData() {
        super.initData()
        //隐藏状态栏
        ImmersionBar.with(this).hideBar(BarHide.FLAG_HIDE_STATUS_BAR).init()
    }
}