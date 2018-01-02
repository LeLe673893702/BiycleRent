package com.bicyclerent.feixingbike.presenterinterface;

/**
 * Created by 刺雒 on 2017/1/26.
 */
public interface IRegisterPresenter {
    public void setGetButtonClick(String phoneNum);
    public void setSureButtonClick(String code,String password) throws ClassNotFoundException;
}
