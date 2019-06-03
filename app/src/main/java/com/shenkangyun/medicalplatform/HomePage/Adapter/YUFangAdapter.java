package com.shenkangyun.medicalplatform.HomePage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BeanFolder.NewsBean;
import com.shenkangyun.medicalplatform.R;

public class YUFangAdapter extends BaseQuickAdapter<NewsBean.DataBean.NewslistBean, BaseViewHolder> {
    public YUFangAdapter() {
        super(R.layout.item_yufang, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.DataBean.NewslistBean item) {
        helper.setText(R.id.tv_unitName, item.getTitle());
        helper.addOnClickListener(R.id.tv_unitName);
    }
}
