package com.bicyclerent.feixingbike.Nohttp;

import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;

import rx.Observer;

/**
 * Created by 刺雒 on 2017/1/15.
 */
public class SingleObserver implements Observer {
    private static SingleObserver sSingleObserver = null;
    private IBasePresenter mPresenter = null;
    public static SingleObserver getInstance(){
        if(sSingleObserver == null){
            synchronized (SingleObserver.class){
                sSingleObserver = new SingleObserver();
            }
        }
        return sSingleObserver;
    }


    @Override
    public void onCompleted() {
        Log.e("onCompleted","onCompleted");
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e("onErrorRxJava",throwable.getMessage());
    }

    public void setPresenter(IBasePresenter presenter){
        this.mPresenter = presenter;
    }

    @Override
    public void onNext(Object o) {
        ArrayList<Object> data = (ArrayList<Object>)o;
        switch ((int)data.get(1)){
            case 1:
                try {
                    mPresenter.onSucceed((int) data.get(0), (String)data.get(2));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 0:
                mPresenter.onFailed((int) data.get(0), (String) data.get(2));
                break;
        }
    }
}
