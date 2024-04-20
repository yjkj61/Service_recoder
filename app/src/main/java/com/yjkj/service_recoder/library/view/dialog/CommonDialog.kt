package com.yjkj.service_recoder.library.view.dialog

import android.content.Context
import android.widget.EditText
import android.widget.TextView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.yjkj.service_recoder.R
import com.yjkj.service_recoder.library.utils.ext.toast
import per.wsj.library.AndRatingBar

/**
 * 弹出确认对话框
 *
 *
 *
 */
fun Context.inputJobNumberDialog(cancelBlock : ()->Unit = {}, confirmBlock : (jobNumber : String)->Unit, cancelable : Boolean = false){
    MaterialDialog(this).show {
        val customView = customView(R.layout.start_service_dialog_layout, scrollable = true, horizontalPadding = false, noVerticalPadding = true)
        val cancel = customView.findViewById<TextView>(R.id.cancel_service_btn)
        val confirm = customView.findViewById<TextView>(R.id.confirm_service_btn)
        val jobNumber = customView.findViewById<EditText>(R.id.input_job_number_edit)
        cancelOnTouchOutside(cancelable)
        cancel.setOnClickListener {
            cancelBlock.invoke()
            dismiss()
        }
        confirm.setOnClickListener {
            val s = jobNumber.text.toString()
            if(s.isEmpty()){
                toast("请输入工号")
                return@setOnClickListener
            }
            confirmBlock.invoke(s)
            dismiss()
        }
    }
}

fun Context.serviceCompleted(cancelBlock : ()->Unit = {},confirmBlock : (text : String,star : String)->Unit,cancelable : Boolean = false){
    MaterialDialog(this).show {
        val customView = customView(R.layout.completed_service_dialog_layout, scrollable = true, horizontalPadding = false, noVerticalPadding = true)
        val ratingBar = customView.findViewById<AndRatingBar>(R.id.rating_bar)
        val editText = customView.findViewById<EditText>(R.id.edit_text)
        val submitButton = customView.findViewById<TextView>(R.id.submit_button)
        cancelOnTouchOutside(cancelable)
        cancelable(false)
        submitButton.setOnClickListener {
            confirmBlock.invoke("${editText.text}","${ratingBar.rating.toInt()}")
            dismiss()
        }
    }
}

fun Context.confirmDelDialog(cancel : ()->Unit = {},confirm : ()->Unit = {}){
    MaterialDialog(this).show {
        val customView = customView(R.layout.confirm_del_dialog_layout, scrollable = true, horizontalPadding = false, noVerticalPadding = true)
        val cancelBtn = customView.findViewById<TextView>(R.id.cancel_button)
        val confirmBtn = customView.findViewById<TextView>(R.id.confirm_button)
        cancelOnTouchOutside(cancelable)
        cancelable(false)
        cancelBtn.setOnClickListener{
            cancel.invoke()
            dismiss()
        }
        confirmBtn.setOnClickListener {
            confirm.invoke()
            dismiss()
        }
    }
}


