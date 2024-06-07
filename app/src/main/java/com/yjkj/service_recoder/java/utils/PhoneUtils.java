package com.yjkj.service_recoder.java.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PhoneUtils {

    public static int getMissedCallCount(Context context) {
        int missedCallCount = 0;
        ContentResolver contentResolver = context.getContentResolver();

        // 查询未接来电
        String[] projection = new String[]{
                CallLog.Calls.TYPE,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DATE,
                CallLog.Calls.DURATION
        };

        Cursor cursor = contentResolver.query(
                CallLog.Calls.CONTENT_URI,
                projection,
                CallLog.Calls.TYPE + " = ?",
                new String[]{String.valueOf(CallLog.Calls.MISSED_TYPE)},
                null
        );

        if (cursor != null) {
            missedCallCount = cursor.getCount();
            cursor.close();
        }

        return missedCallCount;
    }

    public static Boolean getPermisson(Activity context){
        Boolean result = true;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.READ_CALL_LOG},
                    10002);
            // 处理用户响应的回调将在Activity中实现
            result = false;
        }
        return result;
    }

}
