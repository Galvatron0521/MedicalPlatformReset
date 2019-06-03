package com.shenkangyun.medicalplatform.IMPage.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.medicalplatform.BeanFolder.ChatContent;
import com.shenkangyun.medicalplatform.R;

/**
 * Created by Administrator on 2018/5/14.
 */

public class ChatContentAdapter extends BaseMultiItemQuickAdapter<ChatContent, BaseViewHolder> {
    public ChatContentAdapter() {
        super(null);
        addItemType(ChatContent.send, R.layout.item_send);
        addItemType(ChatContent.receive, R.layout.item_receive);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatContent item) {
        switch (helper.getItemViewType()) {
            case ChatContent.send:
                helper.setText(R.id.tv_message, item.getContent());
                break;
            case ChatContent.receive:
                helper.setText(R.id.tv_message, item.getContent());
                break;
        }

    }
}
