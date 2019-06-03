package com.shenkangyun.medicalplatform.MainPage;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shenkangyun.medicalplatform.BaseFolder.Base;
import com.shenkangyun.medicalplatform.BeanFolder.CanjiBean;
import com.shenkangyun.medicalplatform.BeanFolder.ToApplyBean;
import com.shenkangyun.medicalplatform.BeanFolder.TownBean;
import com.shenkangyun.medicalplatform.DBFolder.ServiceProject;
import com.shenkangyun.medicalplatform.DBFolder.StreetDB;
import com.shenkangyun.medicalplatform.DBFolder.ToApplyDB;
import com.shenkangyun.medicalplatform.DBFolder.canjiType;
import com.shenkangyun.medicalplatform.HomePage.HomePageFragment;
import com.shenkangyun.medicalplatform.IMPage.AcceptFriendActivity;
import com.shenkangyun.medicalplatform.IMPage.ChatRecordFragment;
import com.shenkangyun.medicalplatform.IMPage.JMessageActivity;
import com.shenkangyun.medicalplatform.LocationPage.LocationPageFragment;
import com.shenkangyun.medicalplatform.LoginPage.LoginActivity;
import com.shenkangyun.medicalplatform.MinePage.MinePageActivity;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.SpeechPage.SpeechPageFragment;
import com.shenkangyun.medicalplatform.ToolPage.Activity.ToolsApplyActivity;
import com.shenkangyun.medicalplatform.ToolPage.ToolPageFragment;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.GsonCallBack;
import com.shenkangyun.medicalplatform.UtilsFolder.NetworkChangeReceiver;
import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.LitePal;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.event.ContactNotifyEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_user)
    ImageView imgUser;
    @BindView(R.id.img_chat)
    ImageView imgChat;
    @BindView(R.id.tv_count)
    TextView tvCount;

    private String name;
    private int id;
    private String targetId;
    private String latestText;
    private IntentFilter intentFilter;
    private NetworkChangeReceiver changeReceiver;
    public static ArrayList<String> forAddFriend = new ArrayList<>();
    private FragmentManager fragmentManager;
    private Fragment HomeFragment, CalendarFragment, ChatRecordFragment, NearByFragment, ToolFragment, replaceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        ButterKnife.bind(this);
        intentFilter = new IntentFilter();
        changeReceiver = new NetworkChangeReceiver();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        JMessageClient.registerEventReceiver(this);
        registerReceiver(changeReceiver, intentFilter);
        initView();
        initFragment();
        android.os.Message message = new android.os.Message();
        message.arg1 = 1;
        handler.sendMessage(message);

    }

    public void onEventMainThread(ContactNotifyEvent event) {
        final String username = event.getFromUsername();
        final String appKey = event.getfromUserAppKey();
        if (event.getType() == ContactNotifyEvent.Type.invite_received) {
            //如果同一个人申请多次,则只会出现一次;当点击进验证消息界面后,同一个人再次申请则可以收到
            if (forAddFriend.size() > 0) {
                for (String forAdd : forAddFriend) {
                    if (forAdd.equals(username)) {
                        return;
                    } else {
                        forAddFriend.add(username);
                    }
                }
            } else {
                forAddFriend.add(username);
            }
            tvCount.setText(String.valueOf(forAddFriend.size()));
            JMessageClient.getUserInfo(username, appKey, new GetUserInfoCallback() {
                @Override
                public void gotResult(int status, String desc, UserInfo userInfo) {
                    if (status == 0) {
                        name = userInfo.getNickname();
                        if (TextUtils.isEmpty(name)) {
                            name = userInfo.getUserName();
                        }

                    }
                }
            });
        } else if (event.getType() == ContactNotifyEvent.Type.invite_declined) {
            Toast.makeText(this, "对方拒绝了您的好友请求", Toast.LENGTH_SHORT).show();
        }
    }

    public void onEventMainThread(MessageEvent event) {
        Message msg = event.getMessage();
        final UserInfo userInfo = (UserInfo) msg.getTargetInfo();
        targetId = userInfo.getUserName();
        Conversation conv = JMessageClient.getSingleConversation(targetId, userInfo.getAppKey());
        latestText = conv.getLatestText();
    }

    public void onEventMainThread(NotificationClickEvent event) {
        UserInfo fromUser = event.getMessage().getFromUser();
        Intent intent = new Intent(this, JMessageActivity.class);
        intent.putExtra("phone", fromUser.getUserName());
        intent.putExtra("message", latestText);
        startActivity(intent);
    }

    private void initView() {
        ToolFragment = new ToolPageFragment();
        HomeFragment = new HomePageFragment();
        NearByFragment = new LocationPageFragment();
        CalendarFragment = new SpeechPageFragment();
        ChatRecordFragment = new ChatRecordFragment();
        tvTitle.setText("首页");
        fragmentManager = getSupportFragmentManager();

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
    }

    private void initFragment() {
        FragmentTransaction Transaction = fragmentManager.beginTransaction();
        Transaction.add(R.id.layout_public, ChatRecordFragment).commit();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.layout_public, HomeFragment).commit();
        replaceFragment = HomeFragment;
    }

    @OnClick({R.id.btn_home, R.id.btn_community, R.id.btn_tool, R.id.btn_calendar, R.id.btn_nearby})
    public void onViewClicked(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.btn_tool:
                tvTitle.setText("机构");
                imgUser.setVisibility(View.GONE);
                replaceFragment(ToolFragment, fragmentTransaction);
                break;
            case R.id.btn_nearby:
                tvTitle.setText("附近");
                imgUser.setVisibility(View.GONE);
                replaceFragment(NearByFragment, fragmentTransaction);
                break;
            case R.id.btn_home:
                tvTitle.setText("首页");
                imgUser.setVisibility(View.VISIBLE);
                replaceFragment(HomeFragment, fragmentTransaction);
                break;
            case R.id.btn_community:
                tvTitle.setText("消息");
                imgUser.setVisibility(View.GONE);
                replaceFragment(ChatRecordFragment, fragmentTransaction);
                break;
            case R.id.btn_calendar:
                tvTitle.setText("搜索");
                imgUser.setVisibility(View.GONE);
                replaceFragment(CalendarFragment, fragmentTransaction);
                break;
        }
    }

    public void replaceFragment(Fragment showFragment, FragmentTransaction fragmentTransaction) {
        if (showFragment.isAdded()) {
            fragmentTransaction.hide(replaceFragment).show(showFragment).commit();
        } else {
            fragmentTransaction.hide(replaceFragment).add(R.id.layout_public, showFragment).commit();
        }
        replaceFragment = showFragment;
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(changeReceiver);
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == 5) {
            finish();
        }
        if (requestCode == 7 && resultCode == 6) {
            finish();
        }
    }

    @OnClick({R.id.img_user, R.id.img_chat})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.img_user:
                Intent intentMine = new Intent(this, MinePageActivity.class);
                startActivityForResult(intentMine, 7);
                break;
            case R.id.img_chat:
                Intent intent = new Intent(this, AcceptFriendActivity.class);
                intent.putExtra("names", forAddFriend);
                startActivity(intent);
                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.arg1 == 1) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LitePal.deleteAll(ToApplyDB.class);
                        OkHttpUtils.post()
                                .url(Base.URL)
                                .addParams("act", "toToolApply")
                                .addParams("data", new toToolApply(Base.getMD5Str(), Base.getTimeSpan(), "1", String.valueOf(id), "2").toJson())
                                .build()
                                .execute(new GsonCallBack<ToApplyBean>() {

                                    @Override
                                    public void onSuccess(ToApplyBean response) {
                                        for (int i = 0; i < response.getData().getRecoveryList().size(); i++) {
                                            int StreetId = response.getData().getRecoveryList().get(i).getTypeDetailCode();
                                            String StreetName = response.getData().getRecoveryList().get(i).getTypeDetailName();

                                            ToApplyDB toApplyDB = new ToApplyDB();
                                            toApplyDB.setTypeDetailCode(StreetId);
                                            toApplyDB.setTypeDetailName(StreetName);
                                            toApplyDB.save();
                                        }
                                    }

                                    @Override
                                    public void onError(Exception e) {

                                    }
                                });
                    }
                }).start();
            }
        }
    };

    static class toToolApply {

        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String patientID;
        private String applyType;

        public toToolApply(String appKey, String timeSpan, String mobileType, String patientID, String applyType) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.patientID = patientID;
            this.applyType = applyType;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
