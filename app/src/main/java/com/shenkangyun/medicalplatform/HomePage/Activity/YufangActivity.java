package com.shenkangyun.medicalplatform.HomePage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shenkangyun.medicalplatform.BaseFolder.Base;
import com.shenkangyun.medicalplatform.BeanFolder.NewsBean;
import com.shenkangyun.medicalplatform.HomePage.Adapter.YUFangAdapter;
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

public class YufangActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.newsRecycler)
    RecyclerView newsRecycler;
    @BindView(R.id.easyLayout)
    SwipeRefreshLayout easyLayout;

    private YUFangAdapter yuFangAdapter;
    private LinearLayoutManager layoutManager;

    private List<NewsBean.DataBean.NewslistBean> totalList = new ArrayList<>();

    private int pageNo = 0;
    private int pageCount = 10;

    private int size;
    private int id;
    private long createTime;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yufang);
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
        initInfo();
        initRefresh();
    }

    private void initView() {
        yuFangAdapter = new YUFangAdapter();
        layoutManager = new LinearLayoutManager(this);
        newsRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        newsRecycler.setLayoutManager(layoutManager);
        newsRecycler.setAdapter(yuFangAdapter);
    }

    private void initInfo() {
        pageNo = 0;
        pageCount = 10;
        totalList.clear();
        final List<NewsBean.DataBean.NewslistBean> newslistBeans = new ArrayList<>();
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "newsList")
                .addParams("data", new newsList(Base.getMD5Str(), Base.getTimeSpan(), String.valueOf(pageNo), String.valueOf(pageCount), "1").toJson())
                .build()
                .execute(new GsonCallBack<NewsBean>() {

                    @Override
                    public void onSuccess(NewsBean response) {
                        size = response.getData().getNewslist().size();
                        for (int i = 0; i < response.getData().getNewslist().size(); i++) {
                            NewsBean.DataBean.NewslistBean listBean = new NewsBean.DataBean.NewslistBean();
                            id = response.getData().getNewslist().get(i).getId();
                            title = response.getData().getNewslist().get(i).getTitle();
                            createTime = response.getData().getNewslist().get(i).getCreateTime();

                            listBean.setId(id);
                            listBean.setTitle(title);
                            listBean.setCreateTime(createTime);

                            newslistBeans.add(listBean);
                            totalList.add(listBean);
                        }
                        yuFangAdapter.setNewData(newslistBeans);
                        if (easyLayout.isRefreshing()) {
                            easyLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
        initLoadMore();
        initClick();
    }

    private void initLoadMore() {
        yuFangAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                newsRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<NewsBean.DataBean.NewslistBean> newslistBeans = new ArrayList<>();
                        if (!(size < pageCount)) {
                            pageNo = pageNo + size;
                            OkHttpUtils
                                    .post()
                                    .url(Base.URL)
                                    .addParams("act", "newsList")
                                    .addParams("data", new newsList(Base.getMD5Str(), Base.getTimeSpan(), String.valueOf(pageNo), String.valueOf(pageCount), "1").toJson())
                                    .build()
                                    .execute(new GsonCallBack<NewsBean>() {
                                        @Override
                                        public void onSuccess(final NewsBean response) {
                                            size = response.getData().getNewslist().size();
                                            for (int i = 0; i < response.getData().getNewslist().size(); i++) {
                                                NewsBean.DataBean.NewslistBean listBean = new NewsBean.DataBean.NewslistBean();
                                                id = response.getData().getNewslist().get(i).getId();
                                                title = response.getData().getNewslist().get(i).getTitle();
                                                createTime = response.getData().getNewslist().get(i).getCreateTime();

                                                listBean.setId(id);
                                                listBean.setTitle(title);
                                                listBean.setCreateTime(createTime);

                                                newslistBeans.add(listBean);
                                                totalList.add(listBean);
                                            }
                                            yuFangAdapter.addData(newslistBeans);
                                            yuFangAdapter.loadMoreComplete();
                                        }

                                        @Override
                                        public void onError(Exception e) {

                                        }
                                    });
                        } else {
                            yuFangAdapter.loadMoreEnd();
                        }
                    }
                }, 2000);

            }
        }, newsRecycler);
    }

    private void initRefresh() {
        easyLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initInfo();
            }
        });
    }

    private void initClick() {
        yuFangAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(YufangActivity.this, YufangInfoActivity.class);
                intent.putExtra("id", totalList.get(position).getId());
                intent.putExtra("title", totalList.get(position).getTitle());
                intent.putExtra("createTime", totalList.get(position).getCreateTime());
                startActivity(intent);
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

    static class newsList {

        private String appKey;
        private String timeSpan;
        private String pageNo;
        private String pageCount;
        private String mobileType;

        public newsList(String appKey, String timeSpan, String pageNo, String pageCount, String mobileType) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
            this.mobileType = mobileType;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
