package com.yjkj.service_recoder.java.ui

import android.os.Bundle
import android.text.Html
import com.yjkj.service_recoder.java.bean.Row
import com.yjkj.service_recoder.databinding.ActivityArticleDetialBinding
import com.yjkj.service_recoder.java.base.BaseActivity

class ArticleDetailActivity : BaseActivity<ActivityArticleDetialBinding>() {
    override fun onResume() {
        super.onResume()
        hideStatusBar()
        intent.extras?.let {
            val row = it.getSerializable(ARTICLE_DETAIL_KEY) as Row
            viewBinding.apply {
                articleTitle.text = row.title
                publishAuthor.text = row.articleCreatorName
                publishTime.text = row.publishTime
                articleContent.text = Html.fromHtml(row.content);
//                articleContent.text = row.content.replace("<p>","").replace("</p>","").replace("<p></p>","")
            }
        }
    }

    companion object{
        const val ARTICLE_DETAIL_KEY = "article_detail"
    }
}