package com.bicyclerent.feixingbike.activityinterface;

import com.bicyclerent.feixingbike.javabean.BillsBean;

import java.util.ArrayList;

/**
 * Created by 刺雒 on 2017/2/14.
 */
public interface IUserCenterActivity {
    void setReturnBikeIntent(ArrayList<BillsBean> billsBeans);
    void setShowBillsIntent(ArrayList<BillsBean> billsBeanArrayList);
    void setToast(String text);
    void setCyclingInfo(ArrayList<String> cyclingDetails);
}
