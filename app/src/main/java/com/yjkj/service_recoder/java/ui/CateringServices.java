package com.yjkj.service_recoder.java.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.yjkj.service_recoder.MyApplication;
import com.yjkj.service_recoder.databinding.FragmentCateringServicesBinding;
import com.yjkj.service_recoder.java.base.BaseActivity;
import com.yjkj.service_recoder.java.bean.CanteenBean;
import com.yjkj.service_recoder.java.bean.CareringServiceData;
import com.yjkj.service_recoder.java.dataBaseBean.UserBean;
import com.yjkj.service_recoder.java.entity.CaterRow;
import com.yjkj.service_recoder.java.entity.CateringBannerEntity;
import com.yjkj.service_recoder.java.http.OkHttpUtil;
import com.yjkj.service_recoder.java.http.medicalservice.API;
import com.yjkj.service_recoder.java.ui.cateringServicesActivity.CateringSelectView;
import com.yjkj.service_recoder.java.ui.cateringServicesActivity.EatCar1;
import com.yjkj.service_recoder.java.ui.cateringServicesActivity.EatCar2;
import com.yjkj.service_recoder.java.ui.cateringServicesActivity.Order.OrderListActivity;
import com.yjkj.service_recoder.java.utils.GlideUtils;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class CateringServices extends BaseActivity<FragmentCateringServicesBinding> {

    public CateringServices() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

//        viewBinding.canteenName.setText(CareringServiceData.getInstance().getrFoodCanteenName());

        updateMoney(handler);
        initBanner();
    }

    @Override
    public void initView() {
        super.initView();

        viewBinding.see.setOnClickListener(v -> go(OrderListActivity.class));
        viewBinding.all.setOnClickListener(v -> go(OrderListActivity.class));

        viewBinding.btnLeft.setOnClickListener(v -> {

            if (!CareringServiceData.getInstance(CateringServices.this).getMarkId().isEmpty()) {
                go(EatCar1.class, "0");
            } else {
                showToast("无食堂供应");
            }


        });
        viewBinding.btnRight.setOnClickListener(v -> {
            if (!CareringServiceData.getInstance(CateringServices.this).getMarkId().isEmpty()) {
                go(EatCar2.class, "1");
            } else {
                showToast("无食堂供应");
            }

        });


        viewBinding.catering.setOnClickListener(v -> go(CateringSelectView.class));
        getCanteen();
    }

    Handler handler = new Handler(msg -> {

        String money = (String) msg.obj;
        viewBinding.ownerRemainMoney.setText("余额：" + money);
        return false;
    });

    private void getCanteen() {

        OkHttpUtil.getInstance().doGet(API.canteenList, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                activity.runOnUiThread(() -> viewBinding.canteenName.setText("暂无食堂供应"));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.body() != null) {
                    CanteenBean canteenBean = new Gson().fromJson(response.body().string(), CanteenBean.class);

                    if (canteenBean.getCode() == 200) {
                        if (canteenBean.getRows().size() > 0) {
                            activity.runOnUiThread(() -> viewBinding.canteenName.setText(canteenBean.getRows().get(0).getRFoodCanteenName()));


                            CareringServiceData.getInstance(CateringServices.this).setMarkId(String.valueOf(canteenBean.getRows().get(0).getMarkerId()));
                            CareringServiceData.getInstance(CateringServices.this).setMarkName(canteenBean.getRows().get(0).getMarkerName());
                            CareringServiceData.getInstance(CateringServices.this).setrFoodCanteenId(String.valueOf(canteenBean.getRows().get(0).getRFoodCanteenId()));
                            CareringServiceData.getInstance(CateringServices.this).setrFoodCanteenName(canteenBean.getRows().get(0).getRFoodCanteenName());
                        } else {
                            activity.runOnUiThread(() -> viewBinding.canteenName.setText("暂无食堂供应"));
                        }
                    } else {
                        activity.runOnUiThread(() -> viewBinding.canteenName.setText("暂无食堂供应"));
                    }

                }
            }
        });

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("markerId", "1");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private void initBanner() {
        List<UserBean> allUser = MyApplication.getInstance().db.userDao().getAllUser();
        Boolean isLogin = false;
        for (UserBean user : allUser) {
            if (user.isLoginStatus()) {
                isLogin = true;
                break;
            }
        }
        if (allUser.size() == 0 || !isLogin) {
            //用户未登录，加载默认轮播图
            viewBinding.defaultBannerImage.setVisibility(View.VISIBLE);
            viewBinding.realBanner.setVisibility(View.GONE);
            return;
        }
        viewBinding.defaultBannerImage.setVisibility(View.GONE);
        viewBinding.realBanner.setVisibility(View.VISIBLE);
        OkHttpUtil.getInstance().doGet(API.cateringBannerUrl, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody body = response.body();
                if (body != null) {
                    CateringBannerEntity bannerEntity = new Gson().fromJson(body.string(), CateringBannerEntity.class);
                    List<CaterRow> rows = bannerEntity.component3();
                    if (rows.isEmpty()) {
                        return;
                    }
                    String[] split = rows.get(0).getImage().split(",");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewBinding.realBanner.isAutoLoop(true).setAdapter(new BannerImageAdapter<String>(Arrays.asList(split)) {
                                @Override
                                public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                                    GlideUtils.load(CateringServices.this, data, holder.imageView);
                                }
                            });
                        }
                    });
                }
            }
        });
    }
}