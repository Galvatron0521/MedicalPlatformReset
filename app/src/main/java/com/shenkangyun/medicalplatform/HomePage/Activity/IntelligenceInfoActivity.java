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
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.shenkangyun.medicalplatform.IMPage.JMessageActivity;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntelligenceInfoActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.tv_scope)
    TextView tvScope;
    @BindView(R.id.tv_policy)
    TextView tvPolicy;
    @BindView(R.id.tv_contacts)
    TextView tvContacts;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    private int id;
    private String name;
    private String phone;
    private String scope;
    private String subsidy;
    private String xAxis;
    private String yAxis;
    private String contactsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intelligence_info);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("机构详情");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        scope = intent.getStringExtra("scope");
        subsidy = intent.getStringExtra("subsidy");
        xAxis = intent.getStringExtra("xAxis");
        yAxis = intent.getStringExtra("yAxis");
        contactsName = intent.getStringExtra("contactsName");
    }

    private void initData() {
        tvPhone.setText(phone);
        tvScope.setText(scope);
        tvPolicy.setText(subsidy);
        tvContacts.setText(contactsName);
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

    @OnClick({R.id.tv_phone, R.id.tv_position,R.id.tv_chat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_phone:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // 没有获得授权，申请授权
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CALL_PHONE)) {
                        Toast.makeText(this, "请授权！", Toast.LENGTH_LONG).show();

                        // 帮跳转到该应用的设置界面，让用户手动授权
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        // 不需要解释为何需要该权限，直接请求授权
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                } else {
                    // 已经获得授权，可以打电话
                    CallPhone();
                }
                break;
            case R.id.tv_position:
                double X = Double.parseDouble(xAxis);
                double Y = Double.parseDouble(yAxis);
                Intent intent = new Intent(this, PositionInfoActivity.class);
                intent.putExtra("X", X);
                intent.putExtra("Y", Y);
                intent.putExtra("name", name);
                startActivity(intent);
                break;
            case R.id.tv_chat:
                Intent intentIM = new Intent(this, JMessageActivity.class);
                intentIM.putExtra("phone", phone);
                startActivity(intentIM);
                break;
        }
    }

    private void CallPhone() {
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
}
