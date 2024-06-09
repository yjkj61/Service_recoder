package com.yjkj.service_recoder.java.ui.property.fragment.submitOrderFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yjkj.service_recoder.R;
import com.yjkj.service_recoder.databinding.FragmentSureOrderInfoBinding;
import com.yjkj.service_recoder.java.base.java.BaseFragment;
import com.yjkj.service_recoder.java.bean.BuyGoodBean;
import com.yjkj.service_recoder.java.bean.OrderGood;
import com.yjkj.service_recoder.java.http.OkHttpUtil;
import com.yjkj.service_recoder.java.http.medicalservice.API;
import com.yjkj.service_recoder.java.ui.SubmitOrderView;
import com.yjkj.service_recoder.java.utils.GetOrderForShop;
import com.yjkj.service_recoder.java.utils.GlideUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SureOrderInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SureOrderInfoFragment extends BaseFragment<FragmentSureOrderInfoBinding> {

    List<BuyGoodBean> buyGoodBeans;

    public static SureOrderInfoFragment newInstance() {
        SureOrderInfoFragment fragment = new SureOrderInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        List<OrderGood> goods = GetOrderForShop.getInstance().getGoods();

        buyGoodBeans = GetOrderForShop.getInstance().getBuyGoodBeans();

        AdapterOrderInfo adapterOrderInfo = new AdapterOrderInfo(goods);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        viewBinding.list.setLayoutManager(linearLayoutManager);
        viewBinding.list.setAdapter(adapterOrderInfo);

        viewBinding.submitBox.setOnClickListener(v -> {
            submit();
        });

        viewBinding.lastStep.setOnClickListener(v -> SubmitOrderView.mActivity.tabSwitch(0));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    private void submit() {
        OkHttpUtil.getInstance().doPost(API.order, new Gson().toJson(buyGoodBeans), new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                activity.runOnUiThread(() -> showToast("请求失败"));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {

                if (response.body() != null) {

                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getInt("code") == 200) {
                            showToast("下单成功");
                            //SubmitOrderView.mActivity.finish();
//                            activity.runOnUiThread(() -> SubmitOrderView.mActivity.tabSwitch(2));
                        } else {
                            activity.runOnUiThread(() -> showToast("请求失败，请联系管理员"));
                        }

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    class AdapterOrderInfo extends RecyclerView.Adapter<AdapterOrderInfo.ViewHolder> {


        private List<OrderGood> orderGoods;

        public AdapterOrderInfo(List<OrderGood> orderGoods) {
            this.orderGoods = orderGoods;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sure_order_info_item, parent, false);

            return new ViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            OrderGood orderGood = orderGoods.get(position);

            holder.goodName.setText(orderGood.getName());
            holder.number.setText(orderGood.getNumber()+"");
            holder.rule.setText(orderGood.getRule());
            holder.money.setText(orderGood.getMoney() * orderGood.number + "");
            GlideUtils.load(getContext(),orderGood.goodsCoverImages,holder.imageGoods);
            holder.reduce.setOnClickListener(v -> {
                if (orderGood.getNumber() == 1) {
                    return;
                }
                orderGood.setNumber(orderGood.getNumber() - 1);

                notifyItemChanged(position);
            });
            holder.plus.setOnClickListener(v -> {

                orderGood.setNumber(orderGood.getNumber() + 1);

                notifyItemChanged(position);
            });
        }

        @Override
        public int getItemCount() {
            return orderGoods.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private TextView goodName, number, rule, money;
            private ImageView reduce, plus, imageGoods;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                goodName = itemView.findViewById(R.id.name);
                number = itemView.findViewById(R.id.people_number);
                rule = itemView.findViewById(R.id.rule);
                money = itemView.findViewById(R.id.money);
                reduce = itemView.findViewById(R.id.reduce);
                plus = itemView.findViewById(R.id.plus);
                imageGoods = itemView.findViewById(R.id.imageGoods);
            }
        }
    }
}