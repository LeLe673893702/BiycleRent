package com.bicyclerent.feixingbike.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bicyclerent.feixingbike.Application;
import com.bicyclerent.feixingbike.Nohttp.BaseNohttp;
import com.bicyclerent.feixingbike.Nohttp.IBasePresenter;
import com.bicyclerent.feixingbike.R;
import com.bicyclerent.feixingbike.javabean.RentpointsBean;
import com.bicyclerent.feixingbike.javabean.UsersBean;
import com.bicyclerent.feixingbike.utils.MD5Util;
import com.bicyclerent.feixingbike.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class WelcomeActivity extends AppCompatActivity implements IBasePresenter{
    private SharedPreferencesUtil mSharedPreferencesUtil;
    private String loginRequestUrl = Application.HTTP_URL + "/UserAPIHandler.ashx?Type=Loginer";
    private String getLongitudeAndLatitudeUrl = Application.HTTP_URL + "/RentPointsAPIHandler.ashx?Type=GetLongitudeAndLatitude";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mSharedPreferencesUtil = new SharedPreferencesUtil(this,"userInfo");
        if(mSharedPreferencesUtil.getStringData("userID").equals("")){
            startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
        }else {
            try {
                setLoginRequest();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /*发送登录请求请求*/
    private void setLoginRequest() throws ClassNotFoundException {
        BaseNohttp LoginRequest = new BaseNohttp(this);
        LoginRequest.setRequestUrl(loginRequestUrl);
        LoginRequest.add("UserID",mSharedPreferencesUtil.getStringData("userID"));
        LoginRequest.add("Password", mSharedPreferencesUtil.getStringData("password"));
        LoginRequest.setConnectTimeout(3000).setReadTimeout(3000);
        LoginRequest.createNohttp(1);
    }

    /*发送获取经纬度请求*/
    private void setGetLongitudeAndLatitudeRequest(){
        BaseNohttp getLongitudeAndLatitudeRequest = new BaseNohttp(this);
        getLongitudeAndLatitudeRequest.setRequestUrl(getLongitudeAndLatitudeUrl);
        getLongitudeAndLatitudeRequest.setConnectTimeout(3000).setReadTimeout(3000);
        getLongitudeAndLatitudeRequest.createNohttp(2);
    }

    /*发送*/
    @Override
    public void onSucceed(int what, String o)  {
        switch (what){
            case 1:
                UsersBean usersBean = JSONObject.parseObject(o, new TypeReference<UsersBean>() {
                });
                SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(this, "userInfo");
                HashMap<String, Object> userInfoMap = new HashMap<>();
                userInfoMap.put("userID", usersBean.getUserid());
                userInfoMap.put("password", usersBean.getPassword());
                Log.e("fsafas", usersBean.getUsertype() + "");
                if(usersBean.getUsertype() == 2) {
                    userInfoMap.put("userType", "已认证用户");
                }
                if(usersBean.getUsertype() == 1){
                    userInfoMap.put("userType","已交押金用户");
                }
                sharedPreferencesUtil.setData(userInfoMap);
                setGetLongitudeAndLatitudeRequest();
                break;
            case 2:
                Application.rentpointsBeans = JSON.parseObject(o,new TypeReference<ArrayList<RentpointsBean>>(){});
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onFailed(int what, String toast) {
        Toast.makeText(this,"网络连接错误",Toast.LENGTH_SHORT).show();
    }
}
