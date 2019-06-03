package com.shenkangyun.medicalplatform.HomePage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shenkangyun.medicalplatform.BeanFolder.ChildrenBean;
import com.shenkangyun.medicalplatform.BeanFolder.VolunteerBean;
import com.shenkangyun.medicalplatform.HomePage.Adapter.ChildrenAdapter;
import com.shenkangyun.medicalplatform.HomePage.Adapter.VolunteerAdapter;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VolunteerActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.volunteerRecycler)
    RecyclerView volunteerRecycler;

    private List<VolunteerBean> beanList;
    private VolunteerAdapter volunteerAdapter;
    private LinearLayoutManager layoutManager;

    private String[] Names = new String[]{"家政服务", "心理咨询", "技能培训", "就业需求", "法律援助"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("志愿服务");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        beanList = new ArrayList<>();
        volunteerAdapter = new VolunteerAdapter();
        layoutManager = new LinearLayoutManager(this);
        volunteerRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        volunteerRecycler.setLayoutManager(layoutManager);
        volunteerRecycler.setAdapter(volunteerAdapter);
    }

    private void initData() {
        List<String> unitName = Arrays.asList(Names);
        for (int i = 0; i < 5; i++) {
            VolunteerBean volunteerBean = new VolunteerBean();
            volunteerBean.setUnitName(unitName.get(i));
            beanList.add(volunteerBean);
        }
        volunteerAdapter.setNewData(beanList);

        volunteerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    Intent intent = new Intent(VolunteerActivity.this, HousekeepingActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
