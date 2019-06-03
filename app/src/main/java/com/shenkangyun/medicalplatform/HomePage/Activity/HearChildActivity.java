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

public class HearChildActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hear_child);
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

    @OnClick({R.id.tv_erWo, R.id.tv_yanYu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_erWo:
                Intent intentEr = new Intent(this,KangfuServiceActivity.class);
                intentEr.putExtra("unitID", "");
                intentEr.putExtra("unitName", "人工耳蜗");
                intentEr.putExtra("recoveryType", "6");
                startActivity(intentEr);
                break;
            case R.id.tv_yanYu:
                Intent intentYan = new Intent(this,KangfuServiceActivity.class);
                intentYan.putExtra("unitID", "402880526265f05b016265f950af0068");
                intentYan.putExtra("unitName", "泰安市康复中心");
                intentYan.putExtra("recoveryType", "7");
                startActivity(intentYan);
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
