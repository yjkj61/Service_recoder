package com.yjkj.service_recoder.java.ui

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yjkj.service_recoder.java.bean.HomeBannerEntity
import com.yjkj.service_recoder.java.bean.Row
import com.google.gson.Gson
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.yjkj.service_recoder.databinding.ActivityArticleListBinding
import com.yjkj.service_recoder.java.adapter.ArticleListAdapter
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

/**
* @Author hxy
* Create at 2024/1/26
* @desc 资讯二级页面
*/
class ArticleListActivity : BaseActivity<ActivityArticleListBinding>() {

    private val articleAdapter by lazy {
        ArticleListAdapter()
    }

    private  var adPosition : String?=""

    override fun onResume() {
        super.onResume()
        hideStatusBar()
        adPosition =  intent.getStringExtra("msg")
        getListData()
        viewBinding.backButton.setOnClickListener {
            finish()
        }

        viewBinding.searchButton.setOnClickListener {
            val s = viewBinding.searchArticleEdit.text.toString()
            if(s.isBlank()){
                return@setOnClickListener
            }
            getListData(s)
        }

        viewBinding.searchArticleEdit.addTextChangedListener(registerTextWatcher {
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

        articleAdapter.OnItemClickCallback {
            val bundle = Bundle()
            bundle.putSerializable(ArticleDetailActivity.ARTICLE_DETAIL_KEY,it)
            go(ArticleDetailActivity::class.java,bundle)
        }
    }



    private fun getListData(title : String = ""){
        val jsonObject = JSONObject()
        jsonObject.put("adPosition", adPosition)
        if(title.isNotEmpty()){
            jsonObject.put("title", title)
        }
        //资讯列表
        OkHttpUtil.getInstance()
            .doPost(API.homeArticleBannerUrl, jsonObject.toString(), object : Callback {
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
                                viewBinding.articleRv.apply {
                                    layoutManager = GridLayoutManager(this@ArticleListActivity, 1)
                                    adapter = articleAdapter
                                    articleAdapter.setArticleData(rows)
                                }

                            }
                        }
                    }
                }
            })
    }

}