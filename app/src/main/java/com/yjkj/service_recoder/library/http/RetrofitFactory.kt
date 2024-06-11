package com.yjkj.service_recoder.library.http

import android.text.TextUtils
import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.yjkj.service_recoder.MyApplication
import com.yjkj.service_recoder.java.data.UserDataHelper
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 * des Retrofit工厂类
 * @author hxy
 * @date 2020-05-09
 */
object RetrofitFactory {


    val client : OkHttpClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(
                20000,
                TimeUnit.MILLISECONDS
            )
            .connectTimeout(
                20000,
                TimeUnit.MILLISECONDS
            )
            .addInterceptor(HeaderBuilder())
            .addInterceptor(getLogInterceptor())
            .cookieJar(getCookie())
            .cache(getCache())
            .build()
    }

    fun factory(): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiContents.BASE_URL)
            .build()
    }




    /**
     * 获取日志拦截器
     */
    private fun getLogInterceptor():Interceptor{
        //http log 拦截器
        return HttpLoggingInterceptor("OkHttp").apply {
                setPrintLevel(HttpLoggingInterceptor.Level.BODY)
                setColorLevel(Level.INFO)
            }
    }

    /**
     * cookie持久化
     */
      fun getCookie(): ClearableCookieJar {
        return PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(MyApplication.context)
        )
    }

    private fun getCache():Cache{
        //缓存100Mb
        return Cache( File(MyApplication.context.cacheDir, "cache")
            , 1024 * 1024 * 300)
    }


}

class HeaderBuilder : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        if(!TextUtils.isEmpty(UserDataHelper.token())){
            request.addHeader("Authorization", "Bearer ${UserDataHelper.token()}")
        }
        return chain.proceed(request.build())
    }

}

