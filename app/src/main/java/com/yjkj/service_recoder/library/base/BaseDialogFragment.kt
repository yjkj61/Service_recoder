package com.yjkj.service_recoder.library.base

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.blankj.utilcode.util.ScreenUtils
import com.gyf.immersionbar.ImmersionBar

import com.kunminx.architecture.ui.page.DataBindingActivity
import com.yjkj.service_recoder.Application
import com.yjkj.service_recoder.R


/**
 *@Created by houxiaoyao on 2022/4/21 10:14
 *  契合mvvm架构的dialogFragment
 */
abstract class BaseDialogFragment : DataBindingDialogFragment(){
    private var mFragmentProvider: ViewModelProvider? = null
    private var mActivityProvider: ViewModelProvider? = null
    private var mApplicationProvider: ViewModelProvider? = null
    private var isLoaded = false
    protected var isPopup = false

    override fun onResume() {
        super.onResume()
        bar()
        //增加了Fragment是否可见的判断
        if (!isLoaded && !isHidden) {
            lazyInit()
            isLoaded = true
        }
    }

    //懒加载的方法
    protected open fun lazyInit() {}
    //执行liveData观察者的代码块
    protected open fun observer() {

    }

    /**
     * 系统返回事件拦截
     */
    protected val dispatcher by lazy {requireActivity().onBackPressedDispatcher}
    protected fun onBackPress(enable : Boolean = false,block: () -> Unit){
        dispatcher.addCallback(this, object : OnBackPressedCallback(enable) {
            override fun handleOnBackPressed() {
                block.invoke()
            }

        })
    }

    protected open fun bar(){
        //初始化状态栏管理
        //软键盘监听回调，keyboardEnable为true才会回调此方法
        ImmersionBar.with(this).keyboardEnable(true).setOnKeyboardListener { isPopup, keyboardHeight ->
            this.isPopup = isPopup
        }.init()
//        ImmersionBar.with(this)
//            .fullScreen(false)
//            .keyboardEnable(true)
//            .statusBarDarkFont(true)
//            .setOnKeyboardListener { isPopup, keyboardHeight ->
//                this.isPopup = isPopup
//                //isPopup为true，软键盘弹出，为false，软键盘关闭
//            }.init()
    }

    protected open fun hideKeyboard() {
        val imm = requireContext().getSystemService(DataBindingActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NO_TITLE, R.style.ScreenDialogStyle)
        super.onCreate(savedInstanceState)
        //观察loadingDialog的生命周期
        observer()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dialogFragmentConfig = getDialogFragmentConfig()
        dialogFragmentConfig.apply {
            setCanceledOnTouchOutSide(canceledOnTouchOutSide)
            setLayout(width, height)
            setGravity(gravity)
            setDimAmount(dimAmount)
        }
    }

    /**
     * 设置dialogFragment属性
     */
    protected abstract fun getDialogFragmentConfig() : ConfigDialogFragment

    private  fun setCanceledOnTouchOutSide(cancel : Boolean = false){
        dialog?.setCanceledOnTouchOutside(cancel)
    }

    private fun setGravity(gravity : Int = Gravity.CENTER){
        dialog?.window?.setGravity(gravity)
    }

    private  fun setDimAmount(amount : Float = 0.5f){
        dialog?.window?.setDimAmount(amount)
    }

    private  fun setLayout(width : Int = WindowManager.LayoutParams.WRAP_CONTENT, height : Int = WindowManager.LayoutParams.WRAP_CONTENT){
        dialog?.window?.setLayout(width,height)
    }


    //TODO tip 1: DataBinding 严格模式（详见 DataBindingFragment - - - - - ）：
    // 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
    // 通过这样的方式，来彻底解决 视图实例 null 安全的一致性问题，
    // 如此，视图实例 null 安全的安全性将和基于函数式编程思想的 Jetpack Compose 持平。
    // 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910
    //TODO tip 2: Jetpack 通过 "工厂模式" 来实现 ViewModel 的作用域可控，
    //目前我们在项目中提供了 Application、Activity、Fragment 三个级别的作用域，
    //值得注意的是，通过不同作用域的 Provider 获得的 ViewModel 实例不是同一个，
    //所以如果 ViewModel 对状态信息的保留不符合预期，可以从这个角度出发去排查 是否眼前的 ViewModel 实例不是目标实例所致。
    //如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/6257931840
    protected open fun <T : BaseViewModel> getFragmentScopeViewModel(modelClass: Class<T>): T {
        if (mFragmentProvider == null) {
            mFragmentProvider = ViewModelProvider(this)

        }
        val t = mFragmentProvider?.get(modelClass)!!
//        t.getLoadingLiveData().observe(this){
//            if(it){
//                if(!dialog.isShowing()){
//                    dialog.show(childFragmentManager,"")
//                }
//            }else{
//                if(dialog.isShowing()){
//                    dialog.dialog?.cancel()
//                }
//            }
//        }
        return t
    }

    protected open fun <T : ViewModel> getActivityScopeViewModel(modelClass: Class<T>): T {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(mActivity!!)
        }
        return mActivityProvider?.get(modelClass)!!
    }

    protected open fun <T : ViewModel> getApplicationScopeViewModel(modelClass: Class<T>): T {
        if (mApplicationProvider == null) {
            mApplicationProvider =
                ViewModelProvider(mActivity?.applicationContext as Application)
        }

        return mApplicationProvider?.get(modelClass)!!
    }

    /**
     * 当短时间内多次调用show方法会抛出java.lang.IllegalStateException: Fragment already added: XXDialogFragment的异常
     * 因为dialogFragment每次调用show()方法会提交一个add fragment的事务
     * 所以重写show(),remove掉已有的事务
     */
    override fun show(manager: FragmentManager, tag: String?) {
        if(manager.isDestroyed){
            return
        }
        manager.beginTransaction().remove(this).commit()
        super.show(manager, tag)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    data class ConfigDialogFragment(
        //点击外部是否可以取消
        var canceledOnTouchOutSide : Boolean = true,
        //宽
        var width : Int = WindowManager.LayoutParams.MATCH_PARENT,
        //高
        var height : Int = WindowManager.LayoutParams.WRAP_CONTENT,
        //对话框在屏幕的显示位置
        var gravity : Int = Gravity.CENTER,
        //屏幕透明度
        var dimAmount : Float = 0.0f
    )

    fun percentWidth(percent : Float) : Int = (ScreenUtils.getScreenWidth() * percent).toInt()
    fun percentHeight(percent : Float) : Int = (ScreenUtils.getScreenHeight() * percent).toInt()

}

/**
 * 为BaseFragment添加扩展方法获取NavController方便fragment跳转
 */
fun BaseDialogFragment.nav(): NavController? {
    return NavHostFragment.findNavController(this)
}

fun BaseDialogFragment.act(clazz: Class<out BaseActivity>, finish : Boolean = false){
    val intent = Intent(activity,clazz)
    activity?.startActivity(intent)
    if(finish){
        activity?.finish()
    }
}

/**
 * intent带值跳转
 */
fun BaseDialogFragment.act(clazz: Class<out BaseActivity>, bundle: Bundle, isFinish: Boolean = false){
    val intent = Intent(activity, clazz)
    if (bundle != null) intent.putExtras(bundle)
    activity!!.startActivity(intent)
    if (isFinish) activity!!.finish()
}
