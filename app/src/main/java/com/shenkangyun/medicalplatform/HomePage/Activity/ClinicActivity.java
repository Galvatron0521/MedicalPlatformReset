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
import com.google.gson.Gson;
import com.shenkangyun.medicalplatform.BaseFolder.Base;
import com.shenkangyun.medicalplatform.BeanFolder.ClinicBean;
import com.shenkangyun.medicalplatform.HomePage.Adapter.ClinicAdapter;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.GsonCallBack;
import com.shenkangyun.medicalplatform.UtilsFolder.RecyclerViewDivider;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClinicActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.clinicRecycler)
    RecyclerView clinicRecycler;

    private ClinicAdapter clinicAdapter;
    private LinearLayoutManager layoutManager;
    private List<ClinicBean.DataBean.ListBean> listBeans;

    private String name;
    private String phone;
    private String scope;
    private String subsidy;
    private String content;
    private String contactsName;

    private String id;
    private int viewPosition;
    private String streetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("街道机构");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        streetName = intent.getStringExtra("name");
        listBeans = new ArrayList<>();
        clinicAdapter = new ClinicAdapter();
        layoutManager = new LinearLayoutManager(this);
        clinicRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        clinicRecycler.setLayoutManager(layoutManager);
        clinicRecycler.setAdapter(clinicAdapter);
    }

    private void initData() {
        listBeans.clear();
        OkHttpUtils.post().url(Base.URL)
                .addParams("act", "selectOrganizeListByStreet")
                .addParams("data", new selectOrganizeListByStreet(Base.getMD5Str(), Base.getTimeSpan(), "1", id, "", "").toJson())
                .build().execute(new GsonCallBack<ClinicBean>() {
            @Override
            public void onSuccess(ClinicBean response) {
                for (int i = 0; i < response.getData().getList().size(); i++) {
                    ClinicBean.DataBean.ListBean listBean = new ClinicBean.DataBean.ListBean();
                    name = response.getData().getList().get(i).getName();
                    phone = response.getData().getList().get(i).getPhone();
                    scope = response.getData().getList().get(i).getScope();
                    subsidy = response.getData().getList().get(i).getSubsidy();
                    content = response.getData().getList().get(i).getContent();
                    contactsName = response.getData().getList().get(i).getResponsibilityName();

                    listBean.setName(name);
                    listBean.setPhone(phone);
                    listBean.setScope(scope);
                    listBean.setSubsidy(subsidy);
                    listBean.setContent(content);
                    listBean.setResponsibilityName(contactsName);

                    listBeans.add(listBean);
                }
                clinicAdapter.setNewData(listBeans);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        initClick();
    }

    private void initClick() {
        clinicAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                viewPosition = position;
                if (ContextCompat.checkSelfPermission(ClinicActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // 没有获得授权，申请授权
                    if (ActivityCompat.shouldShowRequestPermissionRationale(ClinicActivity.this,
                            Manifest.permission.CALL_PHONE)) {
                        Toast.makeText(ClinicActivity.this, "请授权！", Toast.LENGTH_LONG).show();

                        // 帮跳转到该应用的设置界面，让用户手动授权
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        // 不需要解释为何需要该权限，直接请求授权
                        ActivityCompat.requestPermissions(ClinicActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                } else {
                    // 已经获得授权，可以打电话
                    CallPhone();
                }
            }
        });
    }

    private void CallPhone() {
        View position = clinicRecycler.getLayoutManager().findViewByPosition(viewPosition);
        TextView tvPhone = position.findViewById(R.id.tv_phone);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
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

    static class selectOrganizeListByStreet {

        private String appKey;
        private String timeSpan;
        private String name;
        private String street;
        private String prevention;
        private String mobileType;

        public selectOrganizeListByStreet(String appKey, String timeSpan, String mobileType, String street, String prevention, String name) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.name = name;
            this.street = street;
            this.prevention = prevention;
            this.mobileType = mobileType;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
