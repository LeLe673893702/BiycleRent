package com.bicyclerent.feixingbike.activityinterface;

import com.bicyclerent.feixingbike.javabean.BillsBean;

/**
 * Created by 刺雒 on 2017/2/6.
 */
public interface IReturnBikeActivity {
    void setToast(String text);
    void setIntent(String cost,String billID);
}
