package com.bicyclerent.feixingbike.activityinterface;

import com.bicyclerent.feixingbike.javabean.RentpointsBean;

import java.util.ArrayList;

/**
 * Created by 刺雒 on 2017/1/26.
 */
public interface IRegisterActivity {
    void setToastInfo(String text);

    void setCodeButtonText(String text);

    void setCodeButtonClickable(boolean isClickable);

    void setIntent(ArrayList<RentpointsBean> rentpointsBeans);
}
