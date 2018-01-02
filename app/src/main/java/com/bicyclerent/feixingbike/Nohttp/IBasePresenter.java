package com.bicyclerent.feixingbike.Nohttp;

import java.text.ParseException;

/**
 * Created by 刺雒 on 2017/1/15.
 */
public interface IBasePresenter {
    void onSucceed(int what, String text) throws ParseException;
     void onFailed(int what, String toast);
}
