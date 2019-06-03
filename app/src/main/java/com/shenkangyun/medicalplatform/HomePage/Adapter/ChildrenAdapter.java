package com.shenkangyun.medicalplatform.HomePage.Adapter;


import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BeanFolder.ChildrenBean;
import com.shenkangyun.medicalplatform.BeanFolder.HospitalBean;
import com.shenkangyun.medicalplatform.R;

/**
 * Created by Administrator on 2018/3/31.
 */

public class ChildrenAdapter extends BaseQuickAdapter<HospitalBean.DataBean.ListBean, BaseViewHolder> {

    public ChildrenAdapter() {
        super(R.layout.item_children, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, HospitalBean.DataBean.ListBean item) {
        TextView tvContent = helper.getView(R.id.tv_unitContent);
        CharSequence charSequence = Html.fromHtml(item.getContent());
        tvContent.setText(charSequence);
        //该语句在设置后必加，不然没有任何效果
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        helper.setText(R.id.tv_unitName, item.getName());
        helper.addOnClickListener(R.id.unitParent);
    }
}
