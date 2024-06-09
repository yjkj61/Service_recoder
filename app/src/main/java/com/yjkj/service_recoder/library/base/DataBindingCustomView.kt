package com.yjkj.service_recoder.library.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.yjkj.service_recoder.MyApplication

/**
* @Author hxy
* Create at 2022/12/19
* @desc 符合MVVM架构的自定义view的基类
 *
*/
abstract class DataBindingCustomView(context: Context, attributeSet: AttributeSet) : ConstraintLayout(context,attributeSet) {

    private var mApplicationProvider: ViewModelProvider? = null
    private var binding : ViewDataBinding? = null

    protected abstract fun getDataBindingConfig(): DataBindingConfig
    protected abstract fun initViewModel()
    protected open fun initData(){}
    protected open fun observer() {}


    protected fun initBinding(){
        val dataBindingConfig = this.getDataBindingConfig()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater,dataBindingConfig.layout,this,true)
        binding?.setVariable(dataBindingConfig.vmVariableId,dataBindingConfig.stateViewModel)
        val bindingParams = dataBindingConfig.bindingParams
        var i = 0
        run {
            val length = bindingParams.size()
            while (i < length) {
                binding!!.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i))
                ++i
            }
        }
    }


    /**
     * 在 MVVM 架构中，ViewModel 是与视图无关的业务逻辑层，它负责处理与界面交互相关的数据和逻辑。ViewModelStoreOwner 是一个接口，表示拥有 ViewModelStore 的对象，用于存储和管理 ViewModel 实例。
     * 通常，Activity 和 Fragment 是常见的 ViewModelStoreOwner 实现类。但是，在自定义的 View 中，无法直接获取到与之关联的 ViewModelStoreOwner。
     * 'findViewTreeViewModelStoreOwner（）' 方法的作用就是在 View 树中查找与当前 View 相关联的 ViewModelStoreOwner。
     */
    protected open fun<T : BaseViewModel> getCustomViewViewModel(modelClass: Class<T>) : T{
        val vm =  findViewTreeViewModelStoreOwner()?.let {
            ViewModelProvider(it)[modelClass]
        }

        return vm!!
    }


    protected open fun <T : ViewModel> getApplicationScopeViewModel(modelClass: Class<T>): T {
        if (mApplicationProvider == null) {
            mApplicationProvider =
                ViewModelProvider(MyApplication())
        }

        return mApplicationProvider?.get(modelClass)!!
    }


    internal fun getLifecycleOwner() : LifecycleOwner? = findViewTreeLifecycleOwner()

    protected open fun getBinding() : ViewDataBinding?{
        return this.binding
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initViewModel()
        initBinding()
        initData()
        observer()
    }


    override fun onDetachedFromWindow() {
        this.binding?.unbind()
        this.binding = null
        super.onDetachedFromWindow()
    }

}

//fun View.findViewTreeLifecycleOwner() : LifecycleOwner?{
//    return ViewTreeLifecycleOwner.get(this)
//}