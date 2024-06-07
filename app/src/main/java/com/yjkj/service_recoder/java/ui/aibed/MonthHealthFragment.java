package com.yjkj.service_recoder.java.ui.aibed;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.widget.GridView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.google.gson.Gson;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.yjkj.service_recoder.BR;
import com.yjkj.service_recoder.R;
import com.yjkj.service_recoder.java.adapter.MsAdapter;
import com.yjkj.service_recoder.java.bean.GridBean;
import com.yjkj.service_recoder.java.diyView.ChartView;
import com.yjkj.service_recoder.java.diyView.QQStepView;
import com.yjkj.service_recoder.java.entity.SleepTime;
import com.yjkj.service_recoder.library.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class MonthHealthFragment extends BaseFragment {

    private QQStepView stepView;

    private BarChart barChart;

    private GridView circleGrid;

    private MonthHealthViewModel viewModel;


    @Override
    protected void initViewModel() {
        viewModel = getActivityScopeViewModel(MonthHealthViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_month_health, BR.viewModel,viewModel);
    }

    @Override
    protected void lazyInit() {
        super.lazyInit();
        initView();
        setBarData();
        stepView.setStepMax(100);

        //属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 50);
        //先快后慢的效果
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(animation -> {
            float animatedValue = (float) animation.getAnimatedValue();
            stepView.setCurrentStep((int) animatedValue, "day");
        });
        stepView.postDelayed(valueAnimator::start, 1000);//这里延时5s便于人眼观察

        List<GridBean> gridBeans = new ArrayList<>();

        gridBeans.add(new GridBean(23, "day", "本月打鼾", 30));
        gridBeans.add(new GridBean(98, " 分", "本月睡眠效率", 100));
        gridBeans.add(new GridBean(18, "day", "夜间离床", 30));
        gridBeans.add(new GridBean(20, "day", "本月平均体动", 30));


        MsAdapter msAdapter = new MsAdapter<GridBean>(gridBeans, R.layout.circle_progress_item) {

            @Override
            public void bindView(ViewHolder holder, GridBean obj) {
                QQStepView qqStepView = holder.getView(R.id.step_view);
                TextView type = holder.getView(R.id.type);

                TextView bottom_text = holder.getView(R.id.bottom_text);

                qqStepView.setStepMax(obj.getMax());
                qqStepView.setCurrentStep((int) obj.getToData(), "");
                type.setText(obj.getType());
                bottom_text.setText(obj.getBottomText());
            }
        };
        circleGrid.setAdapter(msAdapter);
    }

    private void initView(){
        stepView = getBinding().getRoot().findViewById(R.id.step_view);
        barChart = getBinding().getRoot().findViewById(R.id.barChart);
        circleGrid = getBinding().getRoot().findViewById(R.id.circle_grid);
    }

    private void setBarData() {
        List<SleepTime> sleepTimes = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            SleepTime sleepTime = new SleepTime();
            List<Float> childBens = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                if (j == 1) {
                    childBens.add(0f);
                } else {
                    childBens.add((float) (4 + j));
                }
            }
            sleepTime.setDatTime("08-14");
            sleepTime.setChildBens(childBens);

            sleepTimes.add(sleepTime);
        }

        Log.d("json", new Gson().toJson(sleepTimes));

        ChartView chartView = new ChartView();
        chartView.setBarChart(barChart, sleepTimes);

    }
}
