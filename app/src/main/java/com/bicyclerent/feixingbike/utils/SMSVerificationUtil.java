package com.bicyclerent.feixingbike.utils;

import android.content.Context;
import android.os.Message;

import com.bicyclerent.feixingbike.Nohttp.SingleObservable;
import com.bicyclerent.feixingbike.presenter.RegisterPresenter;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 刺雒 on 2017/1/28.
 */
public class SMSVerificationUtil {
    private RegisterPresenter mPresenter;
    private int time = 15;
    private Message message = null;
    public SMSVerificationUtil(final Context context, String appKey, String appSecret, final RegisterPresenter registerPresenter){
        //短信的初始化
        SMSSDK.initSDK(context,appKey,appSecret);
        message = Message.obtain();
        this.mPresenter = registerPresenter;
        EventHandler eventHandler = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                final Message msg = Message.obtain();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                msg.what = 10;
                SingleObservable.getInstance().observable(msg).subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(mPresenter);
            }
        };
        //注册回调接口
        SMSSDK.registerEventHandler(eventHandler);
    }

    /*设置短信再次发送时间*/
    public void setTime(int t){
        time = t;
        message = Message.obtain();
        Observable timeObservable = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                for (; time > 0; time--) {
                    message.obj = time;
                    message.what = 1;
                    subscriber.onNext(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                message.what = 2;
                subscriber.onNext(message);
            }
        });
        timeObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(mPresenter);
    }


}
