 package com.yjkj.service_recoder.java.adapter;

 import android.content.Context;
 import android.widget.TextView;

 import com.chad.library.adapter.base.BaseQuickAdapter;
 import com.chad.library.adapter.base.BaseViewHolder;
 import com.yjkj.service_recoder.R;
 import com.yjkj.service_recoder.java.bean.HlListBean;

/**
 * @description 其他房间
 * @author Lenovo
 * @time 2024/6/18
 */
public class OtherRoomAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    Context mcontext;

    public OtherRoomAdapter(Context context) {
        super(R.layout.item_other_room, null);
        this.mcontext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tv_value = helper.getView(R.id.tv_room);
        tv_value.setText(item);
    }

}
