package com.bicyclerent.feixingbike.Nohttp;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bicyclerent.feixingbike.Application;
import com.bicyclerent.feixingbike.javabean.UsersBean;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;

/**
 * Created by 刺雒 on 2017/1/15.
 */
public class BaseNohttp {
    private IBasePresenter mPresenter = null;
    RequestQueue mRequestQueue = Application.sRequestQueue;
    Request<String> requestInfo;
    private JSONObject mJSONObject = null;
    private SingleObservable observableHelper = SingleObservable.getInstance();
    private ArrayList<Object> parameters = new ArrayList<>();
    public BaseNohttp(IBasePresenter presenter){
        this.mPresenter = presenter;
    }
    public Request<String> setRequestUrl(String url){
        requestInfo = NoHttp.createStringRequest(url, RequestMethod.POST);
        return requestInfo;
    }
    public void createNohttp(final int requestCode){
        mRequestQueue.add(requestCode, requestInfo, new OnResponseListener<String>() {
            @Override
            public void onStart(int i) {
                Log.e("start", "开始传输");
            }

            @Override
            public void onSucceed(int i, Response<String> response) {
                mJSONObject = JSON.parseObject(response.get());
                String message = mJSONObject.getString("Message");
                int status = mJSONObject.getInteger("Status");
                if (status == 1) {
                    String jsonData = mJSONObject.getString("JsonData");
                    parameters.add(requestCode);
                    parameters.add(1);
                    parameters.add(jsonData);
                    observableHelper.observable(parameters);
                } else {
                    Log.e("error", "error-----" + message);
                    parameters.add(requestCode);
                    parameters.add(0);
                    parameters.add(message);
                    observableHelper.observable(parameters);
                }
                SingleObserver.getInstance().setPresenter(mPresenter);
                observableHelper.subscribe(SingleObserver.getInstance());
            }

            @Override
            public void onFailed(int i, String s, Object o, Exception e, int i1, long l) {
                Log.e("failed",e.getMessage());
            }

            @Override
            public void onFinish(int i) {

            }
        });

    }
    //添加需要传入
    public Request<String> add(String key,Object value){
        requestInfo.add(key, String.valueOf(value));
        return requestInfo;
    }

    //设置读取时间
    public Request<String> setReadTimeout(int time){
        requestInfo.setReadTimeout(time);
        return requestInfo;
    }

    //设置连接时间
    public Request<String> setConnectTimeout(int time){
        requestInfo.setReadTimeout(time);
        return requestInfo;
    }

}
