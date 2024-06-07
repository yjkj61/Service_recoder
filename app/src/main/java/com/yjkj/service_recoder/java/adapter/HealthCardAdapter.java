 package com.yjkj.service_recoder.java.adapter;

 import android.content.Context;
 import android.widget.LinearLayout;
 import android.widget.TextView;

 import com.chad.library.adapter.base.BaseQuickAdapter;
 import com.chad.library.adapter.base.BaseViewHolder;
 import com.yjkj.service_recoder.R;
 import com.yjkj.service_recoder.java.base.HealthBean;

 /**
 * @description 健康信息卡片
 * @author Lenovo
 * @time 2023/10/17
 */
public class HealthCardAdapter extends BaseQuickAdapter<HealthBean, BaseViewHolder> {

    Context mcontext;

    public HealthCardAdapter(Context context) {
        super(R.layout.item_health_card, null);
        this.mcontext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HealthBean item) {
        TextView tv_value = helper.getView(R.id.tv_value);
        TextView tv_title = helper.getView(R.id.tv_title);
        TextView tv_error = helper.getView(R.id.tv_error);
        LinearLayout linear_view = helper.getView(R.id.linear_view);

        linear_view.setBackgroundResource(item.getBg_res());
        tv_title.setText(item.getTitle());
        tv_value.setText(item.getValue());
    }

}
