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
import com.shenkangyun.medicalplatform.BeanFolder.DiagnosisBean;
import com.shenkangyun.medicalplatform.HomePage.Adapter.DiagnosisAdapter;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiagnosisActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.diagnosisRecycler)
    RecyclerView diagnosisRecycler;

    private DiagnosisAdapter diagnosisAdapter;
    private List<DiagnosisBean> diagnosisBeans;
    private LinearLayoutManager layoutManager;

    private String[] names = new String[]{"泰安市儿童医院", "泰山区妇幼保健院", "泰安市中心医院生殖门诊", "泰安市中心医院生殖门诊"};
    private String[] phone = new String[]{"15662069903", "13954895549", "13375388585", "13375387628"};
    private String[] contacts = new String[]{"陈业德", "于雪冬", "刘涛(主任)", "马为梅(护士长)"};

    private int viewPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("诊断机构");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        diagnosisBeans = new ArrayList<>();
        diagnosisAdapter = new DiagnosisAdapter();
        layoutManager = new LinearLayoutManager(this);
        diagnosisRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        diagnosisRecycler.setLayoutManager(layoutManager);
        diagnosisRecycler.setAdapter(diagnosisAdapter);
    }

    private void initData() {
        List<String> unitName = Arrays.asList(names);
        List<String> unitPhone = Arrays.asList(phone);
        List<String> unitContacts = Arrays.asList(contacts);
        for (int i = 0; i < 4; i++) {
            DiagnosisBean diagnosisBean = new DiagnosisBean();
            diagnosisBean.setUnitName(unitName.get(i));
            diagnosisBean.setContacts(unitContacts.get(i));
            diagnosisBean.setPhoneNumber(unitPhone.get(i));
            diagnosisBeans.add(diagnosisBean);
        }
        diagnosisAdapter.setNewData(diagnosisBeans);
        diagnosisAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_phone:
                        viewPosition = position;
                        if (ContextCompat.checkSelfPermission(DiagnosisActivity.this,
                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // 没有获得授权，申请授权
                            if (ActivityCompat.shouldShowRequestPermissionRationale(DiagnosisActivity.this,
                                    Manifest.permission.CALL_PHONE)) {
                                Toast.makeText(DiagnosisActivity.this, "请授权！", Toast.LENGTH_LONG).show();

                                // 帮跳转到该应用的设置界面，让用户手动授权
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                // 不需要解释为何需要该权限，直接请求授权
                                ActivityCompat.requestPermissions(DiagnosisActivity.this,
                                        new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                            }
                        } else {
                            // 已经获得授权，可以打电话
                            CallPhone();
                        }
                        break;
                }
            }
        });
    }

    private void CallPhone() {
        View textView = diagnosisRecycler.getLayoutManager().findViewByPosition(viewPosition);
        TextView tvPhone = textView.findViewById(R.id.tv_phone);
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
