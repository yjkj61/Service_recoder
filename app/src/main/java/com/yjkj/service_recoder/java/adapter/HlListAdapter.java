 package com.yjkj.service_recoder.java.adapter;

 import static com.blankj.utilcode.util.ActivityUtils.startActivity;

 import android.content.Context;
 import android.content.Intent;
 import android.net.Uri;
 import android.view.View;
 import android.widget.ImageView;
 import android.widget.TextView;

 import com.chad.library.adapter.base.BaseQuickAdapter;
 import com.chad.library.adapter.base.BaseViewHolder;
 import com.yjkj.service_recoder.R;
 import com.yjkj.service_recoder.java.bean.HlListBean;

 /**
 * @description 安防
 * @author Lenovo
 * @time 2023/10/17
 */
public class HlListAdapter extends BaseQuickAdapter<HlListBean, BaseViewHolder> {

    Context mcontext;

    public HlListAdapter(Context context) {
        super(R.layout.item_hl_list, null);
        this.mcontext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HlListBean item) {
        TextView tv_start_time = helper.getView(R.id.tv_start_time);
        TextView tv_end_time = helper.getView(R.id.tv_end_time);
        TextView tv_type = helper.getView(R.id.tv_type);
        TextView tv_name = helper.getView(R.id.tv_name);
        ImageView ig_pic = helper.getView(R.id.ig_pic);

        tv_type.setText(item.getProjectName());
        tv_start_time.setText("服务开始时间：" + item.getBookStartTime());
        tv_end_time.setText("服务结束时间：" + item.getBookFinishTime());
        tv_name.setText("服务人员：" + item.getWorkerName());

        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + item.getWorkerPhone()));
                startActivity(intent);
            }
        });
    }

}
