package com.bicyclerent.feixingbike.model;

import android.content.Context;
import android.graphics.Bitmap;

import com.bicyclerent.feixingbike.Application;
import com.bicyclerent.feixingbike.Nohttp.BaseNohttp;
import com.bicyclerent.feixingbike.Nohttp.IBasePresenter;
import com.bicyclerent.feixingbike.modelinterface.IIdentificationModel;
import com.bicyclerent.feixingbike.presenter.IdentificationPresenter;
import com.bicyclerent.feixingbike.utils.ImageToBase64Util;
import com.bicyclerent.feixingbike.utils.SharedPreferencesUtil;

/**
 * Created by 刺雒 on 2017/2/9.
 */
public class IdentificationModel implements IIdentificationModel{
    private String unloadImageUrl = Application.HTTP_URL + "//UserAPIHandler.ashx?Type=UpLoadImg";
    private IBasePresenter mPresenter;
    private SharedPreferencesUtil mSharedPreferencesUtil;
    public IdentificationModel(IdentificationPresenter identificationPresenter,Context context){
        this.mPresenter =identificationPresenter;
        mSharedPreferencesUtil = new SharedPreferencesUtil(context,"userInfo");
    }
    @Override
    public void setUnloadImageRequest(Bitmap bitmap){
        BaseNohttp uploadImageRequest = new BaseNohttp(mPresenter);
        uploadImageRequest.setRequestUrl(unloadImageUrl);
        uploadImageRequest.add("UserID", mSharedPreferencesUtil.getStringData("userID"));
        uploadImageRequest.add("ImgType","CID");
        uploadImageRequest.add("Img", ImageToBase64Util.bitmapToBase64(bitmap));
        uploadImageRequest.setConnectTimeout(3000).setReadTimeout(3000);
        uploadImageRequest.createNohttp(0);
    }
}
