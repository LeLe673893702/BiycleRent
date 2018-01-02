package com.bicyclerent.feixingbike.presenter;

import android.content.Context;
import android.util.Log;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bicyclerent.feixingbike.Application;
import com.bicyclerent.feixingbike.activityinterface.ILoginActivity;
import com.bicyclerent.feixingbike.javabean.RentpointsBean;
import com.bicyclerent.feixingbike.javabean.UsersBean;
import com.bicyclerent.feixingbike.model.LoginModel;
import com.bicyclerent.feixingbike.modelinterface.ILoginModel;
import com.bicyclerent.feixingbike.Nohttp.IBasePresenter;
import com.bicyclerent.feixingbike.presenterinterface.ILoginPresenter;
import com.bicyclerent.feixingbike.utils.MD5Util;
import com.bicyclerent.feixingbike.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 刺雒 on 2017/2/3.
 */
public class LoginPresenter implements ILoginPresenter,IBasePresenter {
    private Context mContext;
    private ILoginActivity mActivity;
    private ILoginModel mModel;
    public LoginPresenter(Context context,ILoginActivity loginActivity){
        this.mContext  = context;
        mActivity = loginActivity;
        mModel = new LoginModel(this);
    }

    @Override
    public void setLoginButtonClick(String phoneNum, String password) throws ClassNotFoundException {
        mModel.setLoginRequest(phoneNum, MD5Util.ToMD5(password));
    }

    @Override
    public void onSucceed(int what, String o) {
        switch (what) {
            case 1:
                UsersBean usersBean = JSONObject.parseObject(o,new TypeReference<UsersBean>(){});
                SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(mContext, "userInfo");
                HashMap<String, Object> userInfoMap = new HashMap<>();
                userInfoMap.put("userID", usersBean.getUserid());
                userInfoMap.put("password", usersBean.getPassword());
                if(usersBean.getUsertype() == 2) {
                    userInfoMap.put("userType", "已认证用户");
                }
                if(usersBean.getUsertype() == 1){
                    userInfoMap.put("userType","已交押金用户");
                }
                sharedPreferencesUtil.setData(userInfoMap);
                mModel.setGetLongitudeAndLatitude();
                break;

            case 2:
                ArrayList<RentpointsBean> rentpointsBeans = JSONObject.parseObject(o,new TypeReference<ArrayList<RentpointsBean>>(){});
                mActivity.setToast("登录成功");
                mActivity.setIntent(rentpointsBeans);
                break;
        }
    }

    @Override
    public void onFailed(int what, String toast) {
        mActivity.setToast(toast);
    }
}
