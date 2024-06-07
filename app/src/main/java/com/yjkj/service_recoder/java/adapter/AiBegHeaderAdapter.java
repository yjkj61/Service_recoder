package com.yjkj.service_recoder.java.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.yjkj.service_recoder.R;
import com.yjkj.service_recoder.java.entity.SleepStatus;

import java.util.List;

public class AiBegHeaderAdapter extends RecyclerView.Adapter<AiBegHeaderAdapter.viewHolder>{

    List<SleepStatus> sleepStatuses ;


    public AiBegHeaderAdapter(List<SleepStatus> sleepStatuses) {
        this.sleepStatuses = sleepStatuses;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ai_beg_right_rec_item, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        SleepStatus status = sleepStatuses.get(position);

        if(position ==1 ){
            holder.show.setVisibility(View.VISIBLE);
        }

        holder.type.setText(status.getType());
        float progress = Float.parseFloat(status.getData());
        holder.progress.setCardBackgroundColor(Color.parseColor(status.getContext()));

        holder.box.post(() -> {
            int width = (int) (progress*holder.box.getMeasuredWidth());

            Log.d("TAG", "onBindViewHolder: "+width);

            holder.progress.setLayoutParams(new LinearLayout.LayoutParams(width,30));
        });

    }

    @Override
    public int getItemCount() {
        return sleepStatuses.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder{

        TextView type;
        MaterialCardView progress;

        ImageView show;

        LinearLayout box;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            progress = itemView.findViewById(R.id.progress);
            show = itemView.findViewById(R.id.show);
            box = itemView.findViewById(R.id.box);

        }
    }
}
