package com.yjkj.service_recoder.library.bindadapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.TypedValue
import android.view.GestureDetector
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnFocusChangeListener
import android.view.View.OnKeyListener
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.view.ViewTreeObserver
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import android.widget.EditText
import android.widget.ExpandableListView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.os.bundleOf
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.dsl.MotionLayoutTransitionBuilder
import com.yjkj.service_recoder.library.utils.ext.loadAvatar
import com.yjkj.service_recoder.library.utils.ext.loadSrc
import com.yjkj.service_recoder.library.utils.ext.textColor


/**
  * @desc 自定义通用bindingAdapter
  * @Author hxy
  * @date 2022/10/26
  **/

object CommonBindingAdapter {

    /**
     * recyclerView设置线性layoutManager
     */
    @JvmStatic
    @BindingAdapter(value = ["recyclerLinearLayoutManager"])
    fun setRecyclerLinearLayoutManager(recyclerView: RecyclerView,layoutManager: LinearLayoutManager){
        layoutManager.let {
            recyclerView.layoutManager = it
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["horizontalLayoutManager"])
    fun setRecyclerView(recyclerView: RecyclerView,enable: Boolean){
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context,RecyclerView.HORIZONTAL,false)
    }


    @JvmStatic
    @BindingAdapter(value = ["gridLayoutManager"])
    fun setGridLayoutManager(recyclerView: RecyclerView,manager: GridLayoutManager?){
        manager?.let {
            if(recyclerView.layoutManager == null){
                recyclerView.layoutManager = it
            }

        }

    }




    /**
     * 设置view的填充颜色
     */
    @JvmStatic
    @BindingAdapter(value = ["solidColor"])
    fun setSolidColor(view: View, color : String){
        val background = view.background as GradientDrawable
        val color = Color.parseColor(color)
        val colors = intArrayOf(color)
        val states = arrayOfNulls<IntArray>(1)
        states[0] = intArrayOf()
        background.color = ColorStateList(states,colors)
    }

    @JvmStatic
    @BindingAdapter(value = ["bindImageButtonSrc","bindImageButtonColor"], requireAll = false)
    fun bindImageButtonSrc(imageButton: ImageButton,res : Int?,color : Int?){
        res?.let {
            imageButton.setBackgroundResource(it)
        }

        color ?: return
        val drawable = imageButton.background

        DrawableCompat.setTint(drawable.mutate(),ContextCompat.getColor(imageButton.context,color))
    }


    @JvmStatic
    @BindingAdapter(value = ["bindDrawableColor"])
    fun bindDrawableColor(view: View,color: Int?){
        color?.let {
            val drawable = view.background
            DrawableCompat.setTint(drawable.mutate(),ContextCompat.getColor(view.context,it))
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["bindDrawableBgColor"])
    fun bindDrawableColor2(view: ImageView,color: Int?){
        color?.let {
            val drawable = view.background
            DrawableCompat.setTint(drawable.mutate(),ContextCompat.getColor(view.context,it))
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["loadSrc","loadRoundSrc"], requireAll = false)
    fun setImageViewSrc(imageView: ImageView,res : Any?,res1 : Any?){
        res?.let {
            imageView.loadSrc(it)
        }

        res1?.let {
            imageView.loadAvatar(it)
        }

    }



    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    @BindingAdapter(value = ["bindTextColor"])
    fun setTextColor(textView: TextView, colorRes : Int?){
        colorRes?.let {
            textView.textColor(it)
        }

    }



    @JvmStatic
    @BindingAdapter(value = ["bindTextBackground"])
    fun setTextBackground(textView: TextView, resource : Int){
        textView.setBackgroundResource(resource)

    }

    /**
     * recyclerView添加分割线
     */
    @JvmStatic
    @BindingAdapter(value = ["dividerItemDecoration"])
    fun setDividerItem(recyclerView: RecyclerView, itemDecoration: RecyclerView.ItemDecoration?){
        itemDecoration?:return
        recyclerView.addItemDecoration(itemDecoration)
    }

    @JvmStatic
    @BindingAdapter(value = ["scrollToTargetItem"])
    fun scrollToTargetItem(recyclerView: RecyclerView,index : Int?){
        if((index == null) || (index < 0)){
            return
        }
        recyclerView.scrollToPosition(index)
    }

    @JvmStatic
    @BindingAdapter(value = ["addGlobalLayoutListener","addRvScrollListener"], requireAll = false)
    fun addRvGlobalLayoutListener(recyclerView: RecyclerView,listener : ViewTreeObserver.OnGlobalLayoutListener?,scrollListener: OnScrollListener){
        listener?.let {
            recyclerView.viewTreeObserver.addOnGlobalLayoutListener(it)
        }

        recyclerView.addOnScrollListener(scrollListener)
    }

    @JvmStatic
    @BindingAdapter(value = ["viewTranslationTo"])
    fun viewTranslationTo(view: View,dy : Float?){
        if(dy == null){
            return
        }
        view.apply {
            translationY = dy
        }
    }


    @JvmStatic
    @BindingAdapter(value = ["appTextSize"])
    fun setTextSize(textView: TextView,size : Float){
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,size)
    }


    @JvmStatic
    @BindingAdapter(value = ["viewBindBackground"])
    fun bindButtonBackground(button: View,background : Int?){
        background ?: return
        button.setBackgroundResource(background)
    }

    /**
     * cardView设置背景
     */
    @JvmStatic
    @BindingAdapter(value = ["viewBindBackgroundColor"])
    fun bindButtonBackgroundColor(view: View,background : Int?){
        background ?: return
        view.setBackgroundColor(background)
    }

    @JvmStatic
    @BindingAdapter(value = ["viewBindBackgroundRes"])
    fun bindButtonBackgroundRes(button: View,background : Int?){
        background ?: return
        button.setBackgroundResource(background)
    }

    /**
     * EditText监听焦点变化
     */
    @JvmStatic
    @BindingAdapter(value = ["editFocusChangeListener","setFocusable"],requireAll = false)
    fun addEditFocusChangeListener(editText: EditText,focusChangeListener: OnFocusChangeListener,setFocusable : Boolean?){
        editText.onFocusChangeListener = focusChangeListener
        setFocusable?.let {
            if(!setFocusable){
                editText.clearFocus()

            }
        }
    }



    /**
     * EditText监听输入框内容变化
     */
    @JvmStatic
    @BindingAdapter(value = ["addTextWatcher","editHintText","addInputType"],requireAll = false)
    fun addTextWatcher(editText: EditText,textWatcher: TextWatcher?,hintText : String?,inputType: Int?){
        textWatcher?.let {
            editText.addTextChangedListener(it)
        }
        hintText?.let {
            editText.hint = hintText
        }
        inputType?.let {
            editText.setInputType(it)
        }

    }

    @JvmStatic
    @BindingAdapter(value = ["addOnKeyListener"])
    fun addMyOnKeyListener(editText: EditText,onKeyListener: OnKeyListener){
        editText.setOnKeyListener(onKeyListener)
    }

    @JvmStatic
    @BindingAdapter(value = ["addEditorActionListener"])
    fun addEditorActionListener(editText: EditText,actionListener : TextView.OnEditorActionListener?){
        actionListener?.let {
            editText.setOnEditorActionListener(actionListener)
        }
    }

    /**
     * 设置editText是否可以输入
     */
    @SuppressLint("NewApi")
    @JvmStatic
    @BindingAdapter(value = ["editTextFocusable"])
    fun setEditTextFocusable(editText: EditText,focusable: Int){
        editText.focusable = focusable
    }


    @JvmStatic
    @BindingAdapter(value = ["onTouch"])
    fun setOnTouch(recyclerView: View,onTouchListener: OnTouchListener){
        recyclerView.setOnTouchListener(onTouchListener)
    }


    @JvmStatic
    @BindingAdapter(value = ["addCompoundButtonCheckedListener"])
    fun addRadioButtonCheckedListener(radioButton: CompoundButton,listener : CompoundButton.OnCheckedChangeListener){
        radioButton.setOnCheckedChangeListener(listener)
    }

    @JvmStatic
    @BindingAdapter(value = ["compoundButtonClick"])
    fun compoundButtonClick(radioButton: RadioButton,listener: OnClickListener){
        radioButton.setOnClickListener(listener)
    }



    /**
     * 动态设置控件高度以及当高度为0时设置marginTop也为0
     * 此方法用于recyclerView使用动态修改高度以及上边距来实现假的item的隐藏
     */
    @JvmStatic
    @BindingAdapter(value = ["viewGroupHeight"])
    fun setViewGroupHeight(viewGroup: ViewGroup,height : Int?){
        height ?: return
        val layoutParams = viewGroup.layoutParams
        layoutParams.height = height
        viewGroup.layoutParams = layoutParams
        if(height == 0){
            val margin = viewGroup.layoutParams as MarginLayoutParams
            margin.topMargin = 0
            viewGroup.layoutParams = margin
        }
    }

    /**
     * 为RecyclerView添加item移除和新增时的动画
     */
    @JvmStatic
    @BindingAdapter(value = ["itemsAnimator"])
    fun addRecyclerItemsAnimator(recyclerView: RecyclerView,animator : RecyclerView.ItemAnimator?){
        return
        animator?.let {
            recyclerView.itemAnimator = it
        }
    }





    /**
     * view添加旋转动画
     */
    @JvmStatic
    @BindingAdapter(value = ["addRotationAnimator"])
    fun setAnimator(view : View,enable : Boolean){
        val rotation = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f)
        rotation.duration = 1000 // 设置旋转一次的时间，单位毫秒
        rotation.repeatCount = ObjectAnimator.INFINITE // 无限循环
        rotation.start()
    }

    /**
     * 双向绑定motionLayout实例，这样可以直接获取到motionLayout对象可以在viewModel/fragment/activity中直接操作该view
     */
    @JvmStatic
    @BindingAdapter(value = ["motionLayoutInstance"])
    fun setMotionState(motion : MotionLayout,instance : MotionLayout?){
        motion.jumpToState(motion.startState)
    }

    @InverseBindingAdapter(attribute = "motionLayoutInstance")
    @JvmStatic
    fun getMotionLayout(motion: MotionLayout):MotionLayout{
        return motion
    }

    @BindingAdapter("motionLayoutInstanceAttrChanged")
    @JvmStatic
    fun motionLayoutChange(motionLayout: MotionLayout,listener: InverseBindingListener){
        motionLayout.viewTreeObserver.addOnDrawListener {
            listener.onChange()
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["viewInstance"])
    fun setViewInstance(view: View,instance : View){

    }

    @InverseBindingAdapter(attribute = "viewInstance")
    @JvmStatic
    fun getViewInstance(view: View):View{
        return view
    }

    @JvmStatic
    @BindingAdapter("viewInstanceAttrChanged")
    fun viewChange(view: View,listener: InverseBindingListener){
        view.viewTreeObserver.addOnDrawListener {
            listener.onChange()
        }

    }




    @SuppressLint("ClickableViewAccessibility")
    @JvmStatic
    @BindingAdapter(value = ["addGestureDetector"])
    fun addGestureDetector(viewGroup: ViewGroup,gestureDetector : GestureDetector){
        viewGroup.setOnTouchListener { v, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["motionLayoutProgress"])
    fun setMotionLayoutState(motion: MotionLayout,progress : Float){
        motion.progress = progress
    }

    @InverseBindingAdapter(attribute = "motionLayoutProgress")
    @JvmStatic
    fun getMotionLayoutProgress(motion: MotionLayout):Float{
        return motion.progress
    }

    @JvmStatic
    @BindingAdapter(value = ["bindMotionLayoutTransitionListener"])
    fun addMotionLayoutTransitionListener(motion: MotionLayout,listener : MotionLayoutTransitionBuilder){
        motion.addTransitionListener(listener)
    }





    @JvmStatic
    @BindingAdapter(value = ["viewGroupHeight2","viewGroupWidth"], requireAll = false)
    fun setViewGroupHeight2(viewGroup: View,height : Int?,width: Int?){
        val layoutParams = viewGroup.layoutParams
        height?.let {
            layoutParams.height = it
            viewGroup.layoutParams = layoutParams
        }


        width?.let {
            layoutParams.width = it
            viewGroup.layoutParams = layoutParams
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["viewMarginBottom"])
    fun setMarginBottom(viewGroup: View, marginBottom : Int?){
        val lp = viewGroup.layoutParams as MarginLayoutParams
        marginBottom?.let {
            lp.bottomMargin = marginBottom
            viewGroup.layoutParams = lp
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["viewMarginTop"])
    fun setMarginTop(viewGroup: View, marginTop : Int?){
        val lp = viewGroup.layoutParams as MarginLayoutParams
        marginTop?.let {
            lp.topMargin = marginTop
            viewGroup.layoutParams = lp
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["viewMarginTop2"])
    fun setMarginTop2(viewGroup: ViewGroup, marginTop : Int?){
        val lp = viewGroup.layoutParams as MarginLayoutParams
        marginTop?.let {
            lp.topMargin = marginTop
            viewGroup.layoutParams = lp
        }
    }

    var mBehavior : BottomSheetBehavior<View>? = null

    @JvmStatic
    @BindingAdapter(value = ["behaviorListener","bindPeekHeight"], requireAll = false)
    fun addBehaviorListener(view: View,callback : BottomSheetBehavior.BottomSheetCallback,peekHeight : Int){
        val mBehavior = BottomSheetBehavior.from(view)

        CommonBindingAdapter.mBehavior = mBehavior
        mBehavior.addBottomSheetCallback(callback)
        mBehavior.setPeekHeight(peekHeight,true)
    }


    @JvmStatic
    @BindingAdapter(value = ["transitionToState"])
    fun bindMotionLayoutState(motion: MotionLayout,state: Int){
        motion.transitionToState(state)
    }

    @InverseBindingAdapter(attribute = "transitionToState")
    @JvmStatic
    fun getMotionState(motion: MotionLayout):Int{
        return motion.currentState
    }



    @BindingAdapter("bindViewVisibility")
    @JvmStatic
    fun bindViewVisibility(view: View,visibility : Int){
        view.visibility = visibility
    }

    @InverseBindingAdapter(attribute = "bindViewVisibility")
    @JvmStatic
    fun getViewVisibility(view: View):Int{
        return view.visibility
    }

    @BindingAdapter("bindViewVisibilityAttrChanged")
    @JvmStatic
    fun viewVisibilityChangedListener(view: View,listener: InverseBindingListener){
        var lastVisibility = 0
        view.viewTreeObserver.addOnGlobalLayoutListener {
            if(lastVisibility != view.visibility){
                listener.onChange()
                lastVisibility = view.visibility
            }
        }
    }




    @JvmStatic
    @BindingAdapter("spinnerItemListener")
    fun spinnerItemListener(spinner: Spinner,listener : AdapterView.OnItemSelectedListener){
        spinner.onItemSelectedListener = listener
    }

    @JvmStatic
    @BindingAdapter("spinnerSelection")
    fun setSpinnerSelection(spinner: Spinner,position : Int?){
        position ?: return
        spinner.setSelection(position)
    }

    @JvmStatic
    @BindingAdapter("editTextFocusChange")
    fun editTextFocusChange(editText: EditText,focusChangeListener: OnFocusChangeListener){
        editText.onFocusChangeListener = focusChangeListener
    }

    @JvmStatic
    @BindingAdapter("bindDigits")
    fun digitsBind(editText: EditText,digits : String?){
        digits ?: return

        val inputFilter = InputFilter{source, _, _, _, _, _ ->
            if (source != null && !source.all { char -> digits.contains(char) }) {
                return@InputFilter ""
            }
            null
        }
        editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        editText.filters = arrayOf(inputFilter)

    }




    @JvmStatic
    @BindingAdapter(value = ["addConstraintLayoutChildView"])
    fun addChildView(viewGroup: ConstraintLayout,view: View?){
        view ?: return
        val lp = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.MATCH_PARENT)
        view.layoutParams = lp
        viewGroup.addView(view)
    }



    @JvmStatic
    @BindingAdapter(value = ["expandableScrollToTarget"])
    fun scrollToTargetPosition(expandableListView: ExpandableListView,position : Any?){
        position?.let {
            expandableListView.smoothScrollToPosition(0)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["spinnerItems","spinnerItemBinding","spinnerItemSelected"], requireAll = false)
    fun bindSpinnerItems(spinner: Spinner,list : List<String>,itemLayout : Int,itemSelectedListener: OnItemSelectedListener?){
        val arrayAdapter = ArrayAdapter(spinner.context, itemLayout,list)
        spinner.adapter = arrayAdapter

        itemSelectedListener?.let {
            spinner.onItemSelectedListener = it
        }

    }

    @JvmStatic
    @BindingAdapter(value = ["addOnCheckedChangeListener"])
    fun addCheckedChangeListener(checkBox: CheckBox,listener: OnCheckedChangeListener){
        checkBox.setOnCheckedChangeListener(listener)
    }

    /**
     * recyclerView添加分割线
     */
    @JvmStatic
    @BindingAdapter(value = ["dividerItemDecoration2","dividerItemShape"], requireAll = false)
    fun setDividerItem2(recyclerView: RecyclerView, direction : Int,shapeId : Int?){
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,direction)
        shapeId?.let {
            val drawable = ContextCompat.getDrawable(recyclerView.context,shapeId) ?: return
            dividerItemDecoration.setDrawable(drawable)
        }

        recyclerView.addItemDecoration(dividerItemDecoration)
    }

}

data class DrawableModel(

    /**
     * @param icon
     * icon图标
     */
    var icon : Int,

    /**
     * @param direction
     * icon显示的方向
     */
    var direction : DrawableDirection,

    /**
     * @param width : R.dimen.dp_xxx
     */
    var width : Int,
    /**
     * @param height : R.dimen.dp_xxx
     */
    var height : Int

){

    enum class DrawableDirection{
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }

    override fun toString(): String {
        return "DrawableModel(icon=$icon, direction=$direction, width=$width, height=$height)"
    }
}