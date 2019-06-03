package com.shenkangyun.medicalplatform.HomePage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.shenkangyun.medicalplatform.BeanFolder.VolunteerBean;
import com.shenkangyun.medicalplatform.HomePage.Adapter.VolunteerAdapter;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LowVisionActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    private List<String> unitName;
    private List<String> unitID;

    private String[] Names = new String[]{"泰安市姜玉坤眼镜有限公司"};
    private String[] OrganizeID = new String[]{"7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_low_vision);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("低视力康复");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
    }

    private void initView() {
        unitName = Arrays.asList(Names);
        unitID = Arrays.asList(OrganizeID);
    }


    @OnClick(R.id.tv_lowVision)
    public void onViewClicked() {
        Intent intent = new Intent(this, KangfuServiceActivity.class);
        intent.putExtra("unitID", unitID.get(0));
        intent.putExtra("unitName", unitName.get(0));
        intent.putExtra("recoveryType", "9");
        startActivity(intent);
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
