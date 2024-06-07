package com.yjkj.service_recoder.java.base

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.blankj.utilcode.util.KeyboardUtils
import com.yjkj.service_recoder.Application
import com.yjkj.service_recoder.library.dsl.PermissionBuilder
import com.yjkj.service_recoder.library.dsl.registerPermission
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import com.yjkj.service_recoder.java.kotbase.BaseActivity;

/**
 *@Created by houxiaoyao on 2022/3/23 16:01
 *
 */
abstract class BaseFragment : DataBindingFragment(){
    private var mFragmentProvider: ViewModelProvider? = null
    private var mActivityProvider: ViewModelProvider? = null
    private var mApplicationProvider: ViewModelProvider? = null
    private var t : BaseViewModel? = null
    private var isLoaded = false
    //当前键盘打开状态 //isPopup为true，软键盘弹出，为false，软键盘关闭
    protected var isPopup = false
    protected var keyboardEnable = true




    /**
     * 系统返回事件拦截
     */
//    protected val dispatcher by lazy {
//        requireActivity().onBackPressedDispatcher
//    }
//
//
//    protected fun onBackPress(enable : Boolean = false,block: () -> Unit){
//        dispatcher.addCallback(this, object : OnBackPressedCallback(enable) {
//            override fun handleOnBackPressed() {
//                block.invoke()
//            }
//
//        })
//    }

    override fun onResume() {
        super.onResume()
        //增加了Fragment是否可见的判断
        if (!isLoaded && !isHidden) {
            //t?.placeholderState?.set(ViewState.SHOW_LOADING)
            lazyInit()
            isLoaded = true
        }
    }



    //懒加载的方法
    protected open fun lazyInit() {}
    //执行liveData观察者的代码块
    protected open fun observer() {

    }


    protected open fun hideKeyboard() {
        if(KeyboardUtils.isSoftInputVisible(context as Activity)){
            KeyboardUtils.hideSoftInput(context as Activity)
        }
    }

    /**
     * 清除所有焦点并隐藏软键盘
     */
    protected open fun clearFocus(){
        val view = activity?.currentFocus
        view?.let {
            val manager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(it.windowToken,0)
            it.clearFocus()
        }
    }

    protected open fun call(phoneNUmber : String){
        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${phoneNUmber}"))
        requireActivity().startActivity(dialIntent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
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
            mFragmentProvider = ViewModelProvider(mActivity)

        }
        t = mFragmentProvider?.get(modelClass)!!
        return mFragmentProvider?.get(modelClass)!!
    }

    protected open fun <T : ViewModel> getActivityScopeViewModel(modelClass: Class<T>): T {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(mActivity)
        }
        return mActivityProvider?.get(modelClass)!!
    }

    protected open fun <T : ViewModel> getApplicationScopeViewModel(modelClass: Class<T>): T {
        if (mApplicationProvider == null) {
            mApplicationProvider =
                ViewModelProvider(mActivity.applicationContext as Application)
        }

        return mApplicationProvider?.get(modelClass)!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    /**
     * 开启子线程
     */
    fun withIO(block : ()->Unit){
        t?.viewModelScope?.launch(Dispatchers.IO){
            block()
        }
    }

    fun launch(block : ()->Unit){
        t?.viewModelScope?.launch(Dispatchers.Main){
            block()
        }
    }

    /**
     * 隐藏软键盘
     */
    fun dismissKeyboard(){
        if(KeyboardUtils.isSoftInputVisible(context as Activity)){
            KeyboardUtils.hideSoftInput(context as Activity)
        }
    }


    internal val perms = arrayOf(
        Manifest.permission.CALL_PHONE,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.READ_CALL_LOG)
    /**
     * 声明运行时权限回调
     */
    private var easyPermissionsCallback : PermissionBuilder? = null

    /**
     * 权限回调
     * @param granted :权限通过
     */
    protected fun permissionCallback(granted : ()->Unit,denied : ()->Unit){
        easyPermissionsCallback = registerPermission {
            permissionsGranted{ _, perms ->
                if(perms.size != this@BaseFragment.perms.size){
                    return@permissionsGranted
                }
                granted.invoke()
            }
            permissionsDenied { _, perms ->
                if(EasyPermissions.somePermissionPermanentlyDenied(this@BaseFragment,perms)){
                    denied.invoke()
                }
            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        easyPermissionsCallback?.let { EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, it) }

    }








}

/**
 * 为BaseFragment添加扩展方法获取NavController方便fragment跳转
 */
fun BaseFragment.nav(): NavController {
    return NavHostFragment.findNavController(this)
}

/**
 * 跳转页面并携带参数
 * nav跳转页面并弹出指定fragment
 * @param destinationId : 需要跳转的目的地
 * @param destinationIds : 需要弹出的fragment
 */
fun BaseFragment.navToByParams(destinationId : Int, params : Bundle, vararg destinationIds : Int){
    if(destinationIds.isNotEmpty()){
        destinationIds.forEach {
            popBackStack(it)
        }
    }
    findNavController().navigate(destinationId,params)
}

/**
 * nav跳转页面并弹出指定fragment
 * @param destinationId : 需要跳转的目的地
 * @param destinationIds : 需要弹出的fragment
 */
fun BaseFragment.navTo(destinationId : Int, vararg destinationIds : Int){
    if(destinationIds.isNotEmpty()){
        destinationIds.forEach {
            popBackStack(it)
        }
    }
    findNavController().navigate(destinationId)
}

/**
 * 跳转到指定fragment并且弹出其他所有fragment
 */
fun BaseFragment.navToThenPopAll(destinationId : Int){
    nav().navigate(destinationId)
    nav().graph.forEach {
        if(it.id != destinationId){
            popBackStack(it.id)
        }
    }
}

/**
 * 弹出指定fragment
 * @param destinationId :可变参数 fragment id
 */
fun BaseFragment.popBackStack(vararg destinationId : Int){
    destinationId.forEach {
        findNavController().popBackStack(it,true)
    }
}



fun BaseFragment.act(clazz: Class<out BaseActivity>, finish : Boolean = false){
    val intent = Intent(activity,clazz)
    activity?.startActivity(intent)
    if(finish){
        activity?.finish()
    }
}

fun BaseFragment.actToJava(clazz: Class<out BaseActivity>, finish : Boolean = false){
    val intent = Intent(activity,clazz)
    activity?.startActivity(intent)
    if(finish){
        activity?.finish()
    }
}

/**
 * intent带值跳转
 */
fun BaseFragment.act(clazz: Class<out BaseActivity>, bundle: Bundle, isFinish: Boolean = false){
    val intent = Intent(activity, clazz)
    if (bundle != null) intent.putExtras(bundle)
    activity?.startActivity(intent)
    if (isFinish) activity?.finish()
}

/**
 * 判断权限
 */
fun BaseFragment.hasPermission() = EasyPermissions.hasPermissions(requireContext(),
    *perms)

@AfterPermissionGranted(1118)
fun BaseFragment.checkPermission(block : ()->Unit){
    if(hasPermission()){
        //权限已通过
        block.invoke()
    }else{
        EasyPermissions.requestPermissions(this, "",
            1118,
            *perms)
    }
}






