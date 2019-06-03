package com.shenkangyun.medicalplatform.HomePage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BeanFolder.VolunteerBean;
import com.shenkangyun.medicalplatform.R;

/**
 * Created by Administrator on 2018/4/20.
 */

public class VolunteerAdapter extends BaseQuickAdapter<VolunteerBean, BaseViewHolder> {

    public VolunteerAdapter() {
        super(R.layout.item_volunteer, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, VolunteerBean item) {
        helper.setText(R.id.tv_name, item.getUnitName());
    }
}
