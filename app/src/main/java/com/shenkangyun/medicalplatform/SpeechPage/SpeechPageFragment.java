package com.shenkangyun.medicalplatform.SpeechPage;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shenkangyun.medicalplatform.BaseFolder.Base;
import com.shenkangyun.medicalplatform.BeanFolder.KeyWordBean;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.GsonCallBack;
import com.shenkangyun.medicalplatform.UtilsFolder.RecyclerViewDivider;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2018/3/30.
 */

public class SpeechPageFragment extends Fragment {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    @BindView(R.id.tv_voice)
    TextView tvVoice;
    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_net)
    TextView tvNet;
    @BindView(R.id.hospitalRecycler)
    RecyclerView hospitalRecycler;

    private String ed_Search;
    private KeyWordAdapter keyWordAdapter;
    private LinearLayoutManager layoutManager;
    private List<KeyWordBean.DataBean.ListBean> listBeans;

    private String name;
    private String message;
    private String scope;
    private String subsidy;
    private String phone;
    private String contacts;
    private int posCall;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speech, null);
        ButterKnife.bind(this, view);
        tvVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), IatDemo.class);
                startActivity(intent);
            }
        });
        initView();
        return view;
    }

    private void initView() {
        listBeans = new ArrayList<>();
        keyWordAdapter = new KeyWordAdapter();
        layoutManager = new LinearLayoutManager(getContext());
        hospitalRecycler.addItemDecoration(new RecyclerViewDivider(getContext(), LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        hospitalRecycler.setLayoutManager(layoutManager);
        hospitalRecycler.setAdapter(keyWordAdapter);
    }

    @OnClick({R.id.tv_search, R.id.tv_voice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                listBeans.clear();
                ed_Search = edSearch.getText().toString();
                if (TextUtils.isEmpty(ed_Search)) {
                    Toast.makeText(getContext(), "请输入关键字后搜索", Toast.LENGTH_SHORT).show();
                } else {
                    OkHttpUtils
                            .post()
                            .url(Base.URL)
                            .addParams("act", "selectKeyWordList")
                            .addParams("data", new selectKeyWordList(Base.getMD5Str(), Base.getTimeSpan(), "1", "0", "10", ed_Search).toJson())
                            .build()
                            .execute(new GsonCallBack<KeyWordBean>() {
                                @Override
                                public void onSuccess(KeyWordBean response) throws JSONException {
                                    if (response.getData().getBaiduMessage().size() == 0) {
                                        tvNet.setText("没有资料");
                                    } else {
                                        message = response.getData().getBaiduMessage().get(0);
                                        if (!TextUtils.isEmpty(message)) {
                                            tvNet.setText(message);
                                        }
                                    }
                                    if (response.getData().getList().size() != 0) {
                                        for (int i = 0; i < response.getData().getList().size(); i++) {
                                            KeyWordBean.DataBean.ListBean listBean = new KeyWordBean.DataBean.ListBean();
                                            name = response.getData().getList().get(i).getName();
                                            scope = response.getData().getList().get(i).getScope();
                                            subsidy = response.getData().getList().get(i).getSubsidy();
                                            phone = response.getData().getList().get(i).getPhone();
                                            contacts = response.getData().getList().get(i).getResponsibilityName();

                                            listBean.setName(name);
                                            listBean.setScope(scope);
                                            listBean.setPhone(phone);
                                            listBean.setSubsidy(subsidy);
                                            listBean.setResponsibilityName(contacts);
                                            listBeans.add(listBean);
                                        }
                                        keyWordAdapter.setNewData(listBeans);
                                    }
                                    keyWordAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                        @Override
                                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                            posCall = position;
                                            switch (view.getId()) {
                                                case R.id.tv_phone:
                                                    if (ContextCompat.checkSelfPermission(getActivity(),
                                                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                                        // 没有获得授权，申请授权
                                                        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                                                                Manifest.permission.CALL_PHONE)) {
                                                            Toast.makeText(getContext(), "请授权！", Toast.LENGTH_LONG).show();

                                                            // 帮跳转到该应用的设置界面，让用户手动授权
                                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                            Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                                                            intent.setData(uri);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                            startActivity(intent);
                                                        } else {
                                                            // 不需要解释为何需要该权限，直接请求授权
                                                            ActivityCompat.requestPermissions(getActivity(),
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

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                }
                break;
            case R.id.tv_voice:
                break;
        }
    }

    private void CallPhone() {
        String callPhone = listBeans.get(posCall).getPhone();
        if (TextUtils.isEmpty(callPhone)) {
            Toast.makeText(getContext(), "号码不能为空！", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getContext(), "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    static class selectKeyWordList {

        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String pageNo;
        private String pageCount;
        private String keyword;

        public selectKeyWordList(String appKey, String timeSpan, String mobileType, String pageNo,
                                 String pageCount, String keyword) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
            this.keyword = keyword;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
