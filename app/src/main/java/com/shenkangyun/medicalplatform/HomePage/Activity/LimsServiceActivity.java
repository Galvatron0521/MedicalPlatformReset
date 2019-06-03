package com.shenkangyun.medicalplatform.HomePage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.ToolPage.Activity.FujuServiceActivity;
import com.shenkangyun.medicalplatform.ToolPage.Activity.ToolsApplyActivity;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LimsServiceActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lims_service);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("肢体康复");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

    }

    @OnClick({R.id.tv_fuJu, R.id.tv_yiZhi, R.id.tv_kangFu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_fuJu:
                Intent intent = new Intent(this, ToolsApplyActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_yiZhi:
                Intent intentYI = new Intent(this, FujuServiceActivity.class);
                intentYI.putExtra("toolID", "40288056696a3a7601696a3acef306d3");
                intentYI.putExtra("toolName", "义肢");
                startActivity(intentYI);
                break;
            case R.id.tv_kangFu:
                Intent intentKang = new Intent(this, LimsRecoveryActivity.class);
                startActivity(intentKang);
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
