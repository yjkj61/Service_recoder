package com.yjkj.service_recoder.java.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yjkj.service_recoder.databinding.ActivityHardsoftDeviceBinding;
import com.yjkj.service_recoder.java.base.BaseActivity;
import com.yjkj.service_recoder.java.bean.OwnerListType;
import com.yjkj.service_recoder.java.ui.aibed.AiBedActivity;

/**
 * @description 物联网设备
 * @author: jhw
 * @date: 2024/5/25
 */
public class HardSoftDeviceActivity extends BaseActivity<ActivityHardsoftDeviceBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        super.initView();

        viewBinding.tvTitle.setText("物联网设备");

        setClick();
    }

    public void setClick(){
        viewBinding.relSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HardSoftDeviceActivity.this, AiBedActivity.class);
                intent.putExtra("page_type_param", OwnerListType.OWNER_BED_WARNING);
                startActivity(intent);
            }
        });

        viewBinding.relNbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        viewBinding.relNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        viewBinding.igBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
    }

}
