package com.shenkangyun.medicalplatform.HomePage.Adapter;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BeanFolder.ClinicBean;
import com.shenkangyun.medicalplatform.R;


/**
 * Created by Administrator on 2018/4/8.
 */

public class ClinicAdapter extends BaseQuickAdapter<ClinicBean.DataBean.ListBean, BaseViewHolder> {
    public ClinicAdapter() {
        super(R.layout.item_clinic, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClinicBean.DataBean.ListBean item) {
        TextView tvContent = helper.getView(R.id.tv_content);
        CharSequence charSequence = Html.fromHtml(item.getContent());
        tvContent.setText(charSequence);
        //该语句在设置后必加，不然没有任何效果
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        helper.setText(R.id.tv_unitName, item.getName());
        helper.setText(R.id.tv_phone, item.getPhone());
        helper.setText(R.id.tv_contacts, item.getResponsibilityName());
        helper.setText(R.id.tv_scope, item.getScope());
        helper.setText(R.id.tv_subsidy, item.getSubsidy());
        helper.addOnClickListener(R.id.tv_phone);
    }
}
