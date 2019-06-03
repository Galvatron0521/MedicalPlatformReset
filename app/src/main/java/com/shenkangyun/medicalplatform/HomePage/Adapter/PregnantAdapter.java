package com.shenkangyun.medicalplatform.HomePage.Adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BeanFolder.PregnantBean;
import com.shenkangyun.medicalplatform.R;

/**
 * Created by Administrator on 2018/4/1.
 */

public class PregnantAdapter extends BaseQuickAdapter<PregnantBean, BaseViewHolder> {
    public PregnantAdapter() {
        super(R.layout.item_pregnant, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, PregnantBean item) {
        helper.setText(R.id.tv_unitName, item.getUnitName());
        helper.setText(R.id.tv_contacts, item.getContacts());
        helper.setText(R.id.tv_phone, item.getPhoneNumber());
        helper.setText(R.id.tv_zhengCe, item.getZhengCe());
        helper.addOnClickListener(R.id.tv_phone);
        helper.addOnClickListener(R.id.tv_position);
    }
}
