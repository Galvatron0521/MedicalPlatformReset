package com.shenkangyun.medicalplatform.HomePage.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shenkangyun.medicalplatform.BeanFolder.PregnantBean;
import com.shenkangyun.medicalplatform.HomePage.Adapter.PregnantAdapter;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PregnantActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.pregnantRecycler)
    RecyclerView pregnantRecycler;

    private PregnantAdapter pregnantAdapter;
    private LinearLayoutManager layoutManager;
    private List<PregnantBean> pregnantBeans;

    private String id;
    private List<Double> X;
    private List<Double> Y;
    private List<String> unitName;
    private Double[] yAxis = new Double[]{36.195675, 36.195675};
    private Double[] xAxis = new Double[]{117.156028, 117.156028};
    private String[] names = new String[]{"泰山区妇幼保健院儿保", "泰山区妇幼保健院妇保"};
    private String[] zhengCe1 = new String[]{"高危孕产妇每人每例500元。", "高危孕产妇每人每例500元。"};
    private String[] zhengCe2 = new String[]{"儿童筛查诊断每人每例300元。", "儿童筛查诊断每人每例300元。"};
    private String[] number = new String[]{"13854826361", "13954895549"};
    private String[] contacts = new String[]{"赵传威", "于雪冬"};

    private int viewPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregnant);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("机构列表");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        X = new ArrayList<>();
        Y = new ArrayList<>();
        unitName = new ArrayList<>();
        pregnantBeans = new ArrayList<>();
        pregnantAdapter = new PregnantAdapter();
        layoutManager = new LinearLayoutManager(this);
        pregnantRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        pregnantRecycler.setLayoutManager(layoutManager);
        pregnantRecycler.setAdapter(pregnantAdapter);
    }

    private void initData() {
        X = Arrays.asList(xAxis);
        Y = Arrays.asList(yAxis);
        unitName = Arrays.asList(names);
        List<String> phoneNumber = Arrays.asList(number);
        List<String> contactsPeople = Arrays.asList(contacts);
        List<String> stringList1 = Arrays.asList(zhengCe1);
        List<String> stringList2 = Arrays.asList(zhengCe2);
        for (int i = 0; i < 2; i++) {
            PregnantBean pregnantBean = new PregnantBean();
            pregnantBean.setUnitName(unitName.get(i));
            pregnantBean.setContacts(contactsPeople.get(i));
            pregnantBean.setPhoneNumber(phoneNumber.get(i));
            if (id.equals("1")){
                pregnantBean.setZhengCe(stringList1.get(i));
            }else {
                pregnantBean.setZhengCe(stringList2.get(i));
            }

            pregnantBeans.add(pregnantBean);
        }
        pregnantAdapter.setNewData(pregnantBeans);
        pregnantAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_phone:
                        viewPosition = position;
                        if (ContextCompat.checkSelfPermission(PregnantActivity.this,
                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // 没有获得授权，申请授权
                            if (ActivityCompat.shouldShowRequestPermissionRationale(PregnantActivity.this,
                                    Manifest.permission.CALL_PHONE)) {
                                Toast.makeText(PregnantActivity.this, "请授权！", Toast.LENGTH_LONG).show();

                                // 帮跳转到该应用的设置界面，让用户手动授权
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                // 不需要解释为何需要该权限，直接请求授权
                                ActivityCompat.requestPermissions(PregnantActivity.this,
                                        new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                            }
                        } else {
                            // 已经获得授权，可以打电话
                            CallPhone();
                        }
                        break;
                    case R.id.tv_position:
                        Intent intent = new Intent(PregnantActivity.this, PositionInfoActivity.class);
                        intent.putExtra("X", X.get(position));
                        intent.putExtra("Y", Y.get(position));
                        intent.putExtra("name", unitName.get(position));
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void CallPhone() {
        TextView tvPhone = pregnantRecycler.getChildAt(viewPosition).findViewById(R.id.tv_phone);
        String callPhone = tvPhone.getText().toString();
        if (TextUtils.isEmpty(callPhone)) {
            Toast.makeText(this, "号码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            // 拨号：激活系统的拨号组件
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + callPhone);
            intent.setData(data);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功，继续打电话
                    CallPhone();
                } else {
                    // 授权失败！
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
