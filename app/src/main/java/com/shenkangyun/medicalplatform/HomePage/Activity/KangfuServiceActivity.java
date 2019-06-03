package com.shenkangyun.medicalplatform.HomePage.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.shenkangyun.medicalplatform.BaseFolder.Base;
import com.shenkangyun.medicalplatform.BeanFolder.ApplyBean;
import com.shenkangyun.medicalplatform.BeanFolder.ApplyToolBean;
import com.shenkangyun.medicalplatform.BeanFolder.UpImgBean;
import com.shenkangyun.medicalplatform.DBFolder.StreetDB;
import com.shenkangyun.medicalplatform.DBFolder.User;
import com.shenkangyun.medicalplatform.DBFolder.canjiType;
import com.shenkangyun.medicalplatform.HomePage.Adapter.GridImageAdapter;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FullyGridLayoutManager;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.GsonCallBack;
import com.shenkangyun.medicalplatform.UtilsFolder.HcUtils;
import com.shenkangyun.medicalplatform.UtilsFolder.OkHttpUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.LitePal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class KangfuServiceActivity extends AppCompatActivity {

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
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    private String unitID;
    private String unitName;
    private String recoveryType;

    private String name;
    private String idCard;
    private String patientID;
    private String CanjiType;
    private int CanjiPosition;
    private int StreetPosition;
    private ListPopupWindow mListType;
    private ListPopupWindow mListTown;
    private List<String> CanjiTypes = new ArrayList<>();
    private List<String> TypeNum = new ArrayList<>();
    private List<String> StreetName = new ArrayList<>();
    private List<String> StreetPID = new ArrayList<>();
    private List<ApplyToolBean.DataBean.ToolListBean> totalList;

    private int maxSelectNum = 9;
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<HashMap<String, String>> filter = new ArrayList<>();
    private HashMap<String, List<HashMap<String, String>>> listHashMap = new HashMap<>();
    private GridImageAdapter adapter;
    private PopupWindow pop;

    private int fileId;
    private int fileType;
    private String fileUrl;
    private int count;
    private String sPic = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kangfu_service);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("康复需求申请");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
        initPopupWindow();
        initWidget();
    }

    private void initView() {
        List<User> users = LitePal.findAll(User.class);
        for (int i = 0; i < users.size(); i++) {
            name = users.get(i).getName();
            patientID = String.valueOf(users.get(i).getUserID());
            idCard = users.get(i).getIdCard();
            CanjiType = users.get(i).getCanjiType();
        }
        edName.setText(name);
        edIdCard.setText(idCard);
        totalList = new ArrayList<>();
    }

    private void initData() {
        Intent intent = getIntent();
        unitID = intent.getStringExtra("unitID");
        unitName = intent.getStringExtra("unitName");
        recoveryType = intent.getStringExtra("recoveryType");
        tvTool.setText(unitName);

        List<canjiType> types = LitePal.findAll(canjiType.class);
        for (int i = 0; i < types.size(); i++) {
            CanjiTypes.add(types.get(i).getTypeDetailName());
            TypeNum.add(String.valueOf(types.get(i).getTypeDetailCode()));
            if (CanjiType.equals(types.get(i).getTypeDetailCode())) {
                tvCanjiType.setText(types.get(i).getTypeDetailName());
            }
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

        if (TextUtils.isEmpty(sPic)) {
            Toast.makeText(this, "请选择证明资料上传", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "toolApplyNew")
                .addParams("data", new toolApplyNew(Base.getMD5Str(), Base.getTimeSpan(), "1", "2", recoveryType, unitID,
                        patientID, name, sPic, "370900", "370902", StreetPID.get(StreetPosition), AddressED, TypeNum.get(CanjiPosition),
                        CanjiTypeTx, "", "").toJson())
                .build()
                .execute(new GsonCallBack<ApplyBean>() {

                    @Override
                    public void onSuccess(ApplyBean response) {
                        String status = response.getStatus();
                        if ("0".equals(status)) {
                            Toast.makeText(KangfuServiceActivity.this, response.getData().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    private void initWidget() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(KangfuServiceActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(KangfuServiceActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(KangfuServiceActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {

        @Override
        public void onAddPicClick() {

            //第一种方式，弹出选择和拍照的dialog
            showPop();
        }
    };

    private void showPop() {
        View bottomView = View.inflate(KangfuServiceActivity.this, R.layout.layout_bottom_dialog, null);
        TextView mAlbum = bottomView.findViewById(R.id.tv_album);
        TextView mCamera = bottomView.findViewById(R.id.tv_camera);
        TextView mCancel = bottomView.findViewById(R.id.tv_cancel);

        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        mAlbum.setOnClickListener(clickListener);
        mCamera.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_album:
                    //相册
                    PictureSelector.create(KangfuServiceActivity.this)
                            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                            .maxSelectNum(8)// 最大图片选择数量 int
                            .minSelectNum(1)// 最小选择数量 int
                            .imageSpanCount(4)// 每行显示个数 int
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .previewImage(true)// 是否可预览图片 true or false
                            .isCamera(true)// 是否显示拍照按钮 true or false
                            .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                            .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                            .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                            .enableCrop(false)// 是否裁剪 true or false
                            .compress(true)// 是否压缩 true or false
                            .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示 true or false
                            .isGif(false)// 是否显示gif图片 true or false
                            .compressSavePath("")//压缩图片保存地址
                            .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> list
                            .minimumCompressSize(100)// 小于100kb的图片不压缩
                            .synOrAsy(true)//同步true或异步false 压缩 默认同步
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                    break;
                case R.id.tv_camera:
                    //拍照
                    PictureSelector
                            .create(KangfuServiceActivity.this)
                            .openCamera(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                            .maxSelectNum(8)// 最大图片选择数量 int
                            .minSelectNum(1)// 最小选择数量 int
                            .imageSpanCount(4)// 每行显示个数 int
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .previewImage(true)// 是否可预览图片 true or false
                            .isCamera(true)// 是否显示拍照按钮 true or false
                            .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                            .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                            .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                            .enableCrop(false)// 是否裁剪 true or false
                            .compress(false)// 是否压缩 true or false
                            .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示 true or false
                            .isGif(false)// 是否显示gif图片 true or false
                            .compressSavePath("")//压缩图片保存地址
                            .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> list
                            .minimumCompressSize(100)// 小于100kb的图片不压缩
                            .synOrAsy(true)//同步true或异步false 压缩 默认同步
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                    break;
                case R.id.tv_cancel:
                    //取消
                    closePopupWindow();
                    break;
            }
            closePopupWindow();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调

                    images = PictureSelector.obtainMultipleResult(data);
                    selectList.addAll(images);

                    selectList = PictureSelector.obtainMultipleResult(data);
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    List<File> paths = new ArrayList<>();
                    for (int i = 0; i < selectList.size(); i++) {
                        String path = selectList.get(i).getPath();
                        File file = new File(path);
                        paths.add(file);
                    }
                    upLoadPic(paths);
                    break;
            }
        }
    }

    public void upLoadPic(final List<File> files) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                String URL = Base.ImgUpUrl + patientID;
                filter.clear();
                for (int i = 0; i < files.size(); i++) {
                    count = i;
                    try {
                        byte[] content;
                        content = HcUtils.readStream(files.get(i));
                        MultipartBody.Builder builder = new MultipartBody.Builder();
                        builder.setType(MultipartBody.FORM).addFormDataPart("file", files.get(i).getName
                                (), RequestBody.create(MediaType.parse
                                ("application/octet-stream"), content));
                        RequestBody requestBody = builder.build();
                        String result = OkHttpUtil.initUpLoad(URL, requestBody);
                        Gson gson = new Gson();
                        UpImgBean upImgBean = gson.fromJson(result, UpImgBean.class);
                        fileId = upImgBean.getFileId();
                        fileType = upImgBean.getFileType();
                        fileUrl = upImgBean.getFileUrl();
                        HashMap hashMap = new HashMap();
                        hashMap.put("sType", fileType);
                        hashMap.put("attachmentID", fileId);
                        hashMap.put("attachmentUrl", fileUrl);
                        filter.add(hashMap);
                        if ((count == files.size() - 1) && (filter.size() - 1 == count)) {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    listHashMap.put("json", filter);
                    Gson gson = new Gson();
                    sPic = gson.toJson(listHashMap);
                    break;
            }
        }
    };

    public void closePopupWindow() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
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
