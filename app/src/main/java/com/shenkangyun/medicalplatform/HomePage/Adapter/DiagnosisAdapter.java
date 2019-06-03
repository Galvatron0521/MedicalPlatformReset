package com.shenkangyun.medicalplatform.HomePage.Adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BeanFolder.DiagnosisBean;
import com.shenkangyun.medicalplatform.R;

/**
 * Created by Administrator on 2018/4/7.
 */

public class DiagnosisAdapter extends BaseQuickAdapter<DiagnosisBean, BaseViewHolder> {
    public DiagnosisAdapter() {
        super(R.layout.item_diagnosis, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiagnosisBean item) {
        helper.setText(R.id.tv_unitName, item.getUnitName());
        helper.setText(R.id.tv_contacts, item.getContacts());
        helper.setText(R.id.tv_phone, item.getPhoneNumber());
        helper.addOnClickListener(R.id.tv_phone);
    }
}
