package com.shenkangyun.medicalplatform.HomePage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shenkangyun.medicalplatform.BaseFolder.Base;
import com.shenkangyun.medicalplatform.BeanFolder.NewInfoBean;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YufangInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.tv_zhiShi)
    TextView tvZhiShi;
    @BindView(R.id.tv_title)
    TextView tvTitle;


    private int id;
    private String title;
    private String createTime;
    private CharSequence charSequence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yufang_info);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("预防知识");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        title = intent.getStringExtra("title");
        createTime = intent.getStringExtra("createTime");
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "newsById")
                .addParams("data", new newsById(Base.getMD5Str(), Base.getTimeSpan(), "1", String.valueOf(id)).toJson())
                .build()
                .execute(new GsonCallBack<NewInfoBean>() {

                    @Override
                    public void onSuccess(NewInfoBean response) {
                        String title = response.getData().getNews().getTitle();
                        String content = response.getData().getNews().getContent();
                        tvTitle.setText(title);
                        charSequence = Html.fromHtml(content);
                        tvZhiShi.setText(charSequence);
                        //该语句在设置后必加，不然没有任何效果
                        tvZhiShi.setMovementMethod(LinkMovementMethod.getInstance());
                    }

                    @Override
                    public void onError(Exception e) {

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

    static class newsById {

        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String id;

        public newsById(String appKey, String timeSpan, String mobileType, String id) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.id = id;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
