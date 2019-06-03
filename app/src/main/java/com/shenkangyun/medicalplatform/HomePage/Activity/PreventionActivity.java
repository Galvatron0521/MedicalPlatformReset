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
import com.shenkangyun.medicalplatform.BeanFolder.PreventionBean;
import com.shenkangyun.medicalplatform.HomePage.Adapter.PreventionAdapter;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreventionActivity extends AppCompatActivity {


    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.preventionRecycler)
    RecyclerView preventionRecycler;

    private PreventionAdapter preventionAdapter;
    private LinearLayoutManager layoutManager;
    private List<PreventionBean> preventionBeans;

    private String[] names = new String[]{"高危孕产妇", "儿童筛查诊断", "残疾报告制度", "预防知识", "诊断机构"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevention);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("残疾预防");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        preventionBeans = new ArrayList<>();
        preventionAdapter = new PreventionAdapter();
        layoutManager = new LinearLayoutManager(this);
        preventionRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        preventionRecycler.setLayoutManager(layoutManager);
        preventionRecycler.setAdapter(preventionAdapter);
    }

    private void initData() {
        List<String> unitName = Arrays.asList(names);
        for (int i = 0; i < 5; i++) {
            PreventionBean preventionBean = new PreventionBean();
            preventionBean.setUnitName(unitName.get(i));
            preventionBeans.add(preventionBean);
        }
        preventionAdapter.setNewData(preventionBeans);

        preventionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        Intent intentPregnant = new Intent(PreventionActivity.this, PregnantActivity.class);
                        intentPregnant.putExtra("id", "1");
                        startActivity(intentPregnant);
                        break;
                    case 1:
                        Intent intentChildren = new Intent(PreventionActivity.this, PregnantActivity.class);
                        intentChildren.putExtra("id", "2");
                        startActivity(intentChildren);
                        break;
                    case 2:
                        Intent intentReport = new Intent(PreventionActivity.this, ReportActivity.class);
                        startActivity(intentReport);
                        break;
                    case 3:
                        Intent intentPolicy = new Intent(PreventionActivity.this, YufangActivity.class);
                        startActivity(intentPolicy);
                        break;
                    case 4:
                        Intent intentDiagnosis = new Intent(PreventionActivity.this, DiagnosisActivity.class);
                        startActivity(intentDiagnosis);
                        break;
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
