package com.bicyclerent.feixingbike;


import com.bicyclerent.feixingbike.javabean.RentpointsBean;
import com.bicyclerent.feixingbike.mapmarkers.DotInfo;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/8/7 0007.
 */
public class Application extends android.app.Application {

    public  enum userType{黑名单用户,未认证用户,普通用户};
    public Application mApplication;
    public static String HTTP_URL;
    public static RequestQueue sRequestQueue;
    public static ArrayList<RentpointsBean> rentpointsBeans;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mApplication = this;
        NoHttp.initialize(this);
        sRequestQueue = NoHttp.newRequestQueue();
        HTTP_URL = "http://172.16.224.14:80";
        rentpointsBeans = new ArrayList<>();
    }
}
