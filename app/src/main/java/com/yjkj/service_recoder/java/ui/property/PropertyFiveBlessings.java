package com.yjkj.service_recoder.java.ui.property;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yjkj.service_recoder.MyApplication;
import com.yjkj.service_recoder.java.bean.ADRow;
import com.yjkj.service_recoder.java.bean.HomeAdBannerEntity;
import com.yjkj.service_recoder.java.bean.HomeBannerEntity;
import com.yjkj.service_recoder.java.bean.Row;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.yjkj.service_recoder.R;
import com.yjkj.service_recoder.databinding.FragmentPropertyFiveBlessingsBinding;
import com.yjkj.service_recoder.java.adapter.BannerAdapter;
import com.yjkj.service_recoder.java.adapter.HomeActiveBannerAdapter;
import com.yjkj.service_recoder.java.adapter.HomeAdBannerAdapter;
import com.yjkj.service_recoder.java.adapter.HomeBannerAdapter;
import com.yjkj.service_recoder.java.base.BaseActivity;
import com.yjkj.service_recoder.java.bean.ActiveBannerEntity;
import com.yjkj.service_recoder.java.bean.ActiveRow;
import com.yjkj.service_recoder.java.bean.BannerBean;
import com.yjkj.service_recoder.java.bean.BannerHomeBean;
import com.yjkj.service_recoder.java.bean.ProperTypeBean;
import com.yjkj.service_recoder.java.http.OkHttpUtil;
import com.yjkj.service_recoder.java.http.medicalservice.API;
import com.yjkj.service_recoder.java.ui.ActiveListActivity;
import com.yjkj.service_recoder.java.ui.ArticleListActivity;
import com.youth.banner.listener.OnPageChangeListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PropertyFiveBlessings extends BaseActivity<FragmentPropertyFiveBlessingsBinding> {


    private List<ProperTypeBean> properTypeBeans = new ArrayList<>();


    private class MyLifecycleObserver implements LifecycleObserver, LifecycleOwner {

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        public void onCreate() {
            // 响应ON_CREATE事件
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            // 响应ON_START事件
        }

        @NonNull
        @Override
        public Lifecycle getLifecycle() {
            return null;
        }

        // 可以添加更多的事件响应方法
    }

    @Override
    public void initView() {
        super.initView();

        viewBinding.order.setOnClickListener(v -> {
            go(OrderView.class);
        });

//        initBanner();
    }

    @Override
    public void onResume() {
        super.onResume();
        initBanner();
        try {
            initMyBanner();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void initBanner() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        viewBinding.cardList.setLayoutManager(linearLayoutManager);

        AdapterCardList adapterCardList = new AdapterCardList();

        viewBinding.cardList.setAdapter(adapterCardList);


        OkHttpUtil.getInstance().doGet(API.homeBanner, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.body() != null) {

                    List<BannerBean> bannerBeans = new ArrayList<>();

                    BannerHomeBean bannerHomeBean = new Gson().fromJson(response.body().string(), BannerHomeBean.class);

                    if (bannerHomeBean != null && bannerHomeBean.getCode() == 200) {

                        for (int i = 0; i < bannerHomeBean.getRows().size(); i++) {
                            BannerHomeBean.RowsDTO rowsDTO = bannerHomeBean.getRows().get(i);
                            String[] imgaes = rowsDTO.getImage().split(",");
                            bannerBeans.add(new BannerBean(imgaes[0], imgaes[1], imgaes[2]));
                        }
                    }


                    activity.runOnUiThread(() -> viewBinding.bannerPropertyFiveBlessings
                            .setAdapter(new BannerAdapter(bannerBeans))
                            .addBannerLifecycleObserver(PropertyFiveBlessings.this)
                            .addOnPageChangeListener(new OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                }

                                @Override
                                public void onPageSelected(int position) {

                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {

                                }
                            }));


                }

            }
        });


        getTypeList(adapterCardList);

    }

    private void getTypeList(AdapterCardList adapterCardList) {
        OkHttpUtil.getInstance().doGet(API.selectAllTypes, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {


                if (response.body() != null) {
                    JsonArray jsonArray = JsonParser.parseString(response.body().string()).getAsJsonArray();
                    Gson gson = new Gson();
                    for (JsonElement jsonElement : jsonArray) {
                        //使用GSON，直接转成Bean对象
                        ProperTypeBean properTypeBean = gson.fromJson(jsonElement, ProperTypeBean.class);

                        if (properTypeBean.getFTypeName().contains("物业")) {
                            properTypeBean.setImageSource(R.drawable.house);
                            properTypeBean.setBackground(R.drawable.property_five_blessings_back1);
                        }
                        if (properTypeBean.getFTypeName().contains("适老")) {
                            properTypeBean.setImageSource(R.drawable.building);
                            properTypeBean.setBackground(R.drawable.property_five_blessings_back2);

                        }

                        if (!haveIt(properTypeBean)) {
                            properTypeBeans.add(properTypeBean);
                        }

                    }

                    activity.runOnUiThread(() -> adapterCardList.notifyDataSetChanged());


                }
            }
        });
    }


    private boolean haveIt(ProperTypeBean properTypeBean) {


        for (ProperTypeBean bean : properTypeBeans) {
            if (properTypeBean.getFTypeId() == bean.getFTypeId()) {
                return true;
            }
        }
        return false;
    }

    class AdapterCardList extends RecyclerView.Adapter<AdapterCardList.ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_card_item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            ProperTypeBean properTypeBean = properTypeBeans.get(position);

            holder.card.setBackgroundResource(properTypeBean.getBackground());
            holder.iconType.setImageResource(properTypeBean.getImageSource());
            holder.name.setText(properTypeBean.getFTypeName());
            holder.context.setText(properTypeBean.getFTypeIntroduction());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    go(PropertyCardService.class, String.valueOf(properTypeBean.getFTypeId()));
                }
            });
        }

        @Override
        public int getItemCount() {
            return properTypeBeans.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private LinearLayout card;
            private ImageView iconType;
            private TextView name, context;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                card = itemView.findViewById(R.id.card);
                iconType = itemView.findViewById(R.id.icon_type);
                name = itemView.findViewById(R.id.name);
                context = itemView.findViewById(R.id.context);
            }
        }
    }

    private void initMyBanner() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("adPosition","1");
        //广告banner
        OkHttpUtil.getInstance().doPost(API.homeAdBannerUrl, jsonObject.toString(),new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.body() != null) {

                    HomeAdBannerEntity bannerHomeBean = new Gson().fromJson(response.body().string(), HomeAdBannerEntity.class);

                    if ( bannerHomeBean.getCode() == 200) {
                        List<ADRow> rows = bannerHomeBean.getRows();
                        activity.runOnUiThread(()->{
                            HomeAdBannerAdapter homeAdBannerAdapter = new HomeAdBannerAdapter(rows);
                            homeAdBannerAdapter.OnItemClickCallback(new Function1<ADRow, Unit>() {
                                @Override
                                public Unit invoke(ADRow adRow) {
                                    Intent intent =new  Intent();
                                    intent.setAction("android.intent.action.VIEW");
                                    intent.setData(Uri.parse(adRow.getAdData().replace("<p>","").replace("</p>","")));
                                    startActivity(intent);
                                    return null;
                                }
                            });
                            viewBinding.bannerHome.addBannerLifecycleObserver(PropertyFiveBlessings.this).setAdapter(homeAdBannerAdapter);
                        });
                    }


//                    activity.runOnUiThread(() -> viewBinding.bannerHome.addBannerLifecycleObserver(getViewLifecycleOwner())
//                            .setAdapter(new BannerAdapter(bannerBeans))
//                            .addOnPageChangeListener(new OnPageChangeListener() {
//                                @Override
//                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                                }
//
//                                @Override
//                                public void onPageSelected(int position) {
//
//                                }
//
//                                @Override
//                                public void onPageScrollStateChanged(int state) {
//
//                                }
//                            }));

                }

            }
        });
        //资讯banner
        OkHttpUtil.getInstance().doPost(API.homeArticleBannerUrl, jsonObject.toString(),new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.body() != null) {

                    HomeBannerEntity bannerHomeBean = new Gson().fromJson(response.body().string(), HomeBannerEntity.class);

                    if ( bannerHomeBean.getCode() == 200 && bannerHomeBean.getRows().size() != 0) {
                        List<Row> rows = bannerHomeBean.getRows();
                        activity.runOnUiThread(()->{
                            HomeBannerAdapter homeBannerAdapter = new HomeBannerAdapter(rows);
                            homeBannerAdapter.OnItemClickCallback(new Function1<Row, Unit>() {
                                @Override
                                public Unit invoke(Row row) {
                                    go(ArticleListActivity.class,"1");
                                    return null;
                                }
                            });
                            viewBinding.bannerHome1.addBannerLifecycleObserver(PropertyFiveBlessings.this).isAutoLoop(false).setAdapter(homeBannerAdapter);
                        });
                    }
                }

            }
        });
        //活动banner
        OkHttpUtil.getInstance().doPost(API.homeActivityBannerUrl, jsonObject.toString(),new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.body() != null) {

                    ActiveBannerEntity bannerHomeBean = new Gson().fromJson(response.body().string(), ActiveBannerEntity.class);

                    if ( bannerHomeBean.getCode() == 200 && bannerHomeBean.getRows().size() != 0) {
                        List<ActiveRow> rows = bannerHomeBean.getRows();
                        activity.runOnUiThread(()->{
                            HomeActiveBannerAdapter homeBannerAdapter = new HomeActiveBannerAdapter(rows);
                            homeBannerAdapter.OnItemClickCallback(new Function1<ActiveRow, Unit>() {
                                @Override
                                public Unit invoke(ActiveRow activeRow) {
                                    go(ActiveListActivity.class,"1");
                                    return null;
                                }
                            });
                            viewBinding.bannerHome2.addBannerLifecycleObserver(PropertyFiveBlessings.this).isAutoLoop(false).setAdapter(homeBannerAdapter);
                        });
                    }

                }

            }
        });

    }

}