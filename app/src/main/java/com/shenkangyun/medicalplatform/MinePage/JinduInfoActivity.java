package com.shenkangyun.medicalplatform.MinePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shenkangyun.medicalplatform.BeanFolder.ImageBean;
import com.shenkangyun.medicalplatform.BeanFolder.PicBean;
import com.shenkangyun.medicalplatform.BeanFolder.UpImgBean;
import com.shenkangyun.medicalplatform.DBFolder.StreetDB;
import com.shenkangyun.medicalplatform.DBFolder.ToApplyDB;
import com.shenkangyun.medicalplatform.DBFolder.User;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.GridDividerItemDecoration;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JinduInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.ed_name)
    TextView edName;
    @BindView(R.id.ed_idCard)
    TextView edIdCard;
    @BindView(R.id.tv_canjiType)
    TextView tvCanjiType;
    @BindView(R.id.tv_region)
    TextView tvRegion;
    @BindView(R.id.tv_town)
    TextView tvTown;
    @BindView(R.id.ed_address)
    TextView edAddress;
    @BindView(R.id.tv_tool)
    TextView tvTool;
    @BindView(R.id.tv_townStatus)
    TextView tvTownStatus;
    @BindView(R.id.tv_regionStatus)
    TextView tvRegionStatus;
    @BindView(R.id.mRecycler)
    RecyclerView mRecycler;

    private long createTime;
    private String address;
    private int applyType;
    private String picUrl;
    private String canjiTypeContent;
    private String organizeID;
    private String patientName;
    private String toolName;
    private int townstatus;
    private int regionstatus;
    private int recoveryType;
    private String idCard;
    private String town;

    private ImageAdapter imageAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<ImageBean> imageBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jindu_info);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("申请进度");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initIntent();
        initData();
    }

    private void initView() {
        imageAdapter = new ImageAdapter();
        gridLayoutManager = new GridLayoutManager(this, 3);
        mRecycler.setLayoutManager(gridLayoutManager);
        mRecycler.addItemDecoration(new GridDividerItemDecoration(this, 15, 5, true));
        mRecycler.setAdapter(imageAdapter);
    }

    private void initIntent() {
        Intent intent = getIntent();
        applyType = intent.getIntExtra("applyType", 0);
        address = intent.getStringExtra("address");
        canjiTypeContent = intent.getStringExtra("canjiTypeContent");
        organizeID = intent.getStringExtra("organizeID");
        recoveryType = intent.getIntExtra("recoveryType", 0);
        patientName = intent.getStringExtra("patientName");
        toolName = intent.getStringExtra("toolName");
        town = intent.getStringExtra("town");
        townstatus = intent.getIntExtra("townstatus", 0);
        regionstatus = intent.getIntExtra("regionstatus", 0);
        createTime = intent.getLongExtra("createTime", 0);
        picUrl = intent.getStringExtra("picUrl");
        Log.i("1234567", "initIntent: " + picUrl);

        List<User> all = LitePal.findAll(User.class);
        for (int i = 0; i < all.size(); i++) {
            idCard = all.get(i).getIdCard();
        }

//        Gson gson = new Gson();
//        if (!TextUtils.isEmpty(picUrl)) {
//            PicBean picBean = gson.fromJson(picUrl, PicBean.class);
//            for (int i = 0; i < picBean.getJson().size(); i++) {
//                String attachmentUrl = picBean.getJson().get(i).getAttachmentUrl();
//                ImageBean imageBean = new ImageBean();
//                imageBean.setImgURL(attachmentUrl);
//                imageBeans.add(imageBean);
//            }
//            imageAdapter.setNewData(imageBeans);
//        }
    }

    private void initData() {
        edName.setText(patientName);
        edIdCard.setText(idCard);
        edAddress.setText(address);
        tvCanjiType.setText(canjiTypeContent);
        List<StreetDB> streetDBS = LitePal.findAll(StreetDB.class);
        for (int i = 0; i < streetDBS.size(); i++) {
            if (town.equals(streetDBS.get(i).getPid())) {
                tvTown.setText(streetDBS.get(i).getStreetname());
                break;
            }
        }
        if (recoveryType == 0) {
            tvTool.setText(toolName);
        } else {
            List<ToApplyDB> toApplyDBS = LitePal.findAll(ToApplyDB.class);
            for (int i = 0; i < toApplyDBS.size(); i++) {
                if (toApplyDBS.get(i).getTypeDetailCode() == recoveryType) {
                    tvTool.setText(toApplyDBS.get(i).getTypeDetailName());
                    break;
                }
            }
        }

        switch (townstatus) {
            case 0:
                tvTownStatus.setText("未审批");
                break;
            case 1:
                tvTownStatus.setText("已通过");
                break;
            case 2:
                tvTownStatus.setText("已拒绝");
                break;
        }

        switch (regionstatus) {
            case 0:
                tvRegionStatus.setText("未审批");
                break;
            case 1:
                tvRegionStatus.setText("已通过");
                break;
            case 2:
                tvRegionStatus.setText("已拒绝");
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
