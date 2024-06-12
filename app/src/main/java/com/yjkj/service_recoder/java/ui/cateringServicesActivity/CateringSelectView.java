package com.yjkj.service_recoder.java.ui.cateringServicesActivity;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yjkj.service_recoder.R;
import com.yjkj.service_recoder.databinding.ActivityCateringSelectViewBinding;
import com.yjkj.service_recoder.java.base.BaseActivity;
import com.yjkj.service_recoder.java.bean.CanteenBean;
import com.yjkj.service_recoder.java.bean.CareringServiceData;
import com.yjkj.service_recoder.java.http.OkHttpUtil;
import com.yjkj.service_recoder.java.http.medicalservice.API;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CateringSelectView extends BaseActivity<ActivityCateringSelectViewBinding> {



    @Override
    public void initView() {
        super.initView();
        viewBinding.back.setOnClickListener(v -> finish());
    }

    @Override
    public void initData() {
        super.initData();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        viewBinding.cateringList.setLayoutManager(linearLayoutManager);

        OkHttpUtil.getInstance().doGet(API.canteenList, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.body() != null) {
                    CanteenBean canteenBean = new Gson().fromJson(response.body().string(), CanteenBean.class);

                  if(canteenBean.getCode() == 200){

                      runOnUiThread(() -> {
                          AdapterCantering adapterCantering = new AdapterCantering(canteenBean.getRows());
                          viewBinding.cateringList.setAdapter(adapterCantering);
                      });
                  }
                }
            }
        });
    }


    class AdapterCantering extends RecyclerView.Adapter<AdapterCantering.ViewHolder>{

        private List<CanteenBean.RowsDTO> rowsDTOS;

        public AdapterCantering(List<CanteenBean.RowsDTO> rowsDTOS) {
            this.rowsDTOS = rowsDTOS;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_type_list_item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

            holder.name.setText(rowsDTOS.get(position).getRFoodCanteenName());

            holder.name.setOnClickListener(v -> {
                CareringServiceData.getInstance(CateringSelectView.this).setMarkName(rowsDTOS.get(position).getMarkerName());
                CareringServiceData.getInstance(CateringSelectView.this).setMarkId(String.valueOf(rowsDTOS.get(position).getMarkerId()));
                CareringServiceData.getInstance(CateringSelectView.this).setrFoodCanteenId(String.valueOf(rowsDTOS.get(position).getRFoodCanteenId()));
                CareringServiceData.getInstance(CateringSelectView.this).setrFoodCanteenName(rowsDTOS.get(position).getRFoodCanteenName());
                activity.finish();
            });
        }

        @Override
        public int getItemCount() {
            return rowsDTOS.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            private TextView name;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name);
            }
        }
    }
}