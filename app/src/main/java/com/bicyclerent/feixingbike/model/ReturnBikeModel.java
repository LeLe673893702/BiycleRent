package com.bicyclerent.feixingbike.model;

import android.content.Context;

import com.bicyclerent.feixingbike.Application;
import com.bicyclerent.feixingbike.Nohttp.BaseNohttp;
import com.bicyclerent.feixingbike.Nohttp.IBasePresenter;
import com.bicyclerent.feixingbike.javabean.BillsBean;
import com.bicyclerent.feixingbike.modelinterface.IReturnBikeModel;
import com.bicyclerent.feixingbike.presenter.ReturnBikePresenter;
import com.bicyclerent.feixingbike.utils.SharedPreferencesUtil;

import java.util.List;

/**
 * Created by 刺雒 on 2017/2/6.
 */
public class ReturnBikeModel implements IReturnBikeModel {
    private IBasePresenter mPresenter;
    private String returnBikeUrl = Application.HTTP_URL  + "/BillAPIHandler.ashx?type=ReturnBicycle";
    public ReturnBikeModel(ReturnBikePresenter basePresenter,Context context){
        this.mPresenter = basePresenter;
    }

    @Override
    public void setReturnBikeRequest(String billID) {
        BaseNohttp returnBikeRequest = new BaseNohttp(mPresenter);
        returnBikeRequest.setRequestUrl(returnBikeUrl);
        returnBikeRequest.add("BillID",billID);
        returnBikeRequest.setConnectTimeout(3000).setReadTimeout(3000);
        returnBikeRequest.createNohttp(0);
    }


}
