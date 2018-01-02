package com.bicyclerent.feixingbike.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bicyclerent.feixingbike.Application;
import com.bicyclerent.feixingbike.Nohttp.BaseNohttp;
import com.bicyclerent.feixingbike.Nohttp.IBasePresenter;
import com.bicyclerent.feixingbike.javabean.RentpointsBean;
import com.bicyclerent.feixingbike.javabean.UsersBean;
import com.bicyclerent.feixingbike.modelinterface.IGetLongitudeAndLatitude;
import com.bicyclerent.feixingbike.modelinterface.IRegisterModel;
import com.bicyclerent.feixingbike.presenter.RegisterPresenter;

import java.util.ArrayList;

/**
 * Created by 刺雒 on 2017/2/2.
 */
public class RegisterModel  implements IRegisterModel{
    private IBasePresenter mPresenter;
    private String registerUrl = Application.HTTP_URL + "/UserAPIHandler.ashx?Type=Register";
    private String getLongitudeAndLatitudeUrl = Application.HTTP_URL + "/RentPointsAPIHandler.ashx?Type=GetLongitudeAndLatitude";
    public RegisterModel(RegisterPresenter registerPresenter){
        this.mPresenter = registerPresenter;
    }

    @Override
    public void setUserInfoRequest(String phoneNum, String password)  {
        BaseNohttp userInfoRequest = new BaseNohttp(mPresenter);
        userInfoRequest.setRequestUrl(registerUrl);
        userInfoRequest.add("UserID", phoneNum);
        userInfoRequest.add("Password", password);
        userInfoRequest.add("UserType",Application.userType.未认证用户.ordinal());
        userInfoRequest.setConnectTimeout(3000).setReadTimeout(3000);
        userInfoRequest.createNohttp(1);
    }

    @Override
    public void setGetLongitudeAndLatitude() {
        BaseNohttp getLongitudeAndLatitude = new BaseNohttp(mPresenter);
        getLongitudeAndLatitude.setRequestUrl(getLongitudeAndLatitudeUrl);
        getLongitudeAndLatitude.setConnectTimeout(3000).setReadTimeout(3000);
        getLongitudeAndLatitude.createNohttp(2);
    }
}
