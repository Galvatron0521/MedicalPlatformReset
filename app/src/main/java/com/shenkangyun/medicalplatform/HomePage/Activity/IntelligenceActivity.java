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
import com.google.gson.Gson;
import com.shenkangyun.medicalplatform.BaseFolder.Base;
import com.shenkangyun.medicalplatform.BeanFolder.HospitalBean;
import com.shenkangyun.medicalplatform.BeanFolder.IntelligenceBean;
import com.shenkangyun.medicalplatform.HomePage.Adapter.IntelligenceAdapter;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.GsonCallBack;
import com.shenkangyun.medicalplatform.UtilsFolder.RecyclerViewDivider;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntelligenceActivity extends AppCompatActivity {


    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.intelligenceRecycler)
    RecyclerView intelligenceRecycler;

    private IntelligenceAdapter intelligenceAdapter;
    private LinearLayoutManager layoutManager;
    private List<HospitalBean.DataBean.ListBean> listBeans;

    private String name;
    private String content;
    private String phone;
    private String scope;
    private String subsidy;
    private String xAxis;
    private String yAxis;
    private String contactsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intelligence);
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
        initHttp();
    }

    private void initView() {
        listBeans = new ArrayList<>();
        intelligenceAdapter = new IntelligenceAdapter();
        layoutManager = new LinearLayoutManager(this);
        intelligenceRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        intelligenceRecycler.setLayoutManager(layoutManager);
        intelligenceRecycler.setAdapter(intelligenceAdapter);
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

    private void initHttp() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "selectOrganizeListByType")
                .addParams("data", new selectOrganizeListByType(Base.getMD5Str(), Base.getTimeSpan(), "1",
                        "0", "10", "1").toJson())
                .build().execute(new GsonCallBack<HospitalBean>() {
            @Override
            public void onSuccess(HospitalBean response) {
                for (int i = 0; i < response.getData().getList().size(); i++) {
                    HospitalBean.DataBean.ListBean listBean = new HospitalBean.DataBean.ListBean();
                    name = response.getData().getList().get(i).getName();
                    content = response.getData().getList().get(i).getContent();
                    phone = response.getData().getList().get(i).getPhone();
                    scope = response.getData().getList().get(i).getScope();
                    subsidy = response.getData().getList().get(i).getSubsidy();
                    xAxis = response.getData().getList().get(i).getXAxis();
                    yAxis = response.getData().getList().get(i).getYAxis();
                    contactsName = response.getData().getList().get(i).getResponsibilityName();
                    listBean.setName(name);
                    listBean.setContent(content);
                    listBean.setPhone(phone);
                    listBean.setScope(scope);
                    listBean.setSubsidy(subsidy);
                    listBean.setXAxis(xAxis);
                    listBean.setYAxis(yAxis);
                    listBean.setResponsibilityName(contactsName);
                    listBeans.add(listBean);
                }
                intelligenceAdapter.setNewData(listBeans);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        intelligenceAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.unitParent:
                        Intent intent = new Intent(IntelligenceActivity.this, IntelligenceInfoActivity.class);
                        intent.putExtra("id", position);
                        intent.putExtra("name",listBeans.get(position).getName());
                        intent.putExtra("phone", listBeans.get(position).getPhone());
                        intent.putExtra("scope", listBeans.get(position).getScope());
                        intent.putExtra("subsidy", listBeans.get(position).getSubsidy());
                        intent.putExtra("xAxis", listBeans.get(position).getXAxis());
                        intent.putExtra("yAxis", listBeans.get(position).getYAxis());
                        intent.putExtra("contactsName", listBeans.get(position).getResponsibilityName());
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    static class selectOrganizeListByType {
        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String pageNo;
        private String pageCount;
        private String serviceprojectID;

        public selectOrganizeListByType(String appKey, String timeSpan, String mobileType, String pageNo, String pageCount, String serviceprojectID) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
            this.serviceprojectID = serviceprojectID;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
