package com.shenkangyun.medicalplatform.IMPage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shenkangyun.medicalplatform.BeanFolder.ChatContent;
import com.shenkangyun.medicalplatform.BeanFolder.MessageEvents;
import com.shenkangyun.medicalplatform.IMPage.adapter.ChatContentAdapter;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.RecyclerViewDivider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

public class JMessageActivity extends AppCompatActivity {


    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.chatRecycler)
    RecyclerView chatRecycler;
    @BindView(R.id.ed_message)
    EditText edMessage;
    @BindView(R.id.btn_send)
    Button btnSend;

    private String phone;
    private String messageTv;
    private String messageIn;

    private List<ChatContent> contents;
    private List<ChatContent> chatContents;
    private LinearLayoutManager layoutManager;
    private ChatContentAdapter chatContentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jmessage);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        EventBus.getDefault().register(this);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("在线聊天");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initState();
        initView();
    }

    private void initState() {
        ContactManager.sendInvitationRequest(phone, null, "", new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
                if (responseCode == 805002) {
                    btnSend.setClickable(true);
                } else if (responseCode == 0) {
                    btnSend.setClickable(false);
                    Toast.makeText(JMessageActivity.this, "已发送添加请求", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        Intent intent = getIntent();
        contents = new ArrayList<>();
        chatContents = new ArrayList<>();
        phone = intent.getStringExtra("phone");
        messageIn = intent.getStringExtra("message");
        layoutManager = new LinearLayoutManager(this);
        chatContentAdapter = new ChatContentAdapter();
        chatRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.ash)));
        chatRecycler.setAdapter(chatContentAdapter);
        chatRecycler.setLayoutManager(layoutManager);
        if (!TextUtils.isEmpty(messageIn)) {
            ChatContent chatContent = new ChatContent(2);
            chatContent.setContent(messageIn);
            contents.add(chatContent);
            chatContents.add(chatContent);
            chatContentAdapter.addData(chatContents);
            chatRecycler.scrollToPosition(contents.size() - 1);
        }
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        messageTv = edMessage.getText().toString();
        if (btnSend.isClickable()) {
            if (TextUtils.isEmpty(messageTv)) {
                Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
            } else {
                initChat();
            }
        } else {
            Toast.makeText(this, "请先添加好友后进行聊天", Toast.LENGTH_SHORT).show();
        }
    }


    private void initChat() {
        final Message message = JMessageClient.createSingleTextMessage(phone, messageTv);
        JMessageClient.sendMessage(message);
        message.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i == 0) {
                    edMessage.setText("");
                    chatContents.clear();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    // 隐藏软键盘
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                    ChatContent chatContent = new ChatContent(1);
                    chatContent.setContent(messageTv);
                    contents.add(chatContent);
                    chatContents.add(chatContent);
                    chatContentAdapter.addData(chatContents);
                    chatRecycler.scrollToPosition(contents.size() - 1);
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEvent(MessageEvents messageEvents) {
        chatContents.clear();
        String message = messageEvents.getMessage();
        ChatContent chatContent = new ChatContent(2);
        chatContent.setContent(message);
        contents.add(chatContent);
        chatContents.add(chatContent);
        chatContentAdapter.addData(chatContents);
        chatRecycler.scrollToPosition(contents.size() - 1);
    }


    public static void controlKeyboardLayout(final View root,
                                             final View editLayout) {
        // TODO Auto-generated method stub
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInVisibleHeigh = root.getRootView().getHeight() - rect.bottom;
                //若不可视区域高度大于100，则键盘显示
                if (rootInVisibleHeigh > 100) {
                    Log.v("hjb", "不可视区域高度大于100，则键盘显示");
                    int[] location = new int[2];
                    //获取editLayout在窗体的坐标
                    editLayout.getLocationInWindow(location);
                    //计算root滚动高度，使editLayout在可见区域
                    int srollHeight = (location[1] + editLayout.getHeight()) - rect.bottom;
                    root.scrollTo(0, srollHeight);
                } else {
                    //键盘隐藏
                    Log.v("hjb", "不可视区域高度小于100，则键盘隐藏");
                    root.scrollTo(0, 0);
                }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
