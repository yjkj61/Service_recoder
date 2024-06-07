package com.yjkj.service_recoder.java.ui.aibed;

import android.graphics.Color;
import android.widget.GridView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;
import com.google.android.material.card.MaterialCardView;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.yjkj.service_recoder.BR;
import com.yjkj.service_recoder.R;
import com.yjkj.service_recoder.java.adapter.AiBegHeaderAdapter;
import com.yjkj.service_recoder.java.adapter.MsAdapter;
import com.yjkj.service_recoder.java.adapter.SleepLayoutChartAdapter;
import com.yjkj.service_recoder.java.adapter.SleepTimeBottomAdapter;
import com.yjkj.service_recoder.java.diyView.ChartView;
import com.yjkj.service_recoder.java.entity.SleepBean;
import com.yjkj.service_recoder.java.entity.SleepStatus;
import com.yjkj.service_recoder.library.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class DayHealthFragment extends BaseFragment {

    private RecyclerView sleepList;

    private RecyclerView timeList;

    private RecyclerView rightRecyclerView;

    private GridView sleepType;

    private LineChart line1;

    private LineChart line2;

    private DayHealthViewModel viewModel;

    @Override
    protected void initViewModel() {
        viewModel = getActivityScopeViewModel(DayHealthViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_day_health, BR.viewModel,viewModel);
    }

    @Override
    protected void lazyInit() {
        super.lazyInit();
        initView();
        initData();
    }

    private void initView(){
        sleepList = getBinding().getRoot().findViewById(R.id.sleep_list);
        timeList = getBinding().getRoot().findViewById(R.id.time);
        sleepType = getBinding().getRoot().findViewById(R.id.sleep_type);
        rightRecyclerView = getBinding().getRoot().findViewById(R.id.right_recyclerView);
        line1 = getBinding().getRoot().findViewById(R.id.line1);
        line2 = getBinding().getRoot().findViewById(R.id.line2);

        List<SleepBean> sleepBeanList = new ArrayList<>();

        sleepBeanList.add(new SleepBean("清醒时间", 1));

        sleepBeanList.add(new SleepBean("快速动眼睡眠", 2));

        sleepBeanList.add(new SleepBean("清醒时间", 1));

        sleepBeanList.add(new SleepBean("深度睡眠", 1));
        sleepBeanList.add(new SleepBean("快速动眼睡眠", 2));

        sleepBeanList.add(new SleepBean("清醒时间", 1));

        sleepBeanList.add(new SleepBean("深度睡眠", 1));

        sleepList.post(() -> {

            //Log.d("TAG", "FragmentDayHealthBinding: "+sleepList.getHeight());
            sleepList.getWidth(); //height可用

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            sleepList.setLayoutManager(linearLayoutManager);

            sleepList.setAdapter(new SleepLayoutChartAdapter(sleepBeanList, 9, sleepList.getWidth()));


            List<String> times = new ArrayList<>();

            times.add("22:00");
            times.add("01:00");
            times.add("04:00");
            times.add("07:00");
            LinearLayoutManager timeLayoutManager = new LinearLayoutManager(getContext());
            timeLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            timeList.setLayoutManager(timeLayoutManager);
            timeList.setAdapter(new SleepTimeBottomAdapter(times, sleepList.getWidth() / times.size()));
        });

    }

    private void initData(){
        List<SleepStatus> sleepStatuses = new ArrayList<>();

        sleepStatuses.add(new SleepStatus("打鼾次数","2次",""));
        sleepStatuses.add(new SleepStatus("睡眠效率","100","合格"));
        sleepStatuses.add(new SleepStatus("体动次数","100次",""));

        sleepStatuses.add(new SleepStatus("离床次数","9次",""));



        MsAdapter msAdapter = new MsAdapter<SleepStatus>(sleepStatuses, R.layout.ai_beg_day_grid_item){

            @Override
            public void bindView(ViewHolder holder, SleepStatus obj) {

                TextView type = holder.getView(R.id.type);
                TextView data = holder.getView(R.id.data);
                TextView context = holder.getView(R.id.context);
                MaterialCardView cardView = holder.getView(R.id.box);

                type.setText(obj.getType());
                data.setText(obj.getData());
                context.setText(obj.getContext());

                switch (obj.getType()){
                    case "打鼾次数":
                        cardView.setCardBackgroundColor(Color.parseColor("#FFDCE3DD"));
                        break;
                    case "睡眠效率":
                        cardView.setCardBackgroundColor(Color.parseColor("#FFC2D8DC"));
                        break;
                    case "体动次数":
                        cardView.setCardBackgroundColor(Color.parseColor("#FFE3DCE2"));
                        break;
                    case "离床次数":
                        cardView.setCardBackgroundColor(Color.parseColor("#FFC2DCCF"));
                        break;
                }

            }
        };

        sleepType.setAdapter(msAdapter);
        initChart();
        initRec();
    }

    private void initChart(){
        ArrayList<String> dateList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dateList.add("");
        }


        List<List<Float>> lists = new ArrayList<>();

        for (int i = 0; i < 1; i++) {

            List<Float> floats = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                floats.add((float) Math.random());
            }

            lists.add(floats);

        }


        ChartView chartView = new ChartView();
        chartView.setLineChartShowFill(line1, dateList, lists,"呼吸频次图");
        chartView.setLineChartShowFill(line2, dateList, lists,"心率记录图");


    }

    private void initRec(){
        List<SleepStatus> sleepStatuses = new ArrayList<>();

        sleepStatuses.add(new SleepStatus("清醒时长  0小时45分钟","0.2","#FFDA96BE"));
        sleepStatuses.add(new SleepStatus("快速动眼睡眠时长  4小时45分钟","0.9","#FFACA4E4"));
        sleepStatuses.add(new SleepStatus("深度睡眠时长  3小时45分钟","0.3","#FF364FA5"));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        rightRecyclerView.setLayoutManager(linearLayoutManager);

        rightRecyclerView.setAdapter(new AiBegHeaderAdapter(sleepStatuses));


    }
}
