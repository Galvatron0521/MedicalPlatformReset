package com.shenkangyun.medicalplatform.MinePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shenkangyun.medicalplatform.BaseFolder.Base;
import com.shenkangyun.medicalplatform.BeanFolder.JinduBean;
import com.shenkangyun.medicalplatform.HomePage.Adapter.JinduAdapter;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.GsonCallBack;
import com.shenkangyun.medicalplatform.UtilsFolder.RecyclerViewDivider;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JinduActivity extends AppCompatActivity {


    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.jinDuRecycler)
    RecyclerView jinDuRecycler;

    private JinduAdapter jinduAdapter;
    private LinearLayoutManager layoutManager;

    private List<JinduBean.DataBean.PatientListBean> totalList = new ArrayList<>();
    private int id;
    private String town;
    private long createTime;
    private String address;
    private int applyType;
    private String picUrl;
    private String canjiTypeContent;
    private String organizeID;
    private String patientName;
    private String toolName;
    private int townstatus;
    private int regionstatus;
    private int recoveryType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jindu);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("申请进度");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initInfo();
    }

    private void initView() {
        jinduAdapter = new JinduAdapter();
        layoutManager = new LinearLayoutManager(this);
        jinDuRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        jinDuRecycler.setLayoutManager(layoutManager);
        jinDuRecycler.setAdapter(jinduAdapter);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
    }

    private void initInfo() {
        final List<JinduBean.DataBean.PatientListBean> newslistBeans = new ArrayList<>();
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "selectApplyPlan")
                .addParams("data", new selectApplyPlan(Base.getMD5Str(), Base.getTimeSpan(), "1", String.valueOf(id)).toJson())
                .build()
                .execute(new GsonCallBack<JinduBean>() {
                    @Override
                    public void onSuccess(JinduBean response) {
                        for (int i = 0; i < response.getData().getPatientList().size(); i++) {
                            JinduBean.DataBean.PatientListBean listBean = new JinduBean.DataBean.PatientListBean();
                            id = response.getData().getPatientList().get(i).getApplyType();
                            address = response.getData().getPatientList().get(i).getAddress();
                            recoveryType = response.getData().getPatientList().get(i).getRecoveryType();
                            canjiTypeContent = response.getData().getPatientList().get(i).getCanjiTypeContent();
                            organizeID = response.getData().getPatientList().get(i).getOrganizeID();
                            applyType = response.getData().getPatientList().get(i).getApplyType();
                            patientName = response.getData().getPatientList().get(i).getPatientName();
                            toolName = response.getData().getPatientList().get(i).getToolName();
                            town = response.getData().getPatientList().get(i).getTown();
                            townstatus = response.getData().getPatientList().get(i).getTownstatus();
                            regionstatus = response.getData().getPatientList().get(i).getRegionstatus();
                            createTime = response.getData().getPatientList().get(i).getCreateTime();
                            picUrl = response.getData().getPatientList().get(i).getPicUrl();

                            listBean.setApplyType(applyType);
                            listBean.setAddress(address);
                            listBean.setCanjiTypeContent(canjiTypeContent);
                            listBean.setOrganizeID(organizeID);
                            listBean.setPatientName(patientName);
                            listBean.setTownstatus(townstatus);
                            listBean.setToolName(toolName);
                            listBean.setTown(town);
                            listBean.setPicUrl(picUrl);
                            listBean.setRecoveryType(recoveryType);
                            listBean.setRegionstatus(regionstatus);
                            listBean.setCreateTime(createTime);

                            newslistBeans.add(listBean);
                            totalList.add(listBean);
                        }
                        jinduAdapter.setNewData(newslistBeans);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
        initClick();
    }

    private void initClick() {
        jinduAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(JinduActivity.this, JinduInfoActivity.class);
                intent.putExtra("applyType", totalList.get(position).getApplyType());
                intent.putExtra("address", totalList.get(position).getAddress());
                intent.putExtra("canjiTypeContent", totalList.get(position).getCanjiTypeContent());
                intent.putExtra("organizeID", totalList.get(position).getOrganizeID());
                intent.putExtra("patientName", totalList.get(position).getPatientName());
                intent.putExtra("toolName", totalList.get(position).getToolName());
                intent.putExtra("town", totalList.get(position).getTown());
                intent.putExtra("picUrl", totalList.get(position).getPicUrl());
                intent.putExtra("townstatus", totalList.get(position).getTownstatus());
                intent.putExtra("regionstatus", totalList.get(position).getRegionstatus());
                intent.putExtra("recoveryType", totalList.get(position).getRecoveryType());
                intent.putExtra("createTime", totalList.get(position).getCreateTime());
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

    static class selectApplyPlan {

        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String patientID;

        public selectApplyPlan(String appKey, String timeSpan, String mobileType, String patientID) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.patientID = patientID;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
