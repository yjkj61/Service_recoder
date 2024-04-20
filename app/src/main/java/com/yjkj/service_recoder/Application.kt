package com.yjkj.service_recoder

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.multidex.MultiDex
import com.tencent.mmkv.MMKV


class Application : Application(), ViewModelStoreOwner {
    private lateinit var mAppViewModelStore: ViewModelStore


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
        MMKV.initialize(this)
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        mAppViewModelStore = ViewModelStore()
    }

    override val viewModelStore: ViewModelStore
        get() = mAppViewModelStore


    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context : Context
    }

}