package com.yjkj.service_recoder.java.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.gson.Gson;
import com.yjkj.service_recoder.R;
import com.yjkj.service_recoder.databinding.ActivityDetailListBinding;
import com.yjkj.service_recoder.java.adapter.HealthCardAdapter;
import com.yjkj.service_recoder.java.base.BaseActivity;
import com.yjkj.service_recoder.java.base.HealthBean;
import com.yjkj.service_recoder.java.bean.LastPhysicalExamination;
import com.yjkj.service_recoder.java.http.API;
import com.yjkj.service_recoder.java.http.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @description 健康检测
 * @author: jhw
 * @date: 2024/5/25
 */
public class HealthCardActivity extends BaseActivity<ActivityDetailListBinding> {

    HealthCardAdapter adapter = null;

    private int ownerid = 0;

    private List<HealthBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        super.initView();

        viewBinding.tvTitle.setText("健康检测");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 3);
        viewBinding.recyclweview.setLayoutManager(gridLayoutManager);
        adapter = new HealthCardAdapter(this);
        viewBinding.recyclweview.setAdapter(adapter);

        getList();
    }

    public void back() {
        finish();
    }

    //获取检测监测信息
    private void getList() {
        ownerid = getIntent().getIntExtra("id", 0);
        OkHttpUtil.getInstance().doGet(API.Health_Card + ownerid, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.body() != null) {
                    LastPhysicalExamination bean = new Gson().fromJson(response.body().string(), LastPhysicalExamination.class);
                    if (bean.getCode() == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                list.add(new HealthBean("血压", bean.getData().getHHypotension() + "/" + bean.getData().getHHypertension(), 0, R.drawable.icon_bg_xy));
                                list.add(new HealthBean("体温", bean.getData().getHTemperatureValue(), 0, R.drawable.icon_bg_tw));
                                list.add(new HealthBean("血氧", bean.getData().getHBloodOxygenSaturation(), 0, R.drawable.icon_bg_xueyang));
                                list.add(new HealthBean("脉搏", bean.getData().getHPulseRate(), 0, R.drawable.icon_bg_mb));
                                list.add(new HealthBean("血糖", bean.getData().getHBloodSugarValue(), 0, R.drawable.icon_bg_xt));
                                list.add(new HealthBean("尿酸", bean.getData().getHUricAcidValue(), 0, R.drawable.icon_bg_ns));
                                list.add(new HealthBean("胆固醇", bean.getData().getHCholesterolContent(), 0, R.drawable.icon_bg_dgc));
                                list.add(new HealthBean("血脂四项", bean.getData().getHTcValue() + "/" + bean.getData().getHTriglycerideValue()
                                        + "/" + bean.getData().getHHdlValue() + "/" + bean.getData().getHLdlValue(), 0, R.drawable.icon_bg_xzsx));
                                adapter.setNewData(list);
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
    }

}
