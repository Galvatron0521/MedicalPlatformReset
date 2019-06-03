package com.shenkangyun.medicalplatform.HomePage.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shenkangyun.medicalplatform.BeanFolder.VolunteerBean;
import com.shenkangyun.medicalplatform.HomePage.Adapter.VolunteerAdapter;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuduActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.volunteerRecycler)
    RecyclerView volunteerRecycler;

    private List<String> unitName;
    private List<String> unitID;
    private List<VolunteerBean> beanList;
    private VolunteerAdapter volunteerAdapter;
    private LinearLayoutManager layoutManager;

    private String[] Names = new String[]{"泰山区小天使康复中心", "泰山区智康能力儿童活动中心", "泰山区东岳特教中心", "星童特教学校"};
    private String[] OrganizeID = new String[]{"2", "3", "4", "40288056695c751401695c7bd3010001"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gudu);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("机构列表");
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
        unitName = Arrays.asList(Names);
        unitID = Arrays.asList(OrganizeID);
        for (int i = 0; i < unitName.size(); i++) {
            VolunteerBean volunteerBean = new VolunteerBean();
            volunteerBean.setUnitName(unitName.get(i));
            beanList.add(volunteerBean);
        }
        volunteerAdapter.setNewData(beanList);

        volunteerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(GuduActivity.this, KangfuServiceActivity.class);
                intent.putExtra("unitID", unitID.get(position));
                intent.putExtra("unitName", unitName.get(position));
                intent.putExtra("recoveryType", "3");
                startActivity(intent);
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