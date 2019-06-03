package com.shenkangyun.medicalplatform.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shenkangyun.medicalplatform.BaseFolder.Base;
import com.shenkangyun.medicalplatform.BeanFolder.AdBean;
import com.shenkangyun.medicalplatform.BeanFolder.ProjectsBean;
import com.shenkangyun.medicalplatform.BeanFolder.URLBean;
import com.shenkangyun.medicalplatform.CommunityPage.CommunityPageActivity;
import com.shenkangyun.medicalplatform.HomePage.Activity.AdaptationActivity;
import com.shenkangyun.medicalplatform.HomePage.Activity.ChildrenActivity;
import com.shenkangyun.medicalplatform.HomePage.Activity.FavouredPolicyActivity;
import com.shenkangyun.medicalplatform.HomePage.Activity.HearingActivity;
import com.shenkangyun.medicalplatform.HomePage.Activity.IntelligenceActivity;
import com.shenkangyun.medicalplatform.HomePage.Activity.LimbsActivity;
import com.shenkangyun.medicalplatform.HomePage.Activity.PreventionActivity;
import com.shenkangyun.medicalplatform.HomePage.Activity.SpeechActivity;
import com.shenkangyun.medicalplatform.HomePage.Activity.SpiritActivity;
import com.shenkangyun.medicalplatform.HomePage.Activity.VisionActivity;
import com.shenkangyun.medicalplatform.HomePage.Activity.VolunteerActivity;
import com.shenkangyun.medicalplatform.HomePage.Adapter.ProjectsAdapter;
import com.shenkangyun.medicalplatform.IMPage.AcceptFriendActivity;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.GlideImageLoader;
import com.shenkangyun.medicalplatform.UtilsFolder.GsonCallBack;
import com.shenkangyun.medicalplatform.UtilsFolder.RecyclerViewDivider;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.event.ContactNotifyEvent;
import cn.jpush.im.android.api.event.ConversationRefreshEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.MessageReceiptStatusChangeEvent;
import cn.jpush.im.android.api.event.MessageRetractEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by Administrator on 2018/3/30.
 */

public class HomePageFragment extends Fragment {
    @BindView(R.id.carousel)
    Banner carousel;
    @BindView(R.id.projectsRecycler)
    RecyclerView projectsRecycler;

    private URLBean urlBean;
    private String imgUrl;
    private Gson gson;
    private StringBuilder builder;
    private List<String> imgUrls;
    private List<ProjectsBean> projectsBeans;
    private ProjectsAdapter projectsAdapter;
    private LinearLayoutManager gridLayoutManager;

    private Integer[] colors = {R.color.home_red, R.color.home_red, R.color.home_red,
            R.color.home_yellow, R.color.home_yellow, R.color.home_yellow,
            R.color.home_green, R.color.home_green, R.color.home_green,
            R.color.home_red, R.color.home_red, R.color.home_red};

    private String[] names = {"0-11儿童", "视力", "听力", "肢体", "精神", "智力", "言语", "辅具适配", "残疾预防", "政策", "志愿服务","社区"};

    private Integer[] imgs = {R.drawable.childrens, R.drawable.eyes, R.drawable.ears,
            R.drawable.limbs, R.drawable.spirit, R.drawable.intelligence,
            R.drawable.speech, R.drawable.assistive, R.drawable.security,
            R.drawable.policy, R.drawable.volunteer, R.drawable.community_r};

    private String[] imageUrl = {"http://img2.imgtn.bdimg.com/it/u=3229240762,1116615332&fm=27&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3319937834,729386450&fm=27&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=249183895,3398871827&fm=27&gp=0.jpg"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        initView();
        initBanner();
        initProjects();
        return view;
    }

    private void initView() {
        gson = new Gson();
        imgUrls = new ArrayList<>();
        projectsBeans = new ArrayList<>();
        projectsAdapter = new ProjectsAdapter();
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        projectsRecycler.addItemDecoration(new RecyclerViewDivider(getActivity(), GridLayoutManager.VERTICAL,
                5, getContext().getResources().getColor(R.color.white)));
        projectsRecycler.addItemDecoration(new RecyclerViewDivider(getActivity(), GridLayoutManager.HORIZONTAL,
                5, getContext().getResources().getColor(R.color.white)));
        projectsRecycler.setLayoutManager(gridLayoutManager);
        projectsRecycler.setAdapter(projectsAdapter);
    }

    private void initBanner() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "adList")
                .addParams("data", new AdList(Base.getMD5Str(), Base.getTimeSpan()).toJson())
                .build().execute(new GsonCallBack<AdBean>() {
            @Override
            public void onSuccess(AdBean response) throws JSONException {
                for (int i = 0; i < response.getData().getAdlist().size(); i++) {
                    String picUrl = response.getData().getAdlist().get(i).getPicUrl();
                    urlBean = gson.fromJson(picUrl, URLBean.class);
                    for (int j = 0; j < urlBean.getJson().size(); j++) {
                        imgUrl = urlBean.getJson().get(j).getAttachmentUrl();
                        builder = new StringBuilder();
                        builder.append(Base.imageURLs).append(imgUrl);
                        imgUrls.add(builder.toString());
                    }
                }
                initImgData();
            }

            private void initImgData() {
                List<String> strings = Arrays.asList(imageUrl);
                carousel.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                carousel.setImageLoader(new GlideImageLoader());
                //设置图片集合
                if (imgUrls.size() == 0) {
                    carousel.setImages(strings);
                } else {
                    carousel.setImages(imgUrls);
                }
                //设置banner动画效果
                carousel.setBannerAnimation(Transformer.DepthPage);
                //设置自动轮播，默认为true
                carousel.isAutoPlay(true);
                //设置轮播时间
                carousel.setDelayTime(3000);
                //设置指示器位置（当banner模式中有指示器时）
                carousel.setIndicatorGravity(BannerConfig.CENTER);
                //banner设置方法全部调用完毕时最后调用
                carousel.start();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void initProjects() {
        List<Integer> proImg = Arrays.asList(imgs);
        List<String> proName = Arrays.asList(names);
        List<Integer> proColor = Arrays.asList(colors);
        for (int i = 0; i < proImg.size(); i++) {
            ProjectsBean projectsBean = new ProjectsBean();
            projectsBean.setImg(proImg.get(i));
            projectsBean.setColorId(proColor.get(i));
            projectsBean.setProjectName(proName.get(i));
            projectsBeans.add(projectsBean);
        }
        projectsAdapter.setNewData(projectsBeans);
        projectsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        Intent intentChildren = new Intent(getContext(), ChildrenActivity.class);
                        startActivity(intentChildren);
                        break;
                    case 1:
                        Intent intentVision = new Intent(getContext(), VisionActivity.class);
                        startActivity(intentVision);
                        break;
                    case 2:
                        Intent intentHearing = new Intent(getContext(), HearingActivity.class);
                        startActivity(intentHearing);
                        break;
                    case 3:
                        Intent intentLimbs = new Intent(getContext(), LimbsActivity.class);
                        startActivity(intentLimbs);
                        break;
                    case 4:
                        Intent intentSpirit = new Intent(getContext(), SpiritActivity.class);
                        startActivity(intentSpirit);
                        break;
                    case 5:
                        Intent intentIntel = new Intent(getContext(), IntelligenceActivity.class);
                        startActivity(intentIntel);
                        break;
                    case 6:
                        Intent intentSpeech = new Intent(getContext(), SpeechActivity.class);
                        startActivity(intentSpeech);
                        break;
                    case 7:
                        Intent intentAdaptation = new Intent(getContext(), AdaptationActivity.class);
                        startActivity(intentAdaptation);
                        break;
                    case 8:
                        Intent intentPre = new Intent(getContext(), PreventionActivity.class);
                        startActivity(intentPre);
                        break;
                    case 9:
                        Intent intentPolicy = new Intent(getContext(), FavouredPolicyActivity.class);
                        startActivity(intentPolicy);
                        break;
                    case 10:
                        Intent intentVolunteer = new Intent(getContext(), VolunteerActivity.class);
                        startActivity(intentVolunteer);
                        break;
                    case 11:
                        Intent intentComm = new Intent(getContext(), CommunityPageActivity.class);
                        startActivity(intentComm);
                        break;
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        carousel.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        carousel.stopAutoPlay();
    }

    static class AdList {
        private String appKey;
        private String timeSpan;

        public AdList(String appKey, String timeSpan) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
