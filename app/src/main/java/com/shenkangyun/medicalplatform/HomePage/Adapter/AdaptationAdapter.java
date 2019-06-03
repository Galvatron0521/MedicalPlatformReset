package com.shenkangyun.medicalplatform.HomePage.Adapter;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BeanFolder.AdaptationBean;
import com.shenkangyun.medicalplatform.R;

/**
 * Created by Administrator on 2018/4/7.
 */

public class AdaptationAdapter extends BaseMultiItemQuickAdapter<AdaptationBean, BaseViewHolder> {


    public AdaptationAdapter() {
        super(null);
        addItemType(AdaptationBean.service, R.layout.item_service);
        addItemType(AdaptationBean.street, R.layout.item_adaptation);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdaptationBean item) {
        switch (helper.getItemViewType()) {
            case AdaptationBean.service:
                helper.setText(R.id.tv_unitName, item.getUnitName());
                helper.setText(R.id.tv_phone, item.getUnitPhone());
                helper.setText(R.id.tv_contacts, item.getUnitContacts());
                helper.addOnClickListener(R.id.tv_phone);
                helper.addOnClickListener(R.id.tv_position);
                break;
            case AdaptationBean.street:
                helper.setText(R.id.tv_unitName, item.getUnitName());
                helper.setText(R.id.tv_phone, item.getUnitPhone());
                helper.setText(R.id.tv_contacts, item.getUnitContacts());
                helper.addOnClickListener(R.id.tv_phone);
                break;
        }

    }
}
