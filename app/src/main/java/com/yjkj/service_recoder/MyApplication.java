package com.yjkj.service_recoder;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.multidex.MultiDex;
import androidx.room.Room;

import com.tencent.mmkv.MMKV;
import com.yjkj.service_recoder.java.entity.OwnerEntity;
import com.yjkj.service_recoder.java.utils.AppDatabase;
import com.yjkj.service_recoder.java.utils.CrashHandler;

/**
 * @description
 * @author: Lenovo
 * @date: 2024/6/11
 */
public class MyApplication extends Application implements ViewModelStoreOwner {

    public AppDatabase db = null;

    public static MyApplication instances = null;

    public ViewModelStore mAppViewModelStore = null;

    public static Context context = null;

    public static OwnerEntity ownerEntity = null;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
        MMKV.initialize(this);
    }

    public static MyApplication getInstance(){
        if (null == instances) {
            throw new NullPointerException("please inherit BaseApplication or call setApplication.");
        }
        return instances;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        instances = this;
        mAppViewModelStore = new ViewModelStore();

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "table_name")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        CrashHandler.getInstance().init(getApplicationContext());
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return null;
    }
}
