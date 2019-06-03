package com.shenkangyun.medicalplatform.HomePage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BeanFolder.JinduBean;
import com.shenkangyun.medicalplatform.DBFolder.ToApplyDB;
import com.shenkangyun.medicalplatform.R;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JinduAdapter extends BaseQuickAdapter<JinduBean.DataBean.PatientListBean, BaseViewHolder> {

    public JinduAdapter() {
        super(R.layout.item_jindu, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, JinduBean.DataBean.PatientListBean item) {
        List<ToApplyDB> applyDBS = LitePal.findAll(ToApplyDB.class);
        for (int i = 0; i < applyDBS.size(); i++) {
            int typeDetailCode = applyDBS.get(i).getTypeDetailCode();
            if (item.getRecoveryType() == typeDetailCode) {
                String typeDetailName = applyDBS.get(i).getTypeDetailName();
                helper.setText(R.id.tv_recoveryType, typeDetailName);
                break;
            }
            if (item.getRecoveryType() ==0){
                helper.setText(R.id.tv_recoveryType, item.getToolName());
            }
        }

        Date date = new Date(item.getCreateTime());
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        String format = sd.format(date);
        helper.setText(R.id.tv_time, format);
    }
}