package com.yjkj.service_recoder.java.utils;

import static android.content.Context.ACTIVITY_SERVICE;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.yjkj.service_recoder.java.entity.UpdateAppEntity;
import com.yjkj.service_recoder.java.http.OkHttpUtil;
import com.yjkj.service_recoder.java.http.medicalservice.API;
import com.yjkj.service_recoder.java.ui.UpdateAppDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @description 通用工具类
 * @author: Lenovo
 * @date: 2024/6/11
 */
public class ToolUtils {

    public static final List<String> mPermissionList = new ArrayList<>();

    public static final String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static final int mRequestCode = 100;//权限请求码

    public static void initPermission(Activity activity) {
        mPermissionList.clear();//清空已经允许的没有通过的权限
        //逐个判断是否还有未通过的权限
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permission);//添加还未授予的权限到mPermissionList中
            }
        }
        //申请权限
        if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
            ActivityCompat.requestPermissions(activity, permissions, mRequestCode);
        }

    }

    public static void speedUp(Activity activity){
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(ACTIVITY_SERVICE);
        long beforeMem = getAvailMemory(activity);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if(runningAppProcesses != null && !runningAppProcesses.isEmpty()){
            for (ActivityManager.RunningAppProcessInfo runningApp : runningAppProcesses) {
                if (runningApp.importance > ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                    String[] pkgList = runningApp.pkgList;
                    for (String pkg : pkgList) {
                        activityManager.killBackgroundProcesses(pkg);
                    }
                }
            }
        }
        long afterMem = getAvailMemory(activity);
        Toast.makeText(activity, "为您节省了" + (afterMem - beforeMem) + "M内存", Toast.LENGTH_SHORT).show();
    }

    //获取当前可用内存
    public static long getAvailMemory(Activity activity) {
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / (1024 * 1024);
    }

    public static void showSeekBarVolume(Activity activity){
        AudioManager audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        audioManager.adjustVolume(AudioManager.ADJUST_SAME, AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);

    }

    public static void toSystemSetting(Activity activity){
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(intent);
        }
    }

    public static void checkUpdate(Activity activity, Fragment fragment) throws JSONException, PackageManager.NameNotFoundException {
        String type = "0";
        PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",type);
        jsonObject.put("version","v"+packageInfo.versionName);
        OkHttpUtil.getInstance().doPost(API.updateAppUIrl, jsonObject.toString(), new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody body = response.body();
                if(body != null){
                    UpdateAppEntity updateAppEntity = new Gson().fromJson(body.string(), UpdateAppEntity.class);
                    if(updateAppEntity.getCode() == 200){
                        if(updateAppEntity.component2().getMsg().contains("当前为最新版本")){
                            ToastUtil.showToast(updateAppEntity.getMsg(),activity);
                            return;
                        }
                        if(updateAppEntity.component2().getMsg().contains("新版本版本号为")){
                            UpdateAppDialogFragment dialogFragment = new UpdateAppDialogFragment();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(UpdateAppDialogFragment.UPDATE_APP_ENTITY,updateAppEntity.component2());
                            dialogFragment.setArguments(bundle);
                            dialogFragment.show(fragment.getChildFragmentManager(),"update_dialog");
                        }
                    }
                }
            }
        });
    }

    public static void getWritePermisson(Activity activity){
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (activity.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    activity.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                } else {
                    //这里就是权限打开之后自己要操作的逻辑
                }
            }
        }
    }

}
