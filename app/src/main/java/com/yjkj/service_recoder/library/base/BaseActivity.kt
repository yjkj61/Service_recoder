package com.yjkj.service_recoder.library.base

import android.app.Activity
import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.KeyboardUtils

import com.yjkj.service_recoder.MyApplication


/**
 * @Created by houxiaoyao on 2022/3/23 15:28
 * MVVM版activity基类，遵循DataBinding严格模式
 *
 */
abstract class BaseActivity : DataBindingActivity() {

    private var mActivityProvider: ViewModelProvider? = null
    private var mApplicationProvider: ViewModelProvider? = null
    private var t: BaseViewModel? = null


    //当前键盘打开状态 //isPopup为true，软键盘弹出，为false，软键盘关闭
    var isPopup = false


    override fun onCreate(savedInstanceState: Bundle?) {
        setRefreshRate()
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); /*输入键盘调整*/
        initData()
        observer()
        // tip 1: DataBinding 严格模式（详见 DataBindingActivity - - - - - ）：
        // 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
        // 通过这样的方式，来彻底解决 视图实例 null 安全的一致性问题，
        // 如此，视图实例 null 安全的安全性将和基于函数式编程思想的 Jetpack Compose 持平。

    }

    //tip 2: Jetpack 通过 "工厂模式" 来实现 ViewModel 的作用域可控，
    //目前我们在项目中提供了 Application、Activity、Fragment 三个级别的作用域，
    //值得注意的是，通过不同作用域的 Provider 获得的 ViewModel 实例不是同一个，
    //所以如果 ViewModel 对状态信息的保留不符合预期，可以从这个角度出发去排查 是否眼前的 ViewModel 实例不是目标实例所致。
    protected open fun <T : BaseViewModel> getActivityScopeViewModel(modelClass: Class<T>): T {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(this)
        }
        t = mActivityProvider!![modelClass]
        return mActivityProvider!![modelClass]
    }


    protected open fun <T : ViewModel> getApplicationScopeViewModel(modelClass: Class<T>): T {
        if (mApplicationProvider == null) {
            mApplicationProvider = ViewModelProvider(this.applicationContext as MyApplication)
        }
        return mApplicationProvider!![modelClass]
    }


    protected open fun initData() {}

    protected open fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    /**
     * 所有liveData监听都在此处注册
     */
    protected open fun observer() {

    }


    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }

    /**
     * 隐藏软键盘
     */
    fun dismissKeyboard(){
        if(KeyboardUtils.isSoftInputVisible(this)){
            KeyboardUtils.hideSoftInput(this)
        }
    }

    /**
     * 适配高刷
     */
    private fun setRefreshRate(){
        // 获取系统window支持的模式
        val modes = window.windowManager.defaultDisplay.supportedModes
        // 对获取的模式，基于刷新率的大小进行排序，从小到大排序
        modes.sortBy {
            it.refreshRate
        }
        window.let {
            val lp = it.attributes
            // 取出最大的那一个刷新率，直接设置给window
            lp.preferredDisplayModeId = modes.last().modeId
            it.attributes = lp
        }
    }

    /**
     * 开启全局灰色模式
     */
    private fun openGlobalGrayMode(){
        val paint = Paint()
        val cm = ColorMatrix()
        cm.setSaturation(0f)
        paint.colorFilter = ColorMatrixColorFilter(cm)
        window.decorView.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
    }



}

/**
 * intent普通跳转
 */
fun BaseActivity.toActivity(
    context: Activity?,
    clazz: Class<out BaseActivity?>?,
    isFinish: Boolean = true,
) {
    toActivity(context, clazz, null, isFinish)
}

/**
 * intent带值跳转
 */
fun BaseActivity.toActivity(
    context: Activity?,
    clazz: Class<out BaseActivity?>?,
    bundle: Bundle?,
    isFinish: Boolean = true,
) {
    val intent = Intent(context, clazz)
    if (bundle != null) intent.putExtras(bundle)
    context!!.startActivity(intent)
    if (isFinish) context.finish()
}