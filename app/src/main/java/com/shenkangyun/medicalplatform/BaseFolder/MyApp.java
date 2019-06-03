package com.shenkangyun.medicalplatform.BaseFolder;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.LitePal;

import java.util.concurrent.TimeUnit;

import cn.jpush.im.android.api.JMessageClient;
import okhttp3.OkHttpClient;

import static cn.jpush.im.android.api.JMessageClient.FLAG_NOTIFY_SILENCE;

/**
 * Created by Administrator on 2018/2/22.
 */

public class MyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取全局Context
        context = getApplicationContext();
        //初始化LitePal
        LitePal.initialize(context);
        SDKInitializer.initialize(context);
        //初始化JMessage SDK
        JMessageClient.init(getApplicationContext(), true);
        JMessageClient.setNotificationFlag(FLAG_NOTIFY_SILENCE);
        // 将“12345678”替换成您申请的APPID，申请地址：http://www.xfyun.cn
        // 请勿在“=”与appid之间添加任何空字符或者转义符
        SpeechUtility.createUtility(context, SpeechConstant.APPID + "=5aec0da9");
        //初始化Http
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }


    public static Context getContext() {
        return context;
    }
}
