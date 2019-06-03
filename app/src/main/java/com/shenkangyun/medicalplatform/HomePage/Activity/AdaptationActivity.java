package com.shenkangyun.medicalplatform.HomePage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shenkangyun.medicalplatform.DBFolder.canjiType;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.ToolPage.Activity.ToolsApplyActivity;
import com.shenkangyun.medicalplatform.ToolPage.Adapter.ToolApplyAdapter;
import com.shenkangyun.medicalplatform.ToolPage.ToolInfoFragment;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdaptationActivity extends AppCompatActivity {

    @BindView(R.id.tooLTab)
    TabLayout tooLTab;
    @BindView(R.id.toolFrame)
    FrameLayout toolFrame;
    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    private Bundle bundle;
    private FragmentManager fragmentManager;
    private List<canjiType> serviceProjects;
    private int typeDetailCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaptation);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("辅具页面");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initTab();
    }

    private void initView() {
        bundle = new Bundle();
        fragmentManager = getSupportFragmentManager();
        serviceProjects = LitePal.findAll(canjiType.class);
    }

    private void initTab() {
        for (int i = 0; i < serviceProjects.size(); i++) {
            tooLTab.addTab(tooLTab.newTab().setText(serviceProjects.get(i).getTypeDetailName()));
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ToolInfoFragment toolInfoFragment = new ToolInfoFragment();
        typeDetailCode = serviceProjects.get(0).getTypeDetailCode();
        bundle.putInt("typeDetailCode", typeDetailCode);
        toolInfoFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.toolFrame, toolInfoFragment);
        fragmentTransaction.commit();
        tooLTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ToolInfoFragment toolInfoFragment = new ToolInfoFragment();
                typeDetailCode = serviceProjects.get(tab.getPosition()).getTypeDetailCode();
                bundle.putInt("typeDetailCode", typeDetailCode);
                toolInfoFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.toolFrame, toolInfoFragment);
                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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

    @OnClick(R.id.tv_submit)
    public void onViewClicked() {
        Intent intent = new Intent(this, ToolsApplyActivity.class);
        startActivity(intent);
    }
}
