package com.bicyclerent.feixingbike.model;

import android.content.Context;
import android.util.Log;

import com.bicyclerent.feixingbike.Application;
import com.bicyclerent.feixingbike.Nohttp.BaseNohttp;
import com.bicyclerent.feixingbike.Nohttp.IBasePresenter;
import com.bicyclerent.feixingbike.modelinterface.IUserCenterModel;
import com.bicyclerent.feixingbike.presenter.UserCenterPresenter;
import com.bicyclerent.feixingbike.utils.SharedPreferencesUtil;

/**
 * Created by 刺雒 on 2017/2/14.
 */
public class UserCenterModel implements IUserCenterModel{
    private IBasePresenter mPresenter;
    private Context mContext;
    private SharedPreferencesUtil mSharedPreferencesUtil;
    private String getUnAccountBillUrl = Application.HTTP_URL + "/BillAPIHandler.ashx?type=GetUnAccount";
    private String getAllBillUrl = Application.HTTP_URL + "/BillAPIHandler.ashx?Type=GetBill";
    public UserCenterModel(UserCenterPresenter basePresenter,Context context){
        this.mPresenter = basePresenter;
        this.mContext = context;
        mSharedPreferencesUtil = new SharedPreferencesUtil(mContext,"userInfo");
    }

    //设置订单
    @Override
    public void setGetUnAccountBillsRequest()  {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(mContext,"userInfo");
        BaseNohttp getBillsRequest = new BaseNohttp(mPresenter);
        getBillsRequest.setRequestUrl(getUnAccountBillUrl);
        getBillsRequest.add("UserID",sharedPreferencesUtil.getStringData("userID"));
        getBillsRequest.createNohttp(1);
    }

    @Override
    public void setGetBillsRequest(){
        BaseNohttp getBillsRequest = new BaseNohttp(mPresenter);
        getBillsRequest.setRequestUrl(getAllBillUrl);
        getBillsRequest.add("UserID",mSharedPreferencesUtil.getStringData("userID"));
        getBillsRequest.createNohttp(2);
    }

}
