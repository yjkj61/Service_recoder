package com.yjkj.service_recoder.java.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.yjkj.service_recoder.databinding.ActivitySubmitOrderViewBinding;
import com.yjkj.service_recoder.java.adapter.MainActivityPageAdapter;
import com.yjkj.service_recoder.java.base.BaseActivity;
import com.yjkj.service_recoder.java.bean.JudegeBean;
import com.yjkj.service_recoder.java.ui.property.fragment.submitOrderFragment.JudegeFragment;
import com.yjkj.service_recoder.java.ui.property.fragment.submitOrderFragment.PayFragment;
import com.yjkj.service_recoder.java.ui.property.fragment.submitOrderFragment.SureAddressFragment;
import com.yjkj.service_recoder.java.ui.property.fragment.submitOrderFragment.SureOrderInfoFragment;
import com.yjkj.service_recoder.java.utils.GetOrderForShop;

import java.util.ArrayList;
import java.util.List;

public class SubmitOrderView extends BaseActivity<ActivitySubmitOrderViewBinding> {

    ArrayList<Fragment> fragments = new ArrayList<>();

    public static SubmitOrderView mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        super.initView();
        mActivity = this;

        String type = getIntent().getStringExtra("type");

        tabSwitch(0 );
        if(type != null){
            if(type.equals("JudegeFragment")){
                JudegeBean judegeBean = (JudegeBean) getIntent().getSerializableExtra("judegeBean");

                List<JudegeBean> judegeBeans = new ArrayList<>();
                judegeBeans.add(judegeBean);
                fragments.add(JudegeFragment.newInstance(judegeBeans));
                tabSwitch(3 );
            }
        }else {
            List<JudegeBean> judegeBeans = new ArrayList<>();
           // GetOrderForShop.getInstance().getBuyGoodBeans();

            fragments.add(SureAddressFragment.newInstance(GetOrderForShop.getInstance().getAddressId()));
            fragments.add(SureOrderInfoFragment.newInstance());
            fragments.add(PayFragment.newInstance());
            fragments.add(JudegeFragment.newInstance(judegeBeans));


        }

        viewBinding.back.setOnClickListener(v -> finish());




        MainActivityPageAdapter mainActivityPageAdapter = new MainActivityPageAdapter(getSupportFragmentManager(), fragments);

        viewBinding.viewPager.setAdapter(mainActivityPageAdapter);
        viewBinding.viewPager.setNoScroll(true);
        viewBinding.viewPager.setCurrentItem(0);



    }

    public void tabSwitch(int i) {
        viewBinding.viewPager.setCurrentItem(i);
        switch (i) {
            case 0:
                select(viewBinding.stepBox1);
                unSelect(viewBinding.stepBox2);
                unSelect(viewBinding.stepBox3);
                unSelect(viewBinding.stepBox4);
                break;
            case 1:
                select(viewBinding.stepBox2);
                unSelect(viewBinding.stepBox1);
                unSelect(viewBinding.stepBox3);
                unSelect(viewBinding.stepBox4);
                break;
            case 2:
                select(viewBinding.stepBox3);
                unSelect(viewBinding.stepBox1);
                unSelect(viewBinding.stepBox2);
                unSelect(viewBinding.stepBox4);
                break;
            case 3:
                select(viewBinding.stepBox4);
                unSelect(viewBinding.stepBox1);
                unSelect(viewBinding.stepBox2);
                unSelect(viewBinding.stepBox3);
                break;
        }
    }



    private void select(MaterialCardView cardView) {

        cardView.setCardBackgroundColor(Color.parseColor("#FF9E99F8"));

    }

    private void unSelect(MaterialCardView cardView) {
        cardView.setCardBackgroundColor(Color.parseColor("#FFC9C9C9"));
    }
}