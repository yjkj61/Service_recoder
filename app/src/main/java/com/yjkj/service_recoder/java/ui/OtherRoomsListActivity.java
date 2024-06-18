package com.yjkj.service_recoder.java.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yjkj.service_recoder.databinding.ActivityDetailListBinding;
import com.yjkj.service_recoder.java.adapter.HlListAdapter;
import com.yjkj.service_recoder.java.adapter.OtherRoomAdapter;
import com.yjkj.service_recoder.java.base.BaseActivity;
import com.yjkj.service_recoder.java.bean.HlListBean;
import com.yjkj.service_recoder.java.bean.OtherRoomBean;
import com.yjkj.service_recoder.java.http.API;
import com.yjkj.service_recoder.java.http.OkHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @description 其他房间
 * @author Lenovo
 * @time 2024/6/18
 */
public class OtherRoomsListActivity extends BaseActivity<ActivityDetailListBinding> {

    OtherRoomAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        super.initView();

        viewBinding.tvTitle.setText("其他房间");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        viewBinding.recyclweview.setLayoutManager(linearLayoutManager);
        adapter = new OtherRoomAdapter(this);
        viewBinding.recyclweview.setAdapter(adapter);

        viewBinding.igBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        getList();
    }

    public void back() {
        finish();
    }

    public int pageNum = 1;

    public int pageSize = 100;

    private int ownerid = 0;

    //护理列表
    private void getList() {
        OkHttpUtil.getInstance().doGet(API.Other_Room, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                OtherRoomBean bean = new Gson().fromJson(response.body().string(), OtherRoomBean.class);
                if (bean.getCode() == 200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setNewData(Arrays.asList(bean.getData()));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
    }

}
