package com.bicyclerent.feixingbike.javabean;

/**
 * (RENTPOINTS)
 * 
 * @author bianj
 * @version 1.0.0 2017-01-21
 */
public class RentpointsBean implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -459139840828525580L;
    
    /** 租借点编号 */
    private String pointid;
    
    /** 租借点名 */
    private String pointname;

    public float getLatitude() {
        return Latitude;
    }

    public void setLatitude(float latitude) {
        Latitude = latitude;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setLongitude(float longitude) {
        Longitude = longitude;
    }

    /*纬度*/
    private float Latitude;

    /*经度*/
    private float Longitude;
    
    /**
     * 获取租借点编号
     * 
     * @return 租借点编号
     */
    public String getPointid() {
        return this.pointid;
    }
     
    /**
     * 设置租借点编号
     * 
     * @param pointid
     *          租借点编号
     */
    public void setPointid(String pointid) {
        this.pointid = pointid;
    }
    
    /**
     * 获取租借点名
     * 
     * @return 租借点名
     */
    public String getPointname() {
        return this.pointname;
    }
     
    /**
     * 设置租借点名
     * 
     * @param pointname
     *          租借点名
     */
    public void setPointname(String pointname) {
        this.pointname = pointname;
    }
}