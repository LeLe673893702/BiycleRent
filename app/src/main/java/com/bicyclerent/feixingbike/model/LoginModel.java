package com.bicyclerent.feixingbike.model;

import com.bicyclerent.feixingbike.Application;
import com.bicyclerent.feixingbike.javabean.RentpointsBean;
import com.bicyclerent.feixingbike.javabean.UsersBean;
import com.bicyclerent.feixingbike.modelinterface.IGetLongitudeAndLatitude;
import com.bicyclerent.feixingbike.modelinterface.ILoginModel;
import com.bicyclerent.feixingbike.Nohttp.BaseNohttp;
import com.bicyclerent.feixingbike.Nohttp.IBasePresenter;
import com.bicyclerent.feixingbike.presenter.LoginPresenter;

import java.util.ArrayList;

/**
 * Created by 刺雒 on 2017/2/3.
 */
public class LoginModel implements ILoginModel,IGetLongitudeAndLatitude{
    private IBasePresenter mPresenter;
    private String loginRequestUrl = Application.HTTP_URL + "/UserAPIHandler.ashx?Type=Loginer";
    private String getLongitudeAndLatitudeUrl = Application.HTTP_URL + "/RentPointsAPIHandler.ashx?Type=GetLongitudeAndLatitude";
    public LoginModel(LoginPresenter loginPresenter){
        this.mPresenter = loginPresenter;
    }

    @Override
    public void setLoginRequest(String phoneNum,String password) {
        BaseNohttp loginRequest = new BaseNohttp(mPresenter);
        loginRequest.setRequestUrl(loginRequestUrl);
        loginRequest.add("UserID",phoneNum);
        loginRequest.add("Password",password);
        loginRequest.setConnectTimeout(3000).setReadTimeout(3000);
        loginRequest.createNohttp(1);
    }

    @Override
    public void setGetLongitudeAndLatitude() {
        BaseNohttp getLongitudeAndLatitude = new BaseNohttp(mPresenter);
        getLongitudeAndLatitude.setRequestUrl(getLongitudeAndLatitudeUrl);
        getLongitudeAndLatitude.setConnectTimeout(3000).setReadTimeout(3000);
        getLongitudeAndLatitude.createNohttp(2);
    }
}
