package com.shenkangyun.medicalplatform.HomePage.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shenkangyun.medicalplatform.BaseFolder.Base;
import com.shenkangyun.medicalplatform.BeanFolder.ApplyBean;
import com.shenkangyun.medicalplatform.BeanFolder.ApplyToolBean;
import com.shenkangyun.medicalplatform.BeanFolder.ToolImgBean;
import com.shenkangyun.medicalplatform.DBFolder.StreetDB;
import com.shenkangyun.medicalplatform.DBFolder.User;
import com.shenkangyun.medicalplatform.DBFolder.canjiType;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.ToolPage.Activity.ToolsApplyActivity;
import com.shenkangyun.medicalplatform.ToolPage.Adapter.ToolApplyAdapter;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MedicineActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_idCard)
    EditText edIdCard;
    @BindView(R.id.tv_canjiType)
    TextView tvCanjiType;
    @BindView(R.id.tv_region)
    TextView tvRegion;
    @BindView(R.id.tv_town)
    TextView tvTown;
    @BindView(R.id.ed_address)
    EditText edAddress;
    @BindView(R.id.tv_tool)
    TextView tvTool;

    private String name;
    private String idCard;
    private String patientID;
    private int CanjiPosition;
    private int StreetPosition;

    private ListPopupWindow mListType;
    private ListPopupWindow mListTown;
    private List<String> CanjiTypes = new ArrayList<>();
    private List<String> TypeNum = new ArrayList<>();
    private List<String> StreetName = new ArrayList<>();
    private List<String> StreetPID = new ArrayList<>();
    private List<ApplyToolBean.DataBean.ToolListBean> totalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("服药申请");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
        initPopupWindow();
    }

    private void initView() {
        List<User> users = LitePal.findAll(User.class);
        for (int i = 0; i < users.size(); i++) {
            name = users.get(i).getName();
            patientID = String.valueOf(users.get(i).getUserID());
            idCard = users.get(i).getIdCard();
        }
        edName.setText(name);
        edIdCard.setText(idCard);
        totalList = new ArrayList<>();
    }

    private void initData() {
        List<canjiType> types = LitePal.findAll(canjiType.class);
        for (int i = 0; i < types.size(); i++) {
            CanjiTypes.add(types.get(i).getTypeDetailName());
            TypeNum.add(String.valueOf(types.get(i).getTypeDetailCode()));
        }

        List<StreetDB> streetDBS = LitePal.findAll(StreetDB.class);
        for (int i = 0; i < streetDBS.size(); i++) {
            StreetPID.add(streetDBS.get(i).getPid());
            StreetName.add(streetDBS.get(i).getStreetname());
        }
    }

    private void initPopupWindow() {
        mListType = new ListPopupWindow(this);
        mListType.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, CanjiTypes));
        mListType.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mListType.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mListType.setAnchorView(tvCanjiType);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
        mListType.setModal(true);//设置是否是模式
        mListType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                tvCanjiType.setText(CanjiTypes.get(position));
                CanjiPosition = position;
                mListType.dismiss();
            }
        });

        mListTown = new ListPopupWindow(this);
        mListTown.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, StreetName));
        mListTown.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mListTown.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mListTown.setAnchorView(tvTown);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
        mListTown.setModal(true);//设置是否是模式
        mListTown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                tvTown.setText(StreetName.get(position));
                StreetPosition = position;
                mListTown.dismiss();
            }
        });
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

    @OnClick({R.id.tv_canjiType, R.id.tv_town, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_canjiType:
                mListType.show();
                break;
            case R.id.tv_town:
                mListTown.show();
                break;
            case R.id.tv_submit:
                SubmitInfo();
                break;
        }
    }

    private void SubmitInfo() {
        String CanjiTypeTx = tvCanjiType.getText().toString();
        String AddressED = edAddress.getText().toString();
        String ToolTx = tvTool.getText().toString();
        if (TextUtils.isEmpty(CanjiTypeTx)) {
            Toast.makeText(this, "请选择残疾类型", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(AddressED)) {
            Toast.makeText(this, "请填写详细地址", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(ToolTx) || ToolTx.equals("请点击工具选择")) {
            Toast.makeText(this, "请点击工具确定", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "toolApplyNew")
                .addParams("data", new toolApplyNew(Base.getMD5Str(), Base.getTimeSpan(), "1", "2",
                        "8", "", patientID, name, "", "370900", "370902", StreetPID.get(StreetPosition),
                        AddressED, TypeNum.get(CanjiPosition), CanjiTypeTx, "", "").toJson())
                .build()
                .execute(new GsonCallBack<ApplyBean>() {

                    @Override
                    public void onSuccess(ApplyBean response) {
                        String status = response.getStatus();
                        if ("0".equals(status)) {
                            Toast.makeText(MedicineActivity.this, response.getData().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    static class toolApplyNew {

        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String applyType;
        private String recoveryType;
        private String organizeID;
        private String patientID;
        private String patientName;
        private String picUrl;
        private String city;
        private String region;
        private String town;
        private String address;
        private String canjiTypeID;
        private String canjiTypeContent;
        private String toolID;
        private String toolName;

        public toolApplyNew(String appKey, String timeSpan, String mobileType, String applyType, String recoveryType,
                            String organizeID, String patientID, String patientName, String picUrl, String city, String region,
                            String town, String address, String canjiTypeID, String canjiTypeContent, String toolID, String toolName) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.applyType = applyType;
            this.recoveryType = recoveryType;
            this.organizeID = organizeID;
            this.patientID = patientID;
            this.patientName = patientName;
            this.picUrl = picUrl;
            this.city = city;
            this.region = region;
            this.town = town;
            this.address = address;
            this.canjiTypeID = canjiTypeID;
            this.canjiTypeContent = canjiTypeContent;
            this.toolID = toolID;
            this.toolName = toolName;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}