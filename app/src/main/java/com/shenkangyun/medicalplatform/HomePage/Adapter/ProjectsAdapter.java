package com.shenkangyun.medicalplatform.HomePage.Adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BeanFolder.ProjectsBean;
import com.shenkangyun.medicalplatform.R;

/**
 * Created by Administrator on 2018/3/31.
 */

public class ProjectsAdapter extends BaseQuickAdapter<ProjectsBean, BaseViewHolder> {

    public ProjectsAdapter() {
        super(R.layout.item_home, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectsBean item) {
        helper.setText(R.id.tv_projectName, item.getProjectName());
        helper.setBackgroundRes(R.id.img_project, item.getImg());
        helper.setBackgroundRes(R.id.projectContent, item.getColorId());
    }
}
