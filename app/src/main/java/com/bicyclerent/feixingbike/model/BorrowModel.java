package com.bicyclerent.feixingbike.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.bicyclerent.feixingbike.Application;
import com.bicyclerent.feixingbike.javabean.BicyclesBean;
import com.bicyclerent.feixingbike.javabean.BillsBean;
import com.bicyclerent.feixingbike.modelinterface.IBorrowModel;
import com.bicyclerent.feixingbike.Nohttp.BaseNohttp;
import com.bicyclerent.feixingbike.Nohttp.IBasePresenter;
import com.bicyclerent.feixingbike.presenter.BorrowPresenter;
import com.bicyclerent.feixingbike.utils.SharedPreferencesUtil;

/**
 * Created by 刺雒 on 2017/2/4.
 */
public class BorrowModel implements IBorrowModel {
    private IBasePresenter mPresenter;
    private SharedPreferencesUtil mSharedPreferencesUtil;
    private String borrowBikeUrl = Application.HTTP_URL + "/BillAPIHandler.ashx?Type=RentBicycle";
    private String getBikeInfoUrl = Application.HTTP_URL + "/BicycleAPIHandler.ashx?Type=GetBicycle";

    public BorrowModel(Context context,BorrowPresenter borrowPresenter){
        this.mPresenter = borrowPresenter;
        mSharedPreferencesUtil = new SharedPreferencesUtil(context,"userInfo");
    }

    //获取
    @Override
    public void setBorrowBikeRequest(String bikeID)  {
        BaseNohttp borrowBikeRequest = new BaseNohttp(mPresenter);
        borrowBikeRequest.setRequestUrl(borrowBikeUrl);
        borrowBikeRequest.add("UserID", mSharedPreferencesUtil.getStringData("userID"));
        borrowBikeRequest.add("BicycleID", bikeID);
        borrowBikeRequest.add("Status",2);
        borrowBikeRequest.createNohttp(0);
    }

    @Override
    public void setGetBikeInfORequest(String bikeID) {
        BaseNohttp getBikeInfo = new BaseNohttp(mPresenter);
        getBikeInfo.setRequestUrl(getBikeInfoUrl);
        getBikeInfo.add("BicycleID",bikeID);
        getBikeInfo.createNohttp(1);
    }

}
