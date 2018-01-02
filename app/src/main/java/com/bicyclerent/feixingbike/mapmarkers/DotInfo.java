package com.bicyclerent.feixingbike.mapmarkers;

import com.bicyclerent.feixingbike.javabean.RentpointsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 2016/10/17.
 */

public class DotInfo {

    private String dotId;
    private double dotLat;
    private double dotLon;


    public String getDotId() {
        return dotId;
    }

    public void setDotId(String dotId) {
        this.dotId = dotId;
    }

    public double getDotLat() {
        return dotLat;
    }

    public void setDotLat(double dotLat) {
        this.dotLat = dotLat;
    }

    public double getDotLon() {
        return dotLon;
    }

    public void setDotLon(double dotLon) {
        this.dotLon = dotLon;
    }

    /**
     * 初始化数据
     * 26.0636857434,119.1705293493
     * @return
     */
    public static List<DotInfo> initData(ArrayList<RentpointsBean> rentpointsBeans) {
        List<DotInfo> dotInfoList = new ArrayList<>();
        int i = 0;
        for(RentpointsBean rentpointsBean : rentpointsBeans){
            DotInfo dotInfo = new DotInfo();
            dotInfo.setDotLat(rentpointsBean.getLatitude());
            dotInfo.setDotLon(rentpointsBean.getLongitude());
            dotInfo.setDotId("dotInfo"+i);
            i++;
            dotInfoList.add(dotInfo);
        }
        return dotInfoList;
    }
}
