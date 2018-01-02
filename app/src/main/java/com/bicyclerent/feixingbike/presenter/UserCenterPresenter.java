package com.bicyclerent.feixingbike.presenter;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bicyclerent.feixingbike.Application;
import com.bicyclerent.feixingbike.Nohttp.IBasePresenter;
import com.bicyclerent.feixingbike.activity.UserCenterActivity;
import com.bicyclerent.feixingbike.activityinterface.IUserCenterActivity;
import com.bicyclerent.feixingbike.javabean.BillsBean;
import com.bicyclerent.feixingbike.model.UserCenterModel;
import com.bicyclerent.feixingbike.modelinterface.IUserCenterModel;
import com.bicyclerent.feixingbike.presenterinterface.IUserCenterPresenter;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刺雒 on 2017/2/14.
 */
public class UserCenterPresenter implements IUserCenterPresenter,IBasePresenter{
    private IUserCenterActivity mActivity;
    private IUserCenterModel mModel;
    private ArrayList<String> infoDetails;
    private boolean showBills;
    public UserCenterPresenter(Context context,UserCenterActivity userCenterActivity){
        mActivity = userCenterActivity;
        mModel = new UserCenterModel(this,context);
        infoDetails = new ArrayList<>();
    }

    @Override
    public void getUnAccount(){
        mModel.setGetUnAccountBillsRequest();
    }

    @Override
    public void getBills(boolean showBills ) {
        this.showBills =showBills;
        mModel.setGetBillsRequest();
    }

    @Override
    public void onSucceed(int what, String text) {
        switch (what){
             /*正在使用请求*/
            case 1:
                ArrayList<BillsBean> unAccountBillBeans = JSON.parseObject(text, new TypeReference<ArrayList<BillsBean>>() {
                });
                if(unAccountBillBeans.size()!=0) {
                    setTime(unAccountBillBeans.get(0));
                }
                mActivity.setReturnBikeIntent(unAccountBillBeans);
                break;

            /*设置订单的详情*/
            case 2:
                ArrayList<BillsBean> billsBeans = JSON.parseObject(text, new TypeReference<ArrayList<BillsBean>>() {
                });
                for (int i = 0; i < billsBeans.size(); i++) {
                    setTime(billsBeans.get(i));
                }

                //如果是查看全部订单
                if(!showBills) {
                    mActivity.setShowBillsIntent(billsBeans);
                }else {
                    //显示所有的信息
                    setData(billsBeans);
                    mActivity.setCyclingInfo(infoDetails);
                }
                break;
        }
    }

    /*设置用户消耗的碳排量等信息*/
    private void setData(ArrayList<BillsBean> billsBeans){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        long sumTime = 0;
        if(billsBeans.size() == 0){
            infoDetails.add(String.valueOf(sumTime));
            infoDetails.add(String.valueOf(sumTime * 100));
            infoDetails.add(String.valueOf(sumTime * 40));
            return;
        }

        for(BillsBean billsBean:billsBeans){
            try {
                Date DBTime = simpleDateFormat.parse(billsBean.getBtime());
                Date DETime = simpleDateFormat.parse(billsBean.getEtime());
                long min = (DETime.getTime() - DBTime.getTime())/1000/60;
                sumTime += min;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        infoDetails.add(String.valueOf(sumTime));
        infoDetails.add(String.valueOf(sumTime * 5));
        infoDetails.add(String.valueOf(sumTime * 10));

    }
    //设置开始结束时间
    private void setTime(BillsBean billsBean){
        String bTime = billsBean.getBtime().substring(0,10) +" "+ billsBean.getBtime().substring(11,19);
        String ETime = billsBean.getEtime().substring(0, 10) + " "+billsBean.getEtime().substring(11,19);
        billsBean.setBtime(bTime);
        billsBean.setEtime(ETime);
    }

    //网络请求失败
    @Override
    public void onFailed(int what, String toast) {
        switch (what){
            case 0:
                mActivity.setToast(toast);
                break;
            case 1:
                mActivity.setToast(toast);
                break;
        }
    }
}
