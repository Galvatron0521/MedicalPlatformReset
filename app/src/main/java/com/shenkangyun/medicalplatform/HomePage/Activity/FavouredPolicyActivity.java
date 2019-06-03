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
import com.shenkangyun.medicalplatform.BeanFolder.PolicyBean;
import com.shenkangyun.medicalplatform.HomePage.Adapter.PolicyAdapter;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.GsonCallBack;
import com.shenkangyun.medicalplatform.UtilsFolder.RecyclerViewDivider;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouredPolicyActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.favouredRecycler)
    RecyclerView favouredRecycler;

    private PolicyAdapter policyAdapter;
    private LinearLayoutManager layoutManager;
    private List<PolicyBean.DataBean.ListBean> listBeans;

    private String title;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoured_policy);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this,R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("政策列表");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        listBeans = new ArrayList<>();
        policyAdapter = new PolicyAdapter();
        layoutManager = new LinearLayoutManager(this);
        favouredRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        favouredRecycler.setLayoutManager(layoutManager);
        favouredRecycler.setAdapter(policyAdapter);
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "policyList")
                .addParams("data", new policyList(Base.getMD5Str(), Base.getTimeSpan(), "0", "8").toJson())
                .build().execute(new GsonCallBack<PolicyBean>() {
            @Override
            public void onSuccess(PolicyBean response) {
                for (int i = 0; i < response.getData().getList().size(); i++) {
                    PolicyBean.DataBean.ListBean listBean = new PolicyBean.DataBean.ListBean();
                    title = response.getData().getList().get(i).getTitle();
                    content = response.getData().getList().get(i).getContent();
                    listBean.setTitle(title);
                    listBean.setContent(content);
                    listBeans.add(listBean);
                }
                policyAdapter.setNewData(listBeans);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        initClick();
    }

    private void initClick() {
        policyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(FavouredPolicyActivity.this, FavouredInfoActivity.class);
                intent.putExtra("title", listBeans.get(position).getTitle());
                intent.putExtra("content", listBeans.get(position).getContent());
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

    static class policyList {

        private String appKey;
        private String timeSpan;
        private String pageNo;
        private String pageCount;

        public policyList(String appKey, String timeSpan, String pageNo, String pageCount) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
