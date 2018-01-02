package com.bicyclerent.feixingbike.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.bicyclerent.feixingbike.Nohttp.IBasePresenter;
import com.bicyclerent.feixingbike.activity.IdentificationActivity;
import com.bicyclerent.feixingbike.activityinterface.IIdentificationActivity;
import com.bicyclerent.feixingbike.model.IdentificationModel;
import com.bicyclerent.feixingbike.modelinterface.IIdentificationModel;
import com.bicyclerent.feixingbike.presenterinterface.IIdentificationPresenter;
import com.bicyclerent.feixingbike.utils.SharedPreferencesUtil;

import java.util.HashMap;

/**
 * Created by 刺雒 on 2017/2/9.
 */
public class IdentificationPresenter implements IIdentificationPresenter,IBasePresenter{

    private IIdentificationActivity mActivity;
    private IIdentificationModel mModel;
    private Context mContext;

    public IdentificationPresenter(Context context,IdentificationActivity activity){
        this.mActivity = activity;
        this.mContext = context;
        this.mModel = new IdentificationModel(this,mContext);
    }
    @Override
    public void setSummitClick(Bitmap bitmap)  {
        mModel.setUnloadImageRequest(bitmap);
    }

    @Override
    public void onSucceed(int what, String o)  {
        mActivity.setToast("图片上传成功");
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(mContext,"userInfo");
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("userType","已认证用户");
        sharedPreferencesUtil.setData(hashMap);
        mActivity.setIntent();
    }

    @Override
    public void onFailed(int what, String toast) {
        mActivity.setToast("图片上传失败");
    }
}
