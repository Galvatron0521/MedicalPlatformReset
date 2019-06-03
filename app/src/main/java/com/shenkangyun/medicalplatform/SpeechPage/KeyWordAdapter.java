package com.shenkangyun.medicalplatform.SpeechPage;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BeanFolder.KeyWordBean;
import com.shenkangyun.medicalplatform.R;

/**
 * Created by Administrator on 2018/5/5.
 */

public class KeyWordAdapter extends BaseQuickAdapter<KeyWordBean.DataBean.ListBean, BaseViewHolder> {

    public KeyWordAdapter() {
        super(R.layout.item_keyword, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, KeyWordBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_title, item.getName());
        helper.setText(R.id.tv_scope, item.getScope());
        helper.setText(R.id.tv_policy, item.getSubsidy());
        helper.setText(R.id.tv_contacts, item.getResponsibilityName());
        helper.setText(R.id.tv_phone, item.getPhone());
        helper.addOnClickListener(R.id.tv_phone);
    }
}
