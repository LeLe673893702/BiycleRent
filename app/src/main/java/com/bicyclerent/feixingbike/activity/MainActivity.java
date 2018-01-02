package com.bicyclerent.feixingbike.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.bicyclerent.feixingbike.Application;
import com.bicyclerent.feixingbike.Nohttp.IBasePresenter;
import com.bicyclerent.feixingbike.Nohttp.SingleObservable;
import com.bicyclerent.feixingbike.R;
import com.bicyclerent.feixingbike.javabean.RentpointsBean;
import com.bicyclerent.feixingbike.mapmarkers.DotInfo;
import com.bicyclerent.feixingbike.mapmarkers.MapTogetherManager;
import com.bicyclerent.feixingbike.utils.SharedPreferencesUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;


public class MainActivity extends MPermissionsActivity implements AMapLocationListener,LocationSource ,View.OnClickListener,AMap.OnCameraChangeListener,GeocodeSearch.OnGeocodeSearchListener{
    private MapView mvBicycleView = null;
    private AMapLocationClient mLocationClient = null;
    private LatLng mLatLng;
    private ImageView ivMenu;
    private GeocodeSearch mGeocodeSearch;
    private int i = 0;
    private ArrayList<Marker> infoMarkers = new ArrayList<>();
    private Observer<String> mAddressObserver;

    /*数据列表*/
    private List<DotInfo> dots  = new ArrayList<>();

    /*marker数据集合*/
    public Map<String,Marker> markers = new ConcurrentHashMap<>();
    /**
     * 地图初始化比例尺,地图比例尺
     */
    public static float ORGZOON = 10;

    public static final int MARKER_NORMA = 1;
    public static final int MARKER_TOGE = 2;
    public static int markerStatus = MARKER_NORMA;

    @BindView(R.id.location_button)
    public ImageButton ibLocation;
    @BindView(R.id.rent_bike_button)
    public      Button btRentBike;
    private AMapLocationClientOption mLocationClientOption = null;
    private OnLocationChangedListener mListener;
    private AMap mMap;
    private ActionBar mActionBar;
    private String address = null;
    private SharedPreferencesUtil mSharedPreferencesUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBar();
        setContentView(R.layout.activity_main);

        initView();
        initMap();
        requestPermission();
        mvBicycleView.onCreate(savedInstanceState);
    }

    /*
    * 请求权限
    * */
    private void requestPermission(){
        requestPermission(new String[]{Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
                ,Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS},0x0003);
    }

    /*
    * 设置ActionBar
    * */
    private void setActionBar(){
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //显示自定义的ActionBar
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
        View actionbarLayout = LayoutInflater.from(this).inflate(R.layout.action_bar_bicycle_rent,null);
        mActionBar.setCustomView(actionbarLayout);
        ivMenu = (ImageView)actionbarLayout.findViewById(R.id.menu_image);
        ivMenu.setOnClickListener(this);
    }


    /*
    *初始化设置
    */
    private void initView(){
        mvBicycleView = (MapView)findViewById(R.id.bicycle_rent_view);
        mSharedPreferencesUtil = new SharedPreferencesUtil(this,"userInfo");
        ButterKnife.bind(this);
        ibLocation.setOnClickListener(this);
        btRentBike.setOnClickListener(this);
    }

    /*
    *初始化设置地图
    */
    private void initMap(){
        if(mMap == null){
            mMap = mvBicycleView.getMap();
            //设置缩放按钮位置
            UiSettings uiSettings = mMap.getUiSettings();
            uiSettings.setZoomPosition(AMapOptions.ZOOM_POSITION_RIGHT_CENTER);
        }
        mMap.setLocationSource(this);
        mMap.setOnCameraChangeListener(this);
        dots.clear();
        dots = DotInfo.initData(Application.rentpointsBeans);
        //设置看到自己位置
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(AMap.LOCATION_TYPE_LOCATE);
        mGeocodeSearch = new GeocodeSearch(this);
        mGeocodeSearch.setOnGeocodeSearchListener(this);
    }

    /*
    *设置地图定位监听
    * */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if(mListener != null && aMapLocation != null){
            if(aMapLocation != null && aMapLocation.getErrorCode() == 0){
                //显示系统小蓝点
                mListener.onLocationChanged(aMapLocation);
                mLatLng = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                setUpMap();
            }else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    /*地图正在移动监听*/
    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    /*地图移动结束监听*/
    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        updateMapMarkers();
    }

    /*更新markers*/
    private synchronized void updateMapMarkers(){
        if(dots != null && dots.size() > 0){
            if(mMap.getCameraPosition().zoom < ORGZOON){
                markerStatus = MARKER_TOGE;
                updateTogMarkers();
            }
        }else if(markerStatus == MARKER_TOGE) {
            markerStatus = MARKER_NORMA;
            updateNormalMarkers();
        }
        // 设置marker点击事件,若是聚合网点此时点击marker则放大地图显示正常网点
        mMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                if(markerStatus == MARKER_TOGE){
                    markerStatus = MARKER_NORMA;
                    // 初始化地图按指定的比例尺移动到定位的坐标
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(marker.getPosition(), ORGZOON+5, 3, 0)), 1000, null);
                    mMap.clear();
                    markers.clear();
                    loadMarker(DotInfo.initData(Application.rentpointsBeans));
                }else if(markerStatus == MARKER_NORMA) {
                    LatLonPoint latLonPoint = new LatLonPoint(marker.getPosition().latitude,marker.getPosition().longitude);
                    RegeocodeQuery query = new RegeocodeQuery(latLonPoint,500f,GeocodeSearch.AMAP);
                    mGeocodeSearch.getFromLocationAsyn(query);
                    mAddressObserver = new Observer<String>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable throwable) {

                        }

                        @Override
                        public void onNext(String s) {
                            if(infoMarkers.size() != 0){
                                infoMarkers.get(i-1).hideInfoWindow();
                            }
                            marker.setTitle("租借地点");
                            marker.setSnippet(s);
                            marker.showInfoWindow();
                            infoMarkers.add(marker);
                            i++;
                        }
                    };

                }
                return true;
            }
        });
        System.gc();
    }

    /**
     * 更新普通网点数据
     */
    private void updateNormalMarkers() {
        // 判断上一次更新marker操作的操作类型,若上次显示的是聚合网点,则先清空地图,然后清空网点信息,在刷新地图marker
        mMap.clear();
        markers.clear();
        loadMarker(DotInfo.initData( Application.rentpointsBeans));
    }


    /**
     * 更新聚合网点
     */
    private void updateTogMarkers() {
        mMap.clear();
        // 更新聚合marker
        MapTogetherManager.getInstance(this, mMap).onMapLoadedUpdateMarker(markers);
    }

    /**
     * 初始化marker数据
     */
    private void loadMarker(List<DotInfo> dotList) {
        if (dotList == null || dotList.size() == 0) {
            return;
        }

        for (int i = 0; i < dotList.size(); i++) {
            DotInfo dotInfo = dotList.get(i);

            MarkerOptions options = new MarkerOptions();
            options.anchor(0.5f, 1.0f);
            options.position(new LatLng(dotInfo.getDotLat(), dotInfo.getDotLon()));

            setMarkersStyle(options);

            Marker marker = mMap.addMarker(options);
            marker.setObject(dotInfo);
            marker.setZIndex(ORGZOON);
            markers.put(dotInfo.getDotId(), marker);
        }
    }

    /*设置markers样式*/
    private void setMarkersStyle(MarkerOptions options){
        View view = LayoutInflater.from(this).inflate(R.layout.map_markers_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_marker);
        imageView.setImageResource(R.drawable.map_markers);
        options.icon(BitmapDescriptorFactory.fromView(view));
    }

    /*
    *设置定位蓝点的样式
    */
    private void setUpMap(){
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        myLocationStyle.strokeWidth(0f);
        mMap.setMyLocationStyle(myLocationStyle);
    }

    /*
    *开始定位
    */
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if(mLocationClient == null) {
            //初始化定位
            mLocationClient = new AMapLocationClient(this);
            //初始化定位参数
            mLocationClientOption = new AMapLocationClientOption();
            //设置监听回调监听
            mLocationClient.setLocationListener(this);
            //设置高精度模式
            mLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位间隔2000
            mLocationClientOption.setInterval(2000);
            //设置是否只定位一次
            mLocationClientOption.setOnceLocation(false);
            //设置定位参数
            mLocationClient.setLocationOption(mLocationClientOption);
            //启动定位
            mLocationClient.startLocation();
        }

    }

    /*
    * 停止定位
    * */
    @Override
    public void deactivate() {
        mListener = null;
        if(mLocationClient != null){
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }

    /*
    * 设置点击事件
    * */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.location_button:
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 15));
                break;

            case R.id.menu_image:
                Intent intent = new Intent(this,UserCenterActivity.class);
                startActivity(intent);
//                activity动画跳转
                overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
                break;

            case R.id.rent_bike_button:
                switch (mSharedPreferencesUtil.getStringData("userType")){
                    case "已注册用户":
                        startActivity(new Intent(this,ForegiftActivity.class));
                        break;
                    case "已交押金用户":
                        startActivity(new Intent(this,IdentificationActivity.class));
                        break;
                    case "已认证用户":
                        startActivity(new Intent(this,BorrowBikeActivity.class));
                        break;
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvBicycleView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mvBicycleView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvBicycleView.onResume();
        updateNormalMarkers();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mvBicycleView.onSaveInstanceState(outState);
    }


    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        RegeocodeAddress regeocodeAddress = regeocodeResult.getRegeocodeAddress();
        address = regeocodeAddress.getFormatAddress() + "附近";
        SingleObservable.getInstance().observable(address).subscribe(mAddressObserver);
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }
}
