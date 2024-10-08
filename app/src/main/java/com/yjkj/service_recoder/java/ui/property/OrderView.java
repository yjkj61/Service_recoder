package com.yjkj.service_recoder.java.ui.property;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yjkj.service_recoder.R;
import com.yjkj.service_recoder.databinding.ActivityOrderViewBinding;
import com.yjkj.service_recoder.java.base.BaseActivity;
import com.yjkj.service_recoder.java.ui.property.fragment.OrderFragment;

import java.util.ArrayList;

public class OrderView extends BaseActivity<ActivityOrderViewBinding> {

    private String[] orderList = new String[]{};

    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void initView() {
        super.initView();

        viewBinding.back.setOnClickListener(v -> finish());
    }

    @Override
    public void initData() {
        super.initData();

        orderList = new String[]{getResources().getString(R.string.all_order)
                , getResources().getString(R.string.wait_service)
                , getResources().getString(R.string.wait_finish)
                , getResources().getString(R.string.to_be_evaluated)
                , getResources().getString(R.string.after_sale)};


        fragments.add(OrderFragment.newInstance(getString(R.string.all_order),""));
        fragments.add(OrderFragment.newInstance(getString(R.string.wait_service),"0"));
        fragments.add(OrderFragment.newInstance(getString(R.string.wait_finish),"1"));
        fragments.add(OrderFragment.newInstance(getString(R.string.to_be_evaluated),"2"));
        fragments.add(OrderFragment.newInstance(getString(R.string.after_sale),"3"));

        viewBinding.viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return orderList[position];
            }
        });
        viewBinding.viewPager.setOffscreenPageLimit(4);

        viewBinding.tl2.setViewPager(viewBinding.viewPager);

        viewBinding.viewPager.setCurrentItem(0);

        viewControl();
    }

    private void viewControl() {
        viewBinding.tl2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewBinding.viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        viewBinding.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewBinding.tl2.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}