package com.bicyclerent.feixingbike.modelinterface;

/**
 * Created by 刺雒 on 2017/2/3.
 */
public interface ILoginModel extends IGetLongitudeAndLatitude{
    void setLoginRequest(String phoneNum,String password) ;
}
