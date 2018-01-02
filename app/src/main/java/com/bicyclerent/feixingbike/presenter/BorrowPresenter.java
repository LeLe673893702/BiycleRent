package com.bicyclerent.feixingbike.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bicyclerent.feixingbike.activity.BorrowBikeActivity;
import com.bicyclerent.feixingbike.activityinterface.IBorrowBikeActivity;
import com.bicyclerent.feixingbike.javabean.BicyclesBean;
import com.bicyclerent.feixingbike.model.BorrowModel;
import com.bicyclerent.feixingbike.modelinterface.IBorrowModel;
import com.bicyclerent.feixingbike.Nohttp.IBasePresenter;
import com.bicyclerent.feixingbike.presenterinterface.IBorrowPresenter;

/**
 * Created by 刺雒 on 2017/2/4.
 * 还有获取自行车信息接口
 */
public class BorrowPresenter implements IBasePresenter,IBorrowPresenter {
    private Context mContext;
    private IBorrowBikeActivity mActivity;
    private IBorrowModel mModel;
    private String bikeID = null;
    public BorrowPresenter(Context context,BorrowBikeActivity borrowBikeActivity){
        this.mContext = context;
        this.mActivity = borrowBikeActivity;
        this.mModel = new BorrowModel(context,this);
    }


    public void setPasswordDialog(String bikePassword){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("单车开锁密码").setMessage("开锁密码为" + bikePassword);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               mActivity.setIntent();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override

    public void onSucceed(int what, String o) {
        switch (what){
            case 0:
                mModel.setGetBikeInfORequest(bikeID);
                break;
            case 1:
                BicyclesBean bicyclesBean = JSON.parseObject(o,new TypeReference<BicyclesBean>(){});
                setPasswordDialog(bicyclesBean.getReturnpwd());
                break;
        }

    }

    @Override
    public void onFailed(int what, String toast) {
        switch (what){
            case 0:
                mActivity.setToast(toast);
                break;
            case 1:
                break;
        }
    }

    @Override
    public void setBtStartClick(String bikeID) throws ClassNotFoundException {
        this.bikeID = bikeID;
        mModel.setBorrowBikeRequest(bikeID);
    }
}
