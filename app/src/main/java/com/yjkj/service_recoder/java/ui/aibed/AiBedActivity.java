package com.yjkj.service_recoder.java.ui.aibed;

import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.yjkj.service_recoder.BR;
import com.yjkj.service_recoder.R;
import com.yjkj.service_recoder.java.diyView.NoScrollViewPager;
import com.yjkj.service_recoder.library.base.BaseActivity;

import java.util.ArrayList;

public class AiBedActivity extends BaseActivity {

    private SlidingTabLayout slidingTabLayout;

    private NoScrollViewPager viewPager;

    private AiBedViewModel viewModel;

    private final String[] mTitles = {
            "日", "周", "月"
    };
    private ArrayList<Fragment> fragments = new ArrayList<>();



    @Override
    protected void initViewModel() {
        viewModel = getActivityScopeViewModel(AiBedViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_aibeg, BR.viewModel,viewModel);
    }

    @Override
    protected void initData() {
        super.initData();
        initView();
        viewControl();
    }

    private void initView(){
        slidingTabLayout = getBinding().getRoot().findViewById(R.id.tl_2);
        viewPager = getBinding().getRoot().findViewById(R.id.view_pager);
        fragments.add(new DayHealthFragment());
        fragments.add(new MonthHealthFragment());
        fragments.add(new MonthHealthFragment());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
                return mTitles[position];
            }
        });
        viewPager.setOffscreenPageLimit(4);

        slidingTabLayout.setViewPager(viewPager);

        viewPager.setCurrentItem(0);
        viewPager.setNoScroll(true);

        for (int i = 0; i < slidingTabLayout.getTabCount(); i++) {
            if (0 == i) {
                slidingTabLayout.getTitleView(0).setBackgroundResource(R.drawable.health_tab_select_back);
                slidingTabLayout.getTitleView(0).setLayoutParams(new RelativeLayout.LayoutParams(150,60));
                slidingTabLayout.getTitleView(0).setTranslationY(7);

            } else {
                slidingTabLayout.getTitleView(i).setBackgroundResource(R.drawable.health_tab_unselect_back);
                slidingTabLayout.getTitleView(i).setLayoutParams(new RelativeLayout.LayoutParams(150,60));
                slidingTabLayout.getTitleView(i).setTranslationY(7);

            }
        }
    }

    private void viewControl(){
        slidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                viewPager.setCurrentItem(position);

                for (int i = 0; i < slidingTabLayout.getTabCount(); i++) {
                    if (position == i) {

                        slidingTabLayout.getTitleView(position).setBackgroundResource(R.drawable.health_tab_select_back);
                        slidingTabLayout.getTitleView(0).setLayoutParams(new RelativeLayout.LayoutParams(150,60));
                        slidingTabLayout.getTitleView(0).setTranslationY(7);

                    } else {
                        slidingTabLayout.getTitleView(i).setBackgroundResource(R.drawable.health_tab_unselect_back);
                        slidingTabLayout.getTitleView(i).setLayoutParams(new RelativeLayout.LayoutParams(150,60));
                        slidingTabLayout.getTitleView(i).setTranslationY(7);

                    }
                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

    }
}
