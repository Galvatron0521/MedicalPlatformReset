package com.shenkangyun.medicalplatform.HomePage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChildServiceActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_service);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("儿童康复");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @OnClick({R.id.tv_zhili, R.id.tv_naotan, R.id.tv_gudu, R.id.tv_tingli, R.id.tv_shili})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_zhili:
                Intent intentZhi = new Intent(this, ZhiliActivity.class);
                startActivity(intentZhi);
                break;
            case R.id.tv_naotan:
                Intent intentNao = new Intent(this, NaotanActivity.class);
                startActivity(intentNao);
                break;
            case R.id.tv_gudu:
                Intent intentGu = new Intent(this, GuduActivity.class);
                startActivity(intentGu);
                break;
            case R.id.tv_tingli:
                Intent intentTing = new Intent(this, TingliActivity.class);
                startActivity(intentTing);
                break;
            case R.id.tv_shili:
                Intent intentShi = new Intent(this, ShiliActivity.class);
                startActivity(intentShi);
                break;
        }
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
