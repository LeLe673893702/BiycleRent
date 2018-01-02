package com.bicyclerent.feixingbike.presenter;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bicyclerent.feixingbike.Nohttp.IBasePresenter;
import com.bicyclerent.feixingbike.activity.RegisterActivity;
import com.bicyclerent.feixingbike.activityinterface.IRegisterActivity;
import com.bicyclerent.feixingbike.javabean.RentpointsBean;
import com.bicyclerent.feixingbike.model.RegisterModel;
import com.bicyclerent.feixingbike.modelinterface.IRegisterModel;
import com.bicyclerent.feixingbike.presenterinterface.IRegisterPresenter;
import com.bicyclerent.feixingbike.utils.MD5Util;
import com.bicyclerent.feixingbike.utils.SMSVerificationUtil;
import com.bicyclerent.feixingbike.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.HashMap;

import cn.smssdk.SMSSDK;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by 刺雒 on 2017/1/26.
 */
public class RegisterPresenter implements IRegisterPresenter,Observer,IBasePresenter{
    private String phoneNum = null,password,code;
    private Context mContext = null;
    private IRegisterActivity mActivity = null;
    private SMSVerificationUtil mSMSVerificationUtil = null;
    private SharedPreferencesUtil mSharedPreferencesUtil;
    private IRegisterModel mModel;
    private ArrayList<RentpointsBean> mRentpointsBeans;
    public RegisterPresenter(Context context,RegisterActivity registerActivity){
        this.mContext = context;
        this.mActivity = registerActivity;
        this.mModel = new RegisterModel(RegisterPresenter.this);
        this.mSMSVerificationUtil = new SMSVerificationUtil(mContext,"1b06f81785ad0",
                "a560b3698cd20bec32457751e2527971",this);
        this.mSharedPreferencesUtil = new SharedPreferencesUtil(mContext,"userInfo");
    }

    @Override
    public void onNext(Object o) {
        Message message  = (Message)o;
        int event = message.arg1;
        int result = message.arg2;
        Object data = message.obj;
        switch (message.what){

            case 1:
                mActivity.setCodeButtonText("重新发送(" + message.obj + "）");
                break;
            case 2:
                mActivity.setCodeButtonText("获取验证码");
                mActivity.setCodeButtonClickable(true);
                break;
            case 10:
                try {
                    getSmsResult(result,data,event);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /*解析短信发送后回传的数据*/
    private void analyzeSMSData(int event) throws ClassNotFoundException {
        switch (event){
            //短信验证成功
            case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                mActivity.setToastInfo("短信验证成功");
                codeSuccess();
                break;
            //短信获取成功
            case SMSSDK.EVENT_GET_VERIFICATION_CODE:
                mActivity.setToastInfo("短信发送成功");
                break;
        }
    }

    /*获取返回结果是否正确*/
    private void getSmsResult(int result,Object throwable,int event) throws ClassNotFoundException {
        switch (result){
            //结果错误
            case SMSSDK.RESULT_ERROR:
                if(throwable instanceof Throwable) {
                    JSONObject jsonObject = JSON.parseObject(((Throwable) throwable).getMessage());
                    String des = jsonObject.getString("detail");
                    int status = jsonObject.getInteger("status");
                    if (status > 0 && !TextUtils.isEmpty(des)) {
                        mActivity.setToastInfo(des);
                    }
                }
                break;

            //结果正确
            case SMSSDK.RESULT_COMPLETE:
                analyzeSMSData(event);
                break;
        }
    }
    /*设置获取验证码按钮点击事件*/
    @Override
    public void setGetButtonClick(String phoneNum) {
        if(TextUtils.isEmpty(phoneNum)){
            mActivity.setToastInfo("短信不能为空");
            return;
        }
        this.phoneNum = phoneNum;
        SMSSDK.getVerificationCode("86", phoneNum);
        mActivity.setCodeButtonClickable(false);
        mSMSVerificationUtil.setTime(30);
        mActivity.setCodeButtonText("重新发送(" + 30 + ")");
        Observable<String> observable;
    }

    /*设置确定按钮点击事件*/
    @Override
    public void setSureButtonClick(String code,String password) throws ClassNotFoundException {
        this.code = code;
        this.password = password;

/*//        if(TextUtils.isEmpty(code)){
//            mActivity.setToastInfo("验证码不能为空");
//            return;
//        }*/
        if(TextUtils.isEmpty(password)){
            mActivity.setToastInfo("密码不能为空");
            return;
        }
        SMSSDK.submitVerificationCode("86", phoneNum, code);
    }

    /*验证码正确输入*/
    private void codeSuccess() throws ClassNotFoundException {
        mModel.setUserInfoRequest(phoneNum, MD5Util.ToMD5(password));
    }


    @Override
    public void onCompleted() {
        Log.e("onCompleted","onCompleted");
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e("onError",throwable.toString());
    }

    @Override
    public void onSucceed(int what, String o) {
        switch (what){
            case 1:
                mModel.setGetLongitudeAndLatitude();
                AndroidSchedulers.mainThread();
                break;

            case 2:
                mRentpointsBeans = JSONObject.parseObject(o,new TypeReference<ArrayList<RentpointsBean>>(){});
                HashMap<String,Object> userInfo = new HashMap<>();
                userInfo.put("userID", phoneNum);
                userInfo.put("password", MD5Util.ToMD5(password));
                userInfo.put("userType","已注册用户");
                mSharedPreferencesUtil.setData(userInfo);
                mActivity.setToastInfo("用户注册成功");
                mActivity.setIntent(mRentpointsBeans);

        }
    }

    @Override
    public void onFailed(int what, String toast) {
        mActivity.setToastInfo("注册失败");
    }
}

