package com.shenkangyun.medicalplatform.ToolPage.Adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BaseFolder.MyApp;
import com.shenkangyun.medicalplatform.BeanFolder.ApplyToolBean;
import com.shenkangyun.medicalplatform.R;

public class ToolApplyAdapter extends BaseQuickAdapter<ApplyToolBean.DataBean.ToolListBean, BaseViewHolder> {
    public ToolApplyAdapter() {
        super(R.layout.item_apply_tool, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplyToolBean.DataBean.ToolListBean item) {
        helper.setText(R.id.tool_name, item.getName());
        ImageView imageView = helper.getView(R.id.img_tool);
        Glide.with(MyApp.getContext()).load(item.getImageListURL()).into(imageView);
    }
}
