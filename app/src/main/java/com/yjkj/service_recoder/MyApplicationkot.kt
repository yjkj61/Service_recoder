package com.yjkj.service_recoder

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.multidex.MultiDex
import androidx.room.Room
import com.tencent.mmkv.MMKV
import com.yjkj.service_recoder.java.utils.AppDatabase


class MyApplicationkot : Application(), ViewModelStoreOwner {
    private lateinit var mAppViewModelStore: ViewModelStore

    var db: AppDatabase? = null

    var instances: MyApplicationkot? = null

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
        MMKV.initialize(this)
    }

    companion object {
        val instances: MyApplicationkot? = null
        @SuppressLint("StaticFieldLeak")
        lateinit var context : Context
        @JvmStatic
        fun getInstance(): MyApplicationkot {
            if (instances == null) {
                throw NullPointerException("please inherit BaseApplication or call setApplication.")
            }

            return instances as MyApplicationkot
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        instances = this
        mAppViewModelStore = ViewModelStore()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "table_name"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

//    override val viewModelStore: ViewModelStore
//        get() = mAppViewModelStore\

    override fun getViewModelStore(): ViewModelStore {
        TODO("Not yet implemented")
    }

}