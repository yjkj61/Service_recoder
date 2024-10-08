package com.yjkj.service_recoder.java.ui;

import android.text.Html
import com.yjkj.service_recoder.java.bean.Row
import com.google.gson.Gson
import com.yjkj.service_recoder.databinding.ActivityActiveDetailBinding
import com.yjkj.service_recoder.java.base.BaseActivity
import com.yjkj.service_recoder.java.bean.ApplyActiveEntity
import com.yjkj.service_recoder.java.http.OkHttpUtil
import com.yjkj.service_recoder.java.http.medicalservice.API
import com.yjkj.service_recoder.java.utils.toast
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class ActiveDetailActivity : BaseActivity<ActivityActiveDetailBinding>() {

    override fun onResume() {
        super.onResume()
        hideStatusBar()
        intent.extras?.let {
            val row = it.getSerializable(ACTIVE_DETAIL_KEY) as Row
            row.apply {
                viewBinding.apply {
                    activeTitleText.text = title
                    publishTimeText.text = publishTime
                    publishAuthorText.text = articleCreatorName
                    contentText.text = Html.fromHtml(content)
                }
            }

            viewBinding.applyButton.setOnClickListener {
                val jsonObject = JSONObject()
                jsonObject.put("activityId",row.id)
                OkHttpUtil.getInstance().doPost(
                    API.applyActiveUrl,jsonObject.toString(),
                    object : Callback {
                        override fun onFailure(call: Call, e: IOException) {

                        }

                        override fun onResponse(call: Call, response: Response) {
                            runOnUiThread {
                                response.body?.let {
                                    val activeEntity = Gson().fromJson(it.string(), ApplyActiveEntity::class.java)
                                    when(activeEntity.data){
                                        1 ->{
                                            toast("报名成功")
                                        }
                                        2 ->{
                                            toast("已报名不可重复报名")
                                        }
                                        -1 ->{
                                            toast("报名人数已达上限")
                                        }
                                        else ->{
                                            toast("报名失败请联系管理员")
                                        }
                                    }
                                }
                            }
                        }

                    })
            }
        }
    }




    companion object{
        const val ACTIVE_DETAIL_KEY = "active_detail"
    }

}