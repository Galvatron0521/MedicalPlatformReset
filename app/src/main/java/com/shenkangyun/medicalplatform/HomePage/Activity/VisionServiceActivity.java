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
import com.shenkangyun.medicalplatform.ToolPage.Activity.FujuServiceActivity;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VisionServiceActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision_service);
        ButterKnife.bind(this);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("视力康复");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @OnClick({R.id.tv_zhushi, R.id.tv_mangzhang, R.id.tv_menling, R.id.tv_recovery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_zhushi:
                Intent intentZhu = new Intent(this, FujuServiceActivity.class);
                intentZhu.putExtra("toolID", "40288056696a3a7601696a3a762f0001");
                intentZhu.putExtra("toolName", "助视器");
                startActivity(intentZhu);
                break;
            case R.id.tv_mangzhang:
                Intent intentMang = new Intent(this, FujuServiceActivity.class);
                intentMang.putExtra("toolID", "40288056696a3a7601696a3a762f0000");
                intentMang.putExtra("toolName", "盲杖");
                startActivity(intentMang);
                break;
            case R.id.tv_menling:
                Intent intentMen = new Intent(this, FujuServiceActivity.class);
                intentMen.putExtra("toolID", "40288056696a3a7601696a3acef30002");
                intentMen.putExtra("toolName", "门铃");
                startActivity(intentMen);
                break;
            case R.id.tv_recovery:
                Intent intentLow = new Intent(this, LowVisionActivity.class);
                startActivity(intentLow);
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
