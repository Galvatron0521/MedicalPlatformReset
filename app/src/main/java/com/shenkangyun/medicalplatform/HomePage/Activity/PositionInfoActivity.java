package com.shenkangyun.medicalplatform.HomePage.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.google.gson.Gson;
import com.shenkangyun.medicalplatform.BaseFolder.Base;
import com.shenkangyun.medicalplatform.BeanFolder.MechanismBean;
import com.shenkangyun.medicalplatform.BeanFolder.SearchBean;
import com.shenkangyun.medicalplatform.LocationPage.overlayutil.DrivingRouteOverlay;
import com.shenkangyun.medicalplatform.LocationPage.overlayutil.PoiOverlay;
import com.shenkangyun.medicalplatform.LocationPage.overlayutil.TransitRouteOverlay;
import com.shenkangyun.medicalplatform.LocationPage.overlayutil.WalkingRouteOverlay;
import com.shenkangyun.medicalplatform.R;
import com.shenkangyun.medicalplatform.UtilsFolder.FuncUtil;
import com.shenkangyun.medicalplatform.UtilsFolder.GsonCallBack;
import com.shenkangyun.medicalplatform.UtilsFolder.LocationService;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PositionInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private final int SDK_PERMISSION_REQUEST = 127;
    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.mMapView)
    MapView mMapView;
    @BindView(R.id.btn_route)
    RadioGroup btnRoute;
    @BindView(R.id.ed_search)
    EditText edSearch;

    private LatLng point;
    private BaiduMap mBaiduMap;
    private String permissionInfo;
    private LocationService locService;
    private String city = "泰安";
    private LatLng ll;
    private PlanNode stNode;
    private PlanNode enNode;
    private GeoCoder geocode;
    private PoiSearch search;
    private OverlayOptions option;
    private RoutePlanSearch mSearch;

    private String searchName;
    private String searchPhone;
    private String searchContacts;
    private int size;
    private String key;
    private String name;
    private String phone;
    private String unitName;
    private String callPhone;
    private double xAxis;
    private double yAxis;
    private double x;
    private double y;
    private String responsibility;
    private List<MechanismBean.DataBean.ListBean> listBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_info);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("机构位置");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        getPermissions();
        initView();
        initLocation();
        initData();
    }

    private void initView() {
        Intent intent = getIntent();
        unitName = intent.getStringExtra("name");
        x = intent.getDoubleExtra("X", 117.096674);
        y = intent.getDoubleExtra("Y", 36.263177);
        mBaiduMap = mMapView.getMap();
        listBeans = new ArrayList<>();
        geocode = GeoCoder.newInstance();
        search = PoiSearch.newInstance();
        mSearch = RoutePlanSearch.newInstance();
        option = new MarkerOptions();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15));
        locService = new LocationService(getApplication());
        LocationClientOption mOption = locService.getDefaultLocationClientOption();
        mOption.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        mOption.setCoorType("bd09ll");
        locService.setLocationOption(mOption);
        locService.registerListener(listener);
        search.setOnGetPoiSearchResultListener(resultListener);
        locService.start();

        LatLng latLng = new LatLng(y, x);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
        mBaiduMap.setMyLocationEnabled(false);
    }

    private void initLocation() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "organizeList")
                .addParams("data", new organizeList(Base.getMD5Str(), Base.getTimeSpan(), "1", "0", "100").toJson())
                .build().execute(new GsonCallBack<MechanismBean>() {
            @Override
            public void onSuccess(MechanismBean response) throws JSONException {
                size = response.getData().getList().size();
                for (int i = 0; i < size; i++) {
                    MechanismBean.DataBean.ListBean listBean = new MechanismBean.DataBean.ListBean();
                    name = response.getData().getList().get(i).getName();
                    phone = response.getData().getList().get(i).getPhone();
                    xAxis = response.getData().getList().get(i).getXAxis();
                    yAxis = response.getData().getList().get(i).getYAxis();
                    responsibility = response.getData().getList().get(i).getResponsibilityName();

                    LatLng latLng = new LatLng(yAxis, xAxis);
                    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_mark);
                    BitmapDescriptor current = BitmapDescriptorFactory.fromResource(R.drawable.location_icon); // 推算结果
                    // 构建MarkerOption，用于在地图上添加Marker
                    if (unitName.equals(name)) {
                        option = new MarkerOptions().position(latLng)
                                .icon(current).zIndex(9).draggable(true);
                    } else {
                        option = new MarkerOptions().position(latLng).icon(bitmap)
                                .zIndex(9).draggable(true);
                    }
                    // 在地图上添加Marker，并显示
                    Marker marker = (Marker) mBaiduMap.addOverlay(option);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", name);
                    bundle.putString("phone", phone);
                    bundle.putString("responsibility", responsibility);
                    marker.setExtraInfo(bundle);

                    listBean.setName(name);
                    listBean.setPhone(phone);
                    listBean.setXAxis(xAxis);
                    listBean.setYAxis(yAxis);
                    listBean.setResponsibilityName(responsibility);
                    listBeans.add(listBean);
                }
            }

            @Override
            public void onError(Exception e) {
            }
        });
    }


    private void initData() {
        final View popupView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_map_item, null);
        final RelativeLayout layout = popupView.findViewById(R.id.contents);
        final TextView text_name = popupView.findViewById(R.id.text_name);
        final TextView text_tel = popupView.findViewById(R.id.text_tel);
        final TextView text_contacts = popupView.findViewById(R.id.text_contacts);
        final Button btn_go = popupView.findViewById(R.id.btn_go);
        //添加marker点击事件的监听
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //从marker中获取经纬度的信息来转化成屏幕的坐标
                String name = null;
                String responsibility = null;
                try {
                    name = marker.getExtraInfo().getString("name");
                    callPhone = marker.getExtraInfo().getString("phone");
                    responsibility = marker.getExtraInfo().getString("responsibility");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(callPhone.trim())) {
                    layout.setVisibility(View.VISIBLE);
                }
                ll = marker.getPosition();
                Point point = mBaiduMap.getProjection().toScreenLocation(ll);
                LatLng locInfo = mBaiduMap.getProjection().fromScreenLocation(point);

                text_name.setText(name);
                text_tel.setText(callPhone);
                text_contacts.setText(responsibility);
                text_tel.setOnClickListener(PositionInfoActivity.this);
                btn_go.setOnClickListener(PositionInfoActivity.this);
                //信息窗口
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(responsibility)) {
                    final InfoWindow mInfoWindow = new InfoWindow(popupView, locInfo, -47);
                    if (popupView.isShown()) {
                        layout.setVisibility(View.GONE);
                        mBaiduMap.hideInfoWindow();  //隐藏
                    } else {
                        mBaiduMap.showInfoWindow(mInfoWindow);//显示
                    }
                    return true;
                }
                return false;
            }
        });

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (popupView.isShown()) {
                    layout.setVisibility(View.GONE);
                    mBaiduMap.hideInfoWindow();  //隐藏
                }
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
    }

    /***
     * 定位结果回调，在此方法中处理定位结果
     */
    BDAbstractLocationListener listener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (location != null && (location.getLocType() == 161 || location.getLocType() == 66)) {
                Message locMsg = locHander.obtainMessage();
                Bundle locData = new Bundle();
                locData.putParcelable("loc", location);
                locMsg.setData(locData);
                locHander.sendMessage(locMsg);
            }
        }

    };

    /***
     * 接收定位结果消息，并显示在地图上
     */
    private Handler locHander = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            try {
                BDLocation location = msg.getData().getParcelable("loc");
                if (location != null) {
                    point = new LatLng(location.getLatitude(), location.getLongitude());
                    // 构建Marker图标
                    getLocationInfo(point);
                    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_focuse_mark); // 推算结果
                    // 构建MarkerOption，用于在地图上添加Marker
                    OverlayOptions option = new MarkerOptions().position(point).icon(bitmap)
                            .zIndex(9).draggable(true);
                    // 在地图上添加Marker，并显示
                    Marker marker = (Marker) mBaiduMap.addOverlay(option);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", "当前位置");
                    bundle.putString("phone", "");
                    bundle.putString("responsibility", "");
                    marker.setExtraInfo(bundle);
                    if (locService.isStart()) {
                        locService.stop();
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    };

    private OnGetPoiSearchResultListener resultListener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            if (poiResult == null || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                Toast.makeText(PositionInfoActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
                return;
            }

            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {

                mBaiduMap.clear();
                //创建PoiOverlay
                PoiOverlay overlay = new PoiOverlay(mBaiduMap);
                //设置PoiOverlay数据
                overlay.setData(poiResult);
                //添加PoiOverlay到地图中
                overlay.addToMap();
                overlay.zoomToSpan();
                List<OverlayOptions> overlayOptions = overlay.getOverlayOptions();
                for (int i = 0; i < overlayOptions.size(); i++) {
                    String poiName = poiResult.getAllPoi().get(i).name;
                    Marker marker = (Marker) mBaiduMap.addOverlay(overlayOptions.get(i));
                    Bundle bundle = new Bundle();
                    if (poiName.equals(searchName)) {
                        bundle.putString("name", poiName);
                        bundle.putString("phone", searchPhone);
                        bundle.putString("responsibility", searchContacts);
                    } else {
                        bundle.putString("name", poiName);
                        bundle.putString("phone", "");
                        bundle.putString("responsibility", "");
                    }
                    marker.setExtraInfo(bundle);
                }
                return;
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
        }
    };

    private void getLocationInfo(LatLng point) {
        geocode.setOnGetGeoCodeResultListener(Listener);
        geocode.reverseGeoCode(new ReverseGeoCodeOption().location(point));
    }

    @OnClick({R.id.Location, R.id.btn_bus, R.id.btn_car, R.id.btn_walk, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Location:
                if (mBaiduMap != null) {
                    mBaiduMap.clear();
                    initIcon();
                    mBaiduMap.setMyLocationEnabled(true);
                    locService.start();
                }
                break;
            case R.id.btn_bus:
                mBaiduMap.clear();
                initIcon();
                mSearch.transitSearch(new TransitRoutePlanOption().from(
                        stNode).city(city).to(enNode));
                break;
            case R.id.btn_car:
                mBaiduMap.clear();
                initIcon();
                mSearch.drivingSearch(new DrivingRoutePlanOption().from(
                        stNode).to(enNode));
                break;
            case R.id.btn_walk:
                mBaiduMap.clear();
                initIcon();
                mSearch.walkingSearch(new WalkingRoutePlanOption().from(
                        stNode).to(enNode));
                break;
            case R.id.tv_search:
                key = edSearch.getText().toString();
                if (!TextUtils.isEmpty(key.trim())) {
                    searchDatabase();
                    PoiCitySearchOption poiCity = new PoiCitySearchOption();
                    poiCity.keyword(key).city(city);//这里还能设置显示的个数，默认显示10个
                    search.searchInCity(poiCity);//执行搜索，搜索结束后，在搜索监听对象里面的方法会被回调
                }
                break;
        }
    }

    private void searchDatabase() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "organizeList")
                .addParams("data", new selectOrganizeListByStreet(Base.getMD5Str(), Base.getTimeSpan(), "1", key).toJson())
                .build().execute(new GsonCallBack<SearchBean>() {
            @Override
            public void onSuccess(SearchBean response) throws JSONException {
                int count = response.getData().getList().size();
                if (count != 0) {
                    for (int i = 0; i < count; i++) {
                        searchName = response.getData().getList().get(i).getName();
                        searchPhone = response.getData().getList().get(i).getPhone();
                        searchContacts = response.getData().getList().get(i).getResponsibilityName();
                    }
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go:
                mBaiduMap.clear();
                initIcon();
                btnRoute.setVisibility(View.VISIBLE);
                getTransitRoute(ll);
                break;
            case R.id.text_tel:
                if (ContextCompat.checkSelfPermission(PositionInfoActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // 没有获得授权，申请授权
                    if (ActivityCompat.shouldShowRequestPermissionRationale(PositionInfoActivity.this,
                            Manifest.permission.CALL_PHONE)) {
                        Toast.makeText(PositionInfoActivity.this, "请授权！", Toast.LENGTH_LONG).show();

                        // 帮跳转到该应用的设置界面，让用户手动授权
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        // 不需要解释为何需要该权限，直接请求授权
                        ActivityCompat.requestPermissions(PositionInfoActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                } else {
                    // 已经获得授权，可以打电话
                    CallPhone();
                }
                break;
        }
    }

    private void CallPhone() {
        if (TextUtils.isEmpty(callPhone)) {
            Toast.makeText(PositionInfoActivity.this, "号码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            // 拨号：激活系统的拨号组件
            Intent intent = new Intent(); // 意图对象：动作 + 数据
            intent.setAction(Intent.ACTION_DIAL); // 设置动作
            Uri data = Uri.parse("tel:" + callPhone); // 设置数据
            intent.setData(data);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private void initIcon() {
        for (int i = 0; i < listBeans.size(); i++) {
            LatLng latLng = new LatLng(listBeans.get(i).getYAxis(), listBeans.get(i).getXAxis());
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_mark);
            BitmapDescriptor current = BitmapDescriptorFactory.fromResource(R.drawable.location_icon); // 推算结果
            // 构建MarkerOption，用于在地图上添加Marker
            if (unitName.equals(name)) {
                option = new MarkerOptions().position(latLng)
                        .icon(current).zIndex(9).draggable(true);
            } else {
                option = new MarkerOptions().position(latLng).icon(bitmap)
                        .zIndex(9).draggable(true);
            }
            Marker overlay = (Marker) mBaiduMap.addOverlay(option);
            Bundle bundle = new Bundle();
            bundle.putString("name", listBeans.get(i).getName());
            bundle.putString("phone", listBeans.get(i).getPhone());
            bundle.putString("responsibility", listBeans.get(i).getResponsibilityName());
            overlay.setExtraInfo(bundle);
        }
    }

    private void getTransitRoute(LatLng ll) {
        mSearch.setOnGetRoutePlanResultListener(planResultListener);
        // 起点与终点
        stNode = PlanNode.withLocation(point);
        enNode = PlanNode.withLocation(ll);
        // 步行路线规划
        mSearch.transitSearch(new TransitRoutePlanOption().from(
                stNode).city(city).to(enNode));
    }

    OnGetRoutePlanResultListener planResultListener = new OnGetRoutePlanResultListener() {
        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
            // 获取步行线路规划结果
            if (walkingRouteResult == null || walkingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(PositionInfoActivity.this, "抱歉，未找到结果",
                        Toast.LENGTH_SHORT).show();
            }
            if (walkingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                walkingRouteResult.getSuggestAddrInfo();
                return;
            }
            if (walkingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                WalkingRouteOverlay overlay = new WalkingRouteOverlay(
                        mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(walkingRouteResult.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();
            }
        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
            // 获取公交换乘路径规划结果
            if (transitRouteResult == null || transitRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(PositionInfoActivity.this, "抱歉，未找到结果",
                        Toast.LENGTH_SHORT).show();
            }
            if (transitRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                transitRouteResult.getSuggestAddrInfo();
                return;
            }
            if (transitRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                TransitRouteOverlay overlay = new TransitRouteOverlay(mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(transitRouteResult.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();
                //Toast.makeText(GuideActivity.this,"点击图标会有指示哦～",
                //        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
            // 获取公交换乘路径规划结果
            if (drivingRouteResult == null || drivingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(PositionInfoActivity.this, "抱歉，未找到结果",
                        Toast.LENGTH_SHORT).show();
            }
            if (drivingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                drivingRouteResult.getSuggestAddrInfo();
                return;
            }
            if (drivingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(drivingRouteResult.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();
                //Toast.makeText(GuideActivity.this,"点击图标会有指示哦～",
                //        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

        }
    };

    OnGetGeoCoderResultListener Listener = new OnGetGeoCoderResultListener() {
        @Override
        public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
            if (reverseGeoCodeResult != null && reverseGeoCodeResult.error == SearchResult.ERRORNO.NO_ERROR) {
                ReverseGeoCodeResult.AddressComponent addressDetail = reverseGeoCodeResult.getAddressDetail();
                city = addressDetail.city;
            }
        }
    };

    @Override
    public void onBackPressed() {
        if (btnRoute.getVisibility() == View.VISIBLE) {
            mBaiduMap.clear();
            initIcon();
            btnRoute.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locService.unregisterListener(listener);
        locService.stop();
        mSearch.destroy();
        geocode.destroy();
        search.destroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();

    }

    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            /*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
                permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
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
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

    static class organizeList {

        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String pageNo;
        private String pageCount;

        public organizeList(String appKey, String timeSpan, String mobileType, String pageNo, String pageCount) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

    static class selectOrganizeListByStreet {

        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String name;

        public selectOrganizeListByStreet(String appKey, String timeSpan, String mobileType, String name) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.name = name;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}