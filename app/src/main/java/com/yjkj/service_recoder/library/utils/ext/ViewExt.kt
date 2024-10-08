package com.yjkj.service_recoder.library.utils.ext

import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Parcelable
import android.text.*
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yjkj.service_recoder.MyApplication
import com.yjkj.service_recoder.R

/**
 * des 视图扩展方法
 * @date 2022/4/1
 * @author hxy
 */


inline fun ViewGroup.ViewPager2(init : ViewPager2.() ->Unit){
    addView(ViewPager2(context).apply(init))
}




/**
 * viewPager2适配fragment
 */
fun ViewPager2.initFragment(
    fragment: Fragment,
    fragments: MutableList<Fragment>,
): ViewPager2 {
    //设置适配器
    adapter = object : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int) = fragments[position]
        override fun getItemCount() = fragments.size
    }
    return this
}

fun ViewPager2.initFragment(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    fragments: MutableList<Fragment>,
): ViewPager2 {
    //设置适配器
    adapter = object : FragmentStateAdapter(fragmentManager,lifecycle) {
        override fun createFragment(position: Int) = fragments[position]
        override fun getItemCount() = fragments.size
    }
    return this
}



/**
 * ViewPager2选中事件
 */
fun ViewPager2.doSelected(block : (Int) -> Unit){
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            block(position)
        }
    })
}

/**
 * ViewPager2滑动事件
 */
fun ViewPager2.doPageScrolled(block : (Int) -> Unit){
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int,
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            block(position)
        }
    })
}

/**
* @Author hxy
* Create at 2024/1/13
* @desc 单位dp
*/
fun View.setDimensions(width: Int, height: Int) {
    val layoutParams = this.layoutParams
    layoutParams.width = this.context.dpToPx(width)
    layoutParams.height = this.context.dpToPx(height)
    this.layoutParams = layoutParams
}

fun Context.dpToPx(dp: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        resources.displayMetrics
    ).toInt()
}

/**
 * bottomNavigationView选中事件
 */
fun BottomNavigationView.doSelected(block: (MenuItem) -> Unit){
    setOnItemSelectedListener {
        block(it)
        true
    }
}

/**
 * ViewPager于fragment绑定
 * 通过BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT支持懒加载
 */
fun ViewPager.initFragment(
    manager: FragmentManager,
    fragments: MutableList<Fragment>,
): ViewPager {
    //设置适配器
    adapter = object : FragmentStatePagerAdapter(
        manager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        override fun getCount() = fragments.size

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun saveState(): Parcelable? {
            return null
        }
    }
    return this
}

/**
 * ViewPager选中
 */
fun ViewPager.doSelected(selected: (Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int,
        ) {

        }

        override fun onPageSelected(position: Int) {
            selected.invoke(position)
        }

    })
}

/**
 * 设置TextView颜色
 */
@RequiresApi(Build.VERSION_CODES.M)
fun TextView.textColor(color : Int){
    val colorStateList = MyApplication.context.resources.getColorStateList(color, null)
    this.setTextColor(colorStateList)
}

fun TextView.hintColor(color: Int){
    val colorStateList = MyApplication.context.resources.getColorStateList(color, null)
    this.setHintTextColor(colorStateList)
}

/**
 * EditText添加输入监听事件
 */
fun EditText.watcher(watcher: TextWatcher){
    addTextChangedListener(watcher)
}

/**
 * ImageView加载图片
 */
fun ImageView.load(res : Any?){
    Glide.with(context)
        .load(res)
        .override(250,250)
        .into(this)
}

/**
 * ImageView加载普通图片
 */
fun ImageView.loadSrc(res : Any?){
    Glide.with(this.context)
        .load(res)
        .into(this)
}

fun ImageView.thumb(res : Any?){
    Glide.with(MyApplication.context.applicationContext)
        .setDefaultRequestOptions(
            RequestOptions()
                .frame(1000000)
                .centerCrop())
        .load(res)
        .into(this)
}

/**
 * ImageView加载圆图
 */
fun ImageView.loadCircle(str : Any?){
    Glide.with(this.context)
        .load(str)
        .circleCrop()
        .into(this)
}

/**
 * 加载圆形头像
 */
fun ImageView.loadAvatar(str: Any){
    Glide.with(this.context)
        .load(str)
        .circleCrop()
        .into(this)
}

fun ImageView.loadBase64(base64 : Any){
    Glide.with(MyApplication.context.applicationContext)
        .load("data:image/jpg;base64,${base64}")
        .circleCrop()
        .into(this)
}






/**
 * 防止重复点击,可同时注册多个view
 */
fun setNoRepeatClick(vararg views: View, interval: Long = 400, onClick: (View) -> Unit) {
    views.forEach {
        it.clickNoRepeat(interval = interval) { view ->
            onClick.invoke(view)
        }
    }
}

/**
 * 防止重复点击
 * @param interval 重复间隔
 * @param onClick  事件响应
 */
var lastTime = 0L
fun View.clickNoRepeat(interval: Long = 500, onClick: (View) -> Unit) {
    setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (lastTime != 0L && (currentTime - lastTime < interval)) {
            return@setOnClickListener
        }
        lastTime = currentTime
        onClick(it)
    }
}

/**
 * 防止快速点击
 */
var mLastTime = 0L
fun clickNoRepeat2(interval: Long = 500 , onClick: () -> Unit){
    val currentTime = System.currentTimeMillis()
    if(mLastTime != 0L && (currentTime - mLastTime < interval)){
        return
    }
    mLastTime = currentTime
    onClick()
}


/**
 * 复制剪切板
 */
fun String.copy() {
    val clip = MyApplication.context.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clip.text = this
    toast("复制成功",Toast.LENGTH_SHORT)
}



/**
 * @author sykj
 * create at 2022/8/16 23:27
 * 设置view的填充颜色
 */
@SuppressLint("ResourceAsColor")
fun View.solidColor(color : Int = R.color.white){
    this.setBackgroundColor(color)
}

/**
 * 获取当前主图颜色属性
 */
fun Context.getThemeColor(attr: Int): Int {
    val array: TypedArray = theme.obtainStyledAttributes(
        intArrayOf(
            attr
        )
    )
    val color = array.getColor(0, -0x50506)
    array.recycle()
    return color
}


/**
 * editText搜索按钮
 * @param onClick 搜索点击事件
 */
fun EditText.keyBoardSearch(onClick: () -> Unit) {
    //添加搜索按钮
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            onClick()
        } else {
            toast("请输入关键字")
            return@setOnEditorActionListener false
        }
        return@setOnEditorActionListener true
    }
}

/**
 * @param ： id : R.dimen.dp_xx
 * 返回的单位为像素
 */
fun dp(id : Int):Int{
    return MyApplication.context.resources.getDimensionPixelSize(id)
}

/**
 * 动态修改drawable颜色
 */
fun Drawable.tintDrawable(color : Int){
    val wrap = DrawableCompat.wrap(this)
    DrawableCompat.setTint(wrap, ContextCompat.getColor(MyApplication.context,color))
}

/**
 * 动态修改CompoundButton的drawable颜色
 */
fun CompoundButton.tintDrawable(color: Int){
    val context = this.context
    val drawable = this.compoundDrawables[1]
    val wrap = DrawableCompat.wrap(drawable)
    DrawableCompat.setTint(wrap, ContextCompat.getColor(context,color))
    this.setCompoundDrawables(null,wrap,null,null)
}






