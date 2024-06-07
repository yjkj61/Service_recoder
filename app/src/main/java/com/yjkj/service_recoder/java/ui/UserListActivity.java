package com.yjkj.service_recoder.java.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.yjkj.property_management.ui.page.personal.PersonalFragment;
import com.yjkj.service_recoder.databinding.ActivityDetailListBinding;
import com.yjkj.service_recoder.java.adapter.UserlistAdapter;
import com.yjkj.service_recoder.java.base.BaseActivity;
import com.yjkj.service_recoder.java.bean.UserListBean;
import com.yjkj.service_recoder.java.http.API;
import com.yjkj.service_recoder.java.http.OkHttpUtil;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @description 睡眠垫
 * @author: jhw
 * @date: 2024/5/25
 */
public class UserListActivity extends BaseActivity<ActivityDetailListBinding> {

    UserlistAdapter adapter = null;

    private List<UserListBean.RowsBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        super.initView();

        viewBinding.tvTitle.setText("人员选择");

        GridLayoutManager linearLayoutManager = new GridLayoutManager(activity, 5);
        viewBinding.recyclweview.setLayoutManager(linearLayoutManager);
        adapter = new UserlistAdapter(this);
        viewBinding.recyclweview.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.setClass(UserListActivity.this, PersonalFragment.class);
                intent.putExtra("ownerEntity", list.get(position));
                startActivity(intent);
            }
        });

        getList();
    }

    public void back(){
        finish();
    }

    public int pageNum = 1;

    public int pageSize = 100;

    //人员列表
    private void getList(){
        OkHttpUtil.getInstance().doGet(API.sleep + "pageNum=" + pageNum + "&pageSize=" + pageSize, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                UserListBean bean = new Gson().fromJson(response.body().string(), UserListBean.class);
                if (bean.getCode() == 200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            list.clear();
                            list.addAll(bean.getRows());
                            adapter.setNewData(bean.getRows());
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
