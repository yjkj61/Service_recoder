package com.yjkj.service_recoder.java.ui.cateringServicesActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.gson.Gson;
import com.yjkj.service_recoder.R;
import com.yjkj.service_recoder.databinding.ActivityEatCar1Binding;
import com.yjkj.service_recoder.java.adapter.CarMenuAdapter;
import com.yjkj.service_recoder.java.adapter.MsAdapter;
import com.yjkj.service_recoder.java.base.BaseActivity;
import com.yjkj.service_recoder.java.bean.CarFoodListBean;
import com.yjkj.service_recoder.java.bean.CareringServiceData;
import com.yjkj.service_recoder.java.bean.FoodListBean;
import com.yjkj.service_recoder.java.bean.FoodListNewBean;
import com.yjkj.service_recoder.java.http.OkHttpUtil;
import com.yjkj.service_recoder.java.http.medicalservice.API;
import com.yjkj.service_recoder.java.ui.cateringServicesActivity.Order.OrderListActivity;
import com.yjkj.service_recoder.java.ui.cateringServicesActivity.Order.PayOrderActivity;
import com.yjkj.service_recoder.java.utils.Count;
import com.yjkj.service_recoder.java.utils.GlideUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EatCar1 extends BaseActivity<ActivityEatCar1Binding> {

    private List<CarFoodListBean> foodList = new ArrayList<>();
    private CarMenuAdapter adapter;


    public static Activity mActivity;

    private String type = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        super.initView();
        mActivity = this;

        type = getIntent().getStringExtra("msg");
        viewBinding.seeAll.setOnClickListener(v -> go(OrderListActivity.class));

        adapter = new CarMenuAdapter(foodList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        viewBinding.menuList.setLayoutManager(linearLayoutManager);

        viewBinding.menuList.setAdapter(adapter);

        viewBinding.catering.setOnClickListener(v -> {

            foodList.clear();
            viewBinding.totalMoney.setText("");
            go(CateringSelectView.class);


        });


        adapter.setOnItemClickListener((position, type) -> {
            CarFoodListBean carFoodListBean = foodList.get(position);
            if (type.equals("add")) {
                carFoodListBean.setNumber(carFoodListBean.getNumber() + 1);
                carFoodListBean.setPrice(Count.add(carFoodListBean.getPrice(), carFoodListBean.getSinglePrice()));
                adapter.notifyItemChanged(position);
            } else {
                if (carFoodListBean.getNumber() == 1) {
                    foodList.remove(position);
                    adapter.notifyItemChanged(position);
                    viewBinding.totalMoney.setText("0");
                    return;
                }
                carFoodListBean.setNumber(carFoodListBean.getNumber() - 1);
                carFoodListBean.setPrice(Count.sub(carFoodListBean.getPrice(), carFoodListBean.getSinglePrice()));
                adapter.notifyItemChanged(position);
            }
            double totalMoney = 0;
            for (CarFoodListBean bean : foodList
            ) {
                totalMoney = Count.add(totalMoney, bean.getPrice());
            }

            viewBinding.totalMoney.setText(totalMoney + "");
        });

    }
    Handler handler = new Handler(msg -> {

        String money = (String) msg.obj;
        viewBinding.ownerRemainMoney.setText("余额："+money);
        return false;
    });
    @Override
    public void onResume() {
        super.onResume();

        viewBinding.canteenName.setText(CareringServiceData.getInstance(EatCar1.this).getrFoodCanteenName());
        updateMoney(handler);

//        try {
//            getFoodList();
            getFoodListNew();
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void initData() {
        super.initData();



        viewBinding.submit.setOnClickListener(v -> {

            if(foodList.size()>0){
                Intent intent = new Intent(this, PayOrderActivity.class);
                intent.putExtra("foodList", (Serializable) foodList);
                intent.putExtra("totalMoney",viewBinding.totalMoney.getText().toString());
                startActivity(intent);


            }else {
                showToast("请点菜");
            }


        });


    }

    private void getFoodList() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rFoodCanteenId",CareringServiceData.getInstance(EatCar1.this).getrFoodCanteenId());
        jsonObject.put("rFoodCommunityOrPrivate","0");

        OkHttpUtil.getInstance().doPost(API.foodList(),jsonObject.toString(), new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                DecimalFormat df = new DecimalFormat("#.00");
                String json = Objects.requireNonNull(response.body()).string();

                FoodListBean foodListBean = new Gson().fromJson(json, FoodListBean.class);

                if (foodListBean.getCode() == 200) {
                    runOnUiThread(() -> {
                        MsAdapter msAdapter = new MsAdapter<FoodListBean.RowsDTO>(foodListBean.getRows(), R.layout.car_menu_item) {


                            @SuppressLint({"SetTextI18n", "NotifyDataSetChanged", "DefaultLocale"})
                            @Override
                            public void bindView(ViewHolder holder, FoodListBean.RowsDTO obj) {
                                ImageView imageView = holder.getView(R.id.image);
                                TextView foodName = holder.getView(R.id.foodName);
                                TextView rFoodPrice = holder.getView(R.id.rFoodPrice);

                                ImageView add = holder.getView(R.id.add);

                                GlideUtils.load(activity, obj.getRFoodPic(), imageView, R.drawable.good_test,20);
                                foodName.setText(obj.getRFoodName());
                                double foodPrice = obj.getRFoodPrice();
                                rFoodPrice.setText(String.format("%.2f",foodPrice));

                                add.setOnClickListener(v -> {

                                    if(obj.getRFoodIsorder()==1){
                                        showToast("售罄");
                                        return;
                                    }


                                    CarFoodListBean carFoodListBean = new CarFoodListBean();

                                    if (foodList.size() == 0) {
                                        carFoodListBean.setId(obj.getRFoodId());
                                        carFoodListBean.setImageUrl(obj.getRFoodPic());
                                        carFoodListBean.setName(obj.getRFoodName());
                                        carFoodListBean.setSinglePrice(obj.getRFoodPrice());
                                        carFoodListBean.setPrice(obj.getRFoodPrice());
                                        carFoodListBean.setPrice(obj.getRFoodPrice());
                                        carFoodListBean.setNumber(1);
                                        carFoodListBean.setrFoodCanteenId(obj.getrFoodCanteenId());
                                        carFoodListBean.setrFoodPackingCharge(obj.getrFoodPackingCharge());

                                        foodList.add(carFoodListBean);
                                    } else {

                                        if (haveIt(foodList, obj.getRFoodId())) {
                                            for (int i = 0; i < foodList.size(); i++) {
                                                CarFoodListBean carFood = foodList.get(i);
                                                if (carFood.getId() == obj.getRFoodId()) {
                                                    carFood.setNumber(carFood.getNumber() + 1);
                                                    carFood.setPrice(carFood.getPrice() + carFood.getSinglePrice());
                                                }
                                            }
                                        } else {
                                            carFoodListBean.setId(obj.getRFoodId());
                                            carFoodListBean.setImageUrl(obj.getRFoodPic());
                                            carFoodListBean.setName(obj.getRFoodName());
                                            carFoodListBean.setSinglePrice(obj.getRFoodPrice());
                                            carFoodListBean.setPrice(obj.getRFoodPrice());
                                            carFoodListBean.setNumber(1);
                                            carFoodListBean.setrFoodCanteenId(obj.getrFoodCanteenId());
                                            carFoodListBean.setrFoodPackingCharge(obj.getrFoodPackingCharge());

                                            foodList.add(carFoodListBean);
                                        }


                                    }

                                    adapter.notifyDataSetChanged();

                                    double totalMoney = 0;
                                    for (CarFoodListBean bean : foodList) {
                                        totalMoney = plus(String.valueOf(totalMoney), String.valueOf(bean.getPrice()));
                                    }
                                    viewBinding.totalMoney.setText(String.format("%.2f",totalMoney) + "");
                                });


                            }
                        };

                        viewBinding.menuGrid.setAdapter(msAdapter);

                    });
                } else {
                    runOnUiThread(() -> showToast("请求失败,请联系管理员"));
                }
            }
        });
    }

    public void getFoodListNew(){
        OkHttpUtil.getInstance().doGet(API.newFoodList() + "rFoodCanteenId=" + CareringServiceData.getInstance(EatCar1.this).getrFoodCanteenId()
                + "&rFoodCommunityOrPrivate=" + type, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                DecimalFormat df = new DecimalFormat("#.00");
                String json = Objects.requireNonNull(response.body()).string();

//                Log.i("getFoodListNew", json);

                FoodListNewBean foodListBean = new Gson().fromJson(json, FoodListNewBean.class);

                if (foodListBean.getCode() == 200) {
                    runOnUiThread(() -> {
                        MsAdapter msAdapter = new MsAdapter<FoodListNewBean.DataDTO>(foodListBean.getData(), R.layout.car_menu_item) {


                            @SuppressLint({"SetTextI18n", "NotifyDataSetChanged", "DefaultLocale"})
                            @Override
                            public void bindView(ViewHolder holder, FoodListNewBean.DataDTO obj) {
                                ImageView imageView = holder.getView(R.id.image);
                                TextView foodName = holder.getView(R.id.foodName);
                                TextView rFoodPrice = holder.getView(R.id.rFoodPrice);

                                ImageView add = holder.getView(R.id.add);

                                GlideUtils.load(activity, obj.getRFoodPic(), imageView, R.drawable.good_test,20);
                                foodName.setText(obj.getRFoodName());
                                double foodPrice = obj.getRFoodPrice();
                                rFoodPrice.setText(String.format("%.2f",foodPrice));

                                add.setOnClickListener(v -> {

                                    if(obj.getRFoodIsorder()==1){
                                        showToast("售罄");
                                        return;
                                    }


                                    CarFoodListBean carFoodListBean = new CarFoodListBean();

                                    if (foodList.size() == 0) {
                                        carFoodListBean.setId(obj.getRFoodId());
                                        carFoodListBean.setImageUrl(obj.getRFoodPic());
                                        carFoodListBean.setName(obj.getRFoodName());
                                        carFoodListBean.setSinglePrice(obj.getRFoodPrice());
                                        carFoodListBean.setPrice(obj.getRFoodPrice());
                                        carFoodListBean.setPrice(obj.getRFoodPrice());
                                        carFoodListBean.setNumber(1);
                                        carFoodListBean.setrFoodCanteenId(obj.getRFoodCanteenId() + "");
                                        carFoodListBean.setrFoodPackingCharge(obj.getRFoodPackingCharge());
                                        foodList.add(carFoodListBean);
                                    } else {

                                        if (haveIt(foodList, obj.getRFoodId())) {
                                            for (int i = 0; i < foodList.size(); i++) {
                                                CarFoodListBean carFood = foodList.get(i);
                                                if (carFood.getId() == obj.getRFoodId()) {
                                                    carFood.setNumber(carFood.getNumber() + 1);
                                                    carFood.setPrice(carFood.getPrice() + carFood.getSinglePrice());
                                                }
                                            }
                                        } else {
                                            carFoodListBean.setId(obj.getRFoodId());
                                            carFoodListBean.setImageUrl(obj.getRFoodPic());
                                            carFoodListBean.setName(obj.getRFoodName());
                                            carFoodListBean.setSinglePrice(obj.getRFoodPrice());
                                            carFoodListBean.setPrice(obj.getRFoodPrice());
                                            carFoodListBean.setNumber(1);
                                            carFoodListBean.setrFoodCanteenId(obj.getRFoodCanteenId() + "");
                                            carFoodListBean.setrFoodPackingCharge(obj.getRFoodPackingCharge());

                                            foodList.add(carFoodListBean);
                                        }


                                    }

                                    adapter.notifyDataSetChanged();

                                    double totalMoney = 0;
                                    for (CarFoodListBean bean : foodList) {
                                        totalMoney = plus(String.valueOf(totalMoney), String.valueOf(bean.getPrice()));
                                    }
                                    viewBinding.totalMoney.setText(String.format("%.2f",totalMoney) + "");
                                });


                            }
                        };

                        viewBinding.menuGrid.setAdapter(msAdapter);

                    });
                } else {
                    runOnUiThread(() -> showToast("请求失败,请联系管理员"));
                }
            }
        });
    }

    private Double plus(String value1, String value2) {
        BigDecimal num = new BigDecimal(value1);
        BigDecimal num2 = new BigDecimal(value2);

        BigDecimal result = num.add(num2);

        return Double.valueOf(result.toString());
    }

    public static boolean haveIt(List<CarFoodListBean> foodList, int targetValue) {
        for (CarFoodListBean s : foodList) {
            if (s.getId() == targetValue)
                return true;
        }
        return false;
    }



}