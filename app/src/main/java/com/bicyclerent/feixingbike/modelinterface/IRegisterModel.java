package com.bicyclerent.feixingbike.modelinterface;

/**
 * Created by 刺雒 on 2017/2/2.
 */
public interface IRegisterModel extends IGetLongitudeAndLatitude{
    void setUserInfoRequest(String phoneNum,String password) ;
}
