package com.shenkangyun.medicalplatform.MinePage;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BaseFolder.MyApp;
import com.shenkangyun.medicalplatform.BeanFolder.ImageBean;
import com.shenkangyun.medicalplatform.R;

public class ImageAdapter extends BaseQuickAdapter<ImageBean, BaseViewHolder> {
    public ImageAdapter() {
        super(R.layout.item_adpater_for_specific_width_height, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageBean item) {
        ImageView view = helper.getView(R.id.scope_attachment_item_img);
        Glide.with(MyApp.getContext()).load(item.getImgURL()).into(view);
    }
}