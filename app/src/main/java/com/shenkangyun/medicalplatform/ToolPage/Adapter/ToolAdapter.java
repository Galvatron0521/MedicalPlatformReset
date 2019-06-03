package com.shenkangyun.medicalplatform.ToolPage.Adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BaseFolder.MyApp;
import com.shenkangyun.medicalplatform.BeanFolder.ToolBean;
import com.shenkangyun.medicalplatform.R;

/**
 * Created by Administrator on 2018/3/8.
 */

public class ToolAdapter extends BaseQuickAdapter<ToolBean.DataBean.ToolListBean, BaseViewHolder> {
    public ToolAdapter() {
        super(R.layout.item_tool, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ToolBean.DataBean.ToolListBean item) {
        helper.setText(R.id.tool_name, item.getName());
        ImageView imageView = helper.getView(R.id.img_tool);
        Glide.with(MyApp.getContext()).load(item.getImageListURL()).into(imageView);
    }
}
