package com.bicyclerent.feixingbike.javabean;

/**
 * (BICYCLES)
 * 
 * @author bianj
 * @version 1.0.0 2017-01-21
 */
public class BicyclesBean implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -853814462898867726L;
    
    /** 车辆条形码/编号 */
    private String bicycleid;
    
    /** 车辆密码（6位车辆数字密码） */
    private Integer bicyclepwd;
    
    /** 车辆状态（0.损坏 1.正常 2.流动中） */
    private Integer status;
    
    /** 借点编号 */
    private String pointid;

    public String getReturnpwd() {
        return returnpwd;
    }

    public void setReturnpwd(String returnpwd) {
        this.returnpwd = returnpwd;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private String returnpwd;
    
    /**
     * 获取车辆条形码/编号
     * 
     * @return 车辆条形码/编号
     */
    public String getBicycleid() {
        return this.bicycleid;
    }
     
    /**
     * 设置车辆条形码/编号
     * 
     * @param bicycleid
     *          车辆条形码/编号
     */
    public void setBicycleid(String bicycleid) {
        this.bicycleid = bicycleid;
    }
    
    /**
     * 获取车辆密码（6位车辆数字密码）
     * 
     * @return 车辆密码（6位车辆数字密码）
     */
    public Integer getBicyclepwd() {
        return this.bicyclepwd;
    }
     
    /**
     * 设置车辆密码（6位车辆数字密码）
     * 
     * @param bicyclepwd
     *          车辆密码（6位车辆数字密码）
     */
    public void setBicyclepwd(Integer bicyclepwd) {
        this.bicyclepwd = bicyclepwd;
    }
    
    /**
     * 获取车辆状态（0.损坏 1.正常 2.流动中）
     * 
     * @return 车辆状态（0
     */
    public Integer getStatus() {
        return this.status;
    }
     
    /**
     * 设置车辆状态（0.损坏 1.正常 2.流动中）
     * 
     * @param status
     *          车辆状态（0.损坏 1.正常 2.流动中）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    /**
     * 获取借点编号
     * 
     * @return 借点编号
     */
    public String getPointid() {
        return this.pointid;
    }
     
    /**
     * 设置借点编号
     * 
     * @param pointid
     *          借点编号
     */
    public void setPointid(String pointid) {
        this.pointid = pointid;
    }
}