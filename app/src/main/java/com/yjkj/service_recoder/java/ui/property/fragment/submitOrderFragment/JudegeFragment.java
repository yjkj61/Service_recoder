package com.yjkj.service_recoder.java.ui.property.fragment.submitOrderFragment;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.yjkj.service_recoder.databinding.FragmentJudegeBinding;
import com.yjkj.service_recoder.java.base.java.BaseFragment;
import com.yjkj.service_recoder.java.bean.CareringServiceData;
import com.yjkj.service_recoder.java.bean.JudegeBean;
import com.yjkj.service_recoder.java.http.OkHttpUtil;
import com.yjkj.service_recoder.java.http.medicalservice.API;
import com.yjkj.service_recoder.java.utils.Count;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class JudegeFragment extends BaseFragment<FragmentJudegeBinding> {


    private List<JudegeBean> judegeBeans;

    public JudegeFragment() {
        // Required empty public constructor
    }

    public static JudegeFragment newInstance(List<JudegeBean> judegeBeans) {
        JudegeFragment fragment = new JudegeFragment();
        Bundle args = new Bundle();

        args.putSerializable("data", (Serializable) judegeBeans);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            judegeBeans = (List<JudegeBean>) getArguments().getSerializable("data");
        }
    }

    @Override
    public void initView() {
        super.initView();

        viewBinding.service.setOnRatingChangeListener((ratingBar, rating, fromUser) -> {
            for (JudegeBean judegeBean : judegeBeans
            ) {
                judegeBean.setLogisticsScore(String.valueOf(rating));
            }

        });

        viewBinding.ems.setOnRatingChangeListener((ratingBar, rating, fromUser) -> {
            for (JudegeBean judegeBean : judegeBeans
            ) {
                judegeBean.setServiceScore(String.valueOf(rating));
            }
        });

        viewBinding.goodScore.setOnRatingChangeListener((ratingBar, rating, fromUser) -> {
            for (JudegeBean judegeBean : judegeBeans
            ) {
                judegeBean.setGoodsScore(String.valueOf(rating));
            }
        });


        viewBinding.wuyetuwer.setOnClickListener(v -> activity.finish());

        viewBinding.submit.setOnClickListener(v -> {
            if (viewBinding.goodScore.getRating() == 0f) {
                showToast("请选择此产品评分");
                return;
            }
            if (viewBinding.service.getRating() == 0f) {
                showToast("请选择服务态度评分");
                return;
            }

            if (viewBinding.ems.getRating() == 0f) {
                showToast("请选择物流评分");
                return;
            }


            for (JudegeBean judegeBean : judegeBeans) {

                float serviceScore = viewBinding.service.getRating();
                float logisticsScore = viewBinding.ems.getRating();
                float goodScore = viewBinding.goodScore.getRating();

                float AllScore = serviceScore + logisticsScore + goodScore;

                judegeBean.setCommentsText(viewBinding.commentsText.getText().toString());

                judegeBean.setComprehensiveScore(String.valueOf(Count.division(AllScore, 3)));

                judegeBean.setCommentsImages(CareringServiceData.getInstance(getActivity()).getUserHeader());

                Log.d("TAG", "initView: "+new Gson().toJson(judegeBean));


                OkHttpUtil.getInstance().doPost(API.commentsFirst, new Gson().toJson(judegeBean), new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                        if (response.body() != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                if (jsonObject.getInt("code") == 200) {
                                    activity.runOnUiThread(() -> {
                                        if (judegeBeans.indexOf(judegeBean) == judegeBeans.size() - 1) {
                                            activity.finish();
                                        }

                                    });

                                } else {
                                    activity.runOnUiThread(() -> showToast("请求失败"));
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }

                    }
                });
            }
        });
    }
}