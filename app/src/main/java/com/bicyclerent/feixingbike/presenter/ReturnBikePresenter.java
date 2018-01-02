package com.bicyclerent.feixingbike.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bicyclerent.feixingbike.Nohttp.IBasePresenter;
import com.bicyclerent.feixingbike.activity.ReturnBikeActivity;
import com.bicyclerent.feixingbike.activityinterface.IReturnBikeActivity;
import com.bicyclerent.feixingbike.javabean.BillsBean;
import com.bicyclerent.feixingbike.model.ReturnBikeModel;
import com.bicyclerent.feixingbike.modelinterface.IReturnBikeModel;
import com.bicyclerent.feixingbike.presenterinterface.IReturnBikePresenter;
import com.bicyclerent.feixingbike.utils.SharedPreferencesUtil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 刺雒 on 2017/2/6.
 */
public class ReturnBikePresenter implements IBasePresenter,IReturnBikePresenter{
    private Context mContext;
    private IReturnBikeActivity mActivity;
    private IReturnBikeModel mModel;
    private String billID;

    public ReturnBikePresenter(Context context,ReturnBikeActivity returnBikeActivity){
        mContext  = context;
        this.mActivity = returnBikeActivity;
        mModel = new ReturnBikeModel(this,mContext);
    }


    @Override
    public void onSucceed(int what, String o) throws ParseException {
        switch (what){
            /*还车请求*/
            case 0:
                BillsBean billsBean = JSON.parseObject(o, new TypeReference<BillsBean>() {
                });
                BigDecimal cost = billsBean.getCost();
                cost.setScale(0, BigDecimal.ROUND_CEILING);
                mActivity.setIntent(String.valueOf(cost),billsBean.getBillid());
                break;
        }
    }

    @Override
    public void onFailed(int what, String toast) {
        mActivity.setToast("还车失败,请重新还一次");
    }

    /*设置对话框*/
    public void setDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("结束骑行");
        builder.setMessage("您是否结束本次骑行");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mModel.setReturnBikeRequest(billID);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /*设置完成骑行点击事件*/
    @Override
    public void setBtFinishCyclingClick(String billID) throws ClassNotFoundException {
       this.billID = billID;
        setDialog();
    }


}
