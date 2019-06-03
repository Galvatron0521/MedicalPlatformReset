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

public class HearAdultActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hear_adult);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("成人康复");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @OnClick({R.id.tv_zhuting, R.id.tv_shanguang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_zhuting:
                Intent intent = new Intent(this, FujuServiceActivity.class);
                intent.putExtra("toolID", "40288052626686e6016266a79fae0017");
                intent.putExtra("toolName", "助听器");
                startActivity(intent);
                break;
            case R.id.tv_shanguang:
                Intent intentShan = new Intent(this, FujuServiceActivity.class);
                intentShan.putExtra("toolID", "40288056696a1ff801696a1ff8e40000");
                intentShan.putExtra("toolName", "闪光门铃");
                startActivity(intentShan);
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
