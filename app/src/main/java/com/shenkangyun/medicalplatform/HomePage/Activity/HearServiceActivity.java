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
import com.shenkangyun.medicalplatform.ToolPage.Activity.ToolsApplyActivity;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HearServiceActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hear_service);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("听力康复");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @OnClick({R.id.tv_adult, R.id.tv_child})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_adult:
                Intent intentTool = new Intent(this, HearAdultActivity.class);
                startActivity(intentTool);
                break;
            case R.id.tv_child:
                Intent intentChild = new Intent(this, HearChildActivity.class);
                startActivity(intentChild);
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
