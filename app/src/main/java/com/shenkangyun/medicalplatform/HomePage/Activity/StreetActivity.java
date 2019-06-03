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
import com.shenkangyun.medicalplatform.BeanFolder.StreetBean;
import com.shenkangyun.medicalplatform.HomePage.Adapter.StreetAdapter;
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

public class StreetActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.streetRecycler)
    RecyclerView streetRecycler;

    private StreetAdapter streetAdapter;
    private LinearLayoutManager layoutManager;
    private List<StreetBean.DataBean.ListBean> listBeans;

    private String id;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("街道列表");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        listBeans = new ArrayList<>();
        streetAdapter = new StreetAdapter();
        layoutManager = new LinearLayoutManager(this);
        streetRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        streetRecycler.setLayoutManager(layoutManager);
        streetRecycler.setAdapter(streetAdapter);
    }

    private void initData() {
        listBeans.clear();
        OkHttpUtils.post().url(Base.URL)
                .addParams("act", "selectCityByPid")
                .addParams("data", new selectCityByPid(Base.getMD5Str(), Base.getTimeSpan(), "370902", "1").toJson())
                .build().execute(new GsonCallBack<StreetBean>() {
            @Override
            public void onSuccess(StreetBean response) throws JSONException {
                for (int i = 0; i < response.getData().getList().size(); i++) {
                    StreetBean.DataBean.ListBean listBean = new StreetBean.DataBean.ListBean();
                    id = response.getData().getList().get(i).getId();
                    name = response.getData().getList().get(i).getName();

                    listBean.setId(id);
                    listBean.setName(name);

                    listBeans.add(listBean);
                }
                streetAdapter.setNewData(listBeans);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        initClick();
    }

    private void initClick() {
        streetAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(StreetActivity.this, ClinicActivity.class);
                intent.putExtra("id", listBeans.get(position).getId());
                intent.putExtra("name", listBeans.get(position).getName());
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

    static class selectCityByPid {

        private String appKey;
        private String timeSpan;
        private String pid;
        private String mobileType;

        public selectCityByPid(String appKey, String timeSpan, String pid, String mobileType) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.pid = pid;
            this.mobileType = mobileType;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
