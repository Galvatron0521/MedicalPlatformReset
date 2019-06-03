package com.shenkangyun.medicalplatform.HomePage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BeanFolder.HouseBean;
import com.shenkangyun.medicalplatform.R;

/**
 * Created by Administrator on 2018/4/21.
 */

public class HouseAdapter extends BaseQuickAdapter<HouseBean, BaseViewHolder> {

    public HouseAdapter() {
        super(R.layout.item_house, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseBean item) {
        helper.setText(R.id.tv_name, item.getUnitName());
    }
}
