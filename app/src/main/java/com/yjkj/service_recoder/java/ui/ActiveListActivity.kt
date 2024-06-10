package com.yjkj.service_recoder.java.ui;

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.yjkj.service_recoder.java.bean.HomeBannerEntity
import com.yjkj.service_recoder.java.bean.Row
import com.google.gson.Gson
import com.yjkj.service_recoder.databinding.ActivityActiveListBinding
import com.yjkj.service_recoder.java.adapter.ActiveListAdapter
import com.yjkj.service_recoder.java.base.BaseActivity
import com.yjkj.service_recoder.java.http.OkHttpUtil
import com.yjkj.service_recoder.java.http.medicalservice.API
import com.yjkj.service_recoder.java.utils.clickNoRepeat2
import com.yjkj.service_recoder.library.dsl.registerTextWatcher
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class ActiveListActivity : BaseActivity<ActivityActiveListBinding>() {

    private val activeAdapter by lazy {
        ActiveListAdapter()
    }

    private var adPosition : String? = ""
    override fun onResume() {
        super.onResume()
        hideStatusBar()
        adPosition = intent.getStringExtra("msg")
        getListData()

        activeAdapter.OnItemClickCallback {
            val bundle = Bundle()
            bundle.putSerializable(ActiveDetailActivity.ACTIVE_DETAIL_KEY,it)
            go(ActiveDetailActivity::class.java,bundle)
        }

        viewBinding.searchEdit.addTextChangedListener(registerTextWatcher {
            onTextChanged { s, start, before, count ->
                if(count == 0){
                    getListData()
                }
            }

            afterTextChanged {
                if(it.toString().isNotEmpty()){
                    clickNoRepeat2 {
                        getListData(it.toString())
                    }

                }
            }
        })
    }

    private fun getListData(title : String = ""){
        val jsonObject = JSONObject()
        jsonObject.put("adPosition", adPosition)
        if(title.isNotEmpty()){
            jsonObject.put("title", title)
        }
        //活动banner
        OkHttpUtil.getInstance()
            .doPost(API.homeActivityBannerUrl, jsonObject.toString(), object : Callback {
                override fun onFailure(call: Call, e: IOException) {}

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    if (response.body != null) {
                        val (code, _, rows) = Gson().fromJson(
                            response.body!!.string(),
                            HomeBannerEntity::class.java
                        )
                        if (code == 200 && rows.isNotEmpty()) {
                            activity.runOnUiThread {
                                viewBinding.activeListRv.apply {
                                    layoutManager = LinearLayoutManager(this@ActiveListActivity)
                                    adapter = activeAdapter
                                    activeAdapter.setData(rows.toMutableList())
                                }

                            }
                        }
                    }
                }
            })
    }

    fun launchSearch(view: View){
        val s = viewBinding.searchEdit.text.toString()
        if(s.isNotEmpty()){
            getListData(s)
        }
    }

}