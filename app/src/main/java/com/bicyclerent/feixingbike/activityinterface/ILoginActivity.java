package com.bicyclerent.feixingbike.activityinterface;

import com.bicyclerent.feixingbike.javabean.RentpointsBean;

import java.util.ArrayList;

/**
 * Created by 刺雒 on 2017/2/3.
 */
public interface ILoginActivity {
    void setToast(String text);
    void setIntent(ArrayList<RentpointsBean> rentpointsBeans);
}
