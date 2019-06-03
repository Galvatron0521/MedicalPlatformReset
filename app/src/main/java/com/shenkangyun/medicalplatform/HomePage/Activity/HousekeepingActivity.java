package com.shenkangyun.medicalplatform.HomePage.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.shenkangyun.medicalplatform.BeanFolder.HouseBean;
import com.shenkangyun.medicalplatform.HomePage.Adapter.HouseAdapter;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HousekeepingActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.houseRecycler)
    RecyclerView houseRecycler;

    private List<HouseBean> houseBeans;
    private HouseAdapter houseAdapter;
    private LinearLayoutManager layoutManager;

    private String[] Names = new String[]{"打扫卫生", "洗衣做饭", "修理家电", "修缮房屋", "管道维修", "看病就医", "外出健身"
            , "护理保健", "采购需求", "社区服务", "缴费服务"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housekeeping);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("志愿服务");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        houseBeans = new ArrayList<>();
        houseAdapter = new HouseAdapter();
        layoutManager = new LinearLayoutManager(this);
        houseRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        houseRecycler.setLayoutManager(layoutManager);
        houseRecycler.setAdapter(houseAdapter);
    }

    private void initData() {
        List<String> unitName = Arrays.asList(Names);
        for (int i = 0; i < unitName.size(); i++) {
            HouseBean houseBean = new HouseBean();
            houseBean.setUnitName(unitName.get(i));
            houseBeans.add(houseBean);
        }
        houseAdapter.setNewData(houseBeans);
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
