package com.yjkj.service_recoder.java.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yjkj.service_recoder.databinding.ActivityDetailListBinding;
import com.yjkj.service_recoder.java.adapter.HlListAdapter;
import com.yjkj.service_recoder.java.base.BaseActivity;
import com.yjkj.service_recoder.java.bean.HlListBean;
import com.yjkj.service_recoder.java.http.API;
import com.yjkj.service_recoder.java.http.OkHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @description 护理列表
 * @author: jhw
 * @date: 2024/5/25
 */
public class HlListActivity extends BaseActivity<ActivityDetailListBinding> {

    HlListAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        super.initView();

        viewBinding.tvTitle.setText("护理列表");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        viewBinding.recyclweview.setLayoutManager(linearLayoutManager);
        adapter = new HlListAdapter(this);
        viewBinding.recyclweview.setAdapter(adapter);

        getList();

        viewBinding.igBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public int pageNum = 1;

    public int pageSize = 100;

    private int ownerid = 0;

    //护理列表
    private void getList() {
        ownerid = getIntent().getIntExtra("id", 0);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ownerId", ownerid + "");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        OkHttpUtil.getInstance().doPost(API.HL_LIST, jsonObject.toString(), new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                Log.i("getList", response.body().string());
                List<HlListBean> bean = new Gson().fromJson(response.body().string(), new TypeToken<List<HlListBean>>(){}.getType());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setNewData(bean);
                    }
                });
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
    }

}
