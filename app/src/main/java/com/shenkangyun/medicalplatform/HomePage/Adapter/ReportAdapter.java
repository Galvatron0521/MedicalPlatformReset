package com.shenkangyun.medicalplatform.HomePage.Adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BeanFolder.ReportBean;
import com.shenkangyun.medicalplatform.R;

/**
 * Created by Administrator on 2018/4/1.
 */

public class ReportAdapter extends BaseQuickAdapter<ReportBean, BaseViewHolder> {
    public ReportAdapter() {
        super(R.layout.item_report, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReportBean item) {
        helper.setText(R.id.tv_unitName, item.getUnitName());
        helper.setText(R.id.tv_contacts, item.getContacts());
        helper.setText(R.id.tv_phone, item.getPhoneNumber());
        helper.addOnClickListener(R.id.tv_phone);
        helper.addOnClickListener(R.id.tv_position);
    }
}
