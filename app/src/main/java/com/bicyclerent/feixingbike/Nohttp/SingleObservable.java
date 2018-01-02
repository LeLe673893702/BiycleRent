package com.bicyclerent.feixingbike.Nohttp;

import android.os.Message;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/1/7 0007.
 * 被观察者
 */
public class SingleObservable {
    private Observable<Object> observable;
    private Observable<Object> timeObservable;
    private Message msg;
    private static SingleObservable sHelper = null;
    private  int time;
    public static SingleObservable getInstance(){
        if(sHelper == null){
            synchronized (SingleObservable.class){
                sHelper = new SingleObservable();
            }
        }
        return sHelper;
    }

    public SingleObservable  observable(final Object o){
        observable = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscriber.onNext(o);
            }
        });
        return this;
    }


    public SingleObservable subscribe(Observer o){
        observable.subscribe(o);
        return this;
    }

    public SingleObservable observeOn(Scheduler scheduler){
        observable = observable.observeOn(scheduler);
        return this;
    }

    public SingleObservable subscribeOn(Scheduler scheduler){
        observable = observable.subscribeOn(scheduler);
        return this;
    }
}
