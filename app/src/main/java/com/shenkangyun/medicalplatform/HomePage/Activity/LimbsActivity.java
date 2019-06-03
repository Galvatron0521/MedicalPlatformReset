package com.shenkangyun.medicalplatform.HomePage.Activity;

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
import com.shenkangyun.medicalplatform.BeanFolder.HospitalBean;
import com.shenkangyun.medicalplatform.HomePage.Adapter.LimbsAdapter;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.GsonCallBack;
import com.shenkangyun.medicalplatform.UtilsFolder.RecyclerViewDivider;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LimbsActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.limbsRecycler)
    RecyclerView limbsRecycler;

    private LimbsAdapter limbsAdapter;
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
        setContentView(R.layout.activity_limbs);
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
        limbsAdapter = new LimbsAdapter();
        layoutManager = new LinearLayoutManager(this);
        limbsRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        limbsRecycler.setLayoutManager(layoutManager);
        limbsRecycler.setAdapter(limbsAdapter);
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
        listBeans.clear();
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "selectOrganizeListByType")
                .addParams("data", new selectOrganizeListByType(Base.getMD5Str(), Base.getTimeSpan(), "1",
                        "0", "10", "3").toJson())
                .build().execute(new GsonCallBack<HospitalBean>() {
            @Override
            public void onSuccess(HospitalBean response) {
                for (int i = 0; i < response.getData().getList().size(); i++) {
                    HospitalBean.DataBean.ListBean listBean = new HospitalBean.DataBean.ListBean();
                    name = response.getData().getList().get(i).getName();
                    String id = response.getData().getList().get(i).getId();
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
                HospitalBean.DataBean.ListBean listBean = new HospitalBean.DataBean.ListBean();
                listBean.setName("各街道卫生院");
                listBean.setContent("泰山区下辖的每个街道的卫生院");
                listBeans.add(listBean);
                limbsAdapter.setNewData(listBeans);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        limbsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.unitParent:
                        if (position == listBeans.size() - 1) {
                            Intent intentStreet = new Intent(LimbsActivity.this, StreetActivity.class);
                            startActivity(intentStreet);
                        } else {
                            Intent intentLimbs = new Intent(LimbsActivity.this, LimbsInfoActivity.class);
                            intentLimbs.putExtra("id", position);
                            intentLimbs.putExtra("name", listBeans.get(position).getName());
                            intentLimbs.putExtra("phone", listBeans.get(position).getPhone());
                            intentLimbs.putExtra("scope", listBeans.get(position).getScope());
                            intentLimbs.putExtra("subsidy", listBeans.get(position).getSubsidy());
                            intentLimbs.putExtra("xAxis", listBeans.get(position).getXAxis());
                            intentLimbs.putExtra("yAxis", listBeans.get(position).getYAxis());
                            intentLimbs.putExtra("contactsName", listBeans.get(position).getResponsibilityName());
                            startActivity(intentLimbs);
                        }
                        break;
                }
            }
        });
    }

    @OnClick(R.id.tv_submit)
    public void onViewClicked() {
        Intent intent = new Intent(this, LimsServiceActivity.class);
        startActivity(intent);
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
