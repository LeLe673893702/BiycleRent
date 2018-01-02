package com.bicyclerent.feixingbike.javabean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * (BILLS)
 * 
 * @author bianj
 * @version 1.0.0 2017-01-21
 */
public class BillsBean implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -8223396964706151994L;
    
    /** 账单编号 */
    private String billid;
    
    /** 开始时间 */
    
    private String btime;
    
    /** 结束时间 */
    
    private String etime;
    
    /** 自行车编号 */
    private String bicycleid;
    
    /** 用户编号/用户名 */
    private String userid;
    
    /** 费用 */
    private BigDecimal cost;
    
    /** 状态（0.待结账 1.已结账） */
    private Integer status;
    
    /**
     * 获取账单编号
     * 
     * @return 账单编号
     */
    public String getBillid() {
        return this.billid;
    }
     
    /**
     * 设置账单编号
     * 
     * @param billid
     *          账单编号
     */
    public void setBillid(String billid) {
        this.billid = billid;
    }
    
    /**
     * 获取开始时间
     * 
     * @return 开始时间
     */
    public String getBtime() {
        return this.btime;
    }
     
    /**
     * 设置开始时间
     * 
     * @param btime
     *          开始时间
     */
    public void setBtime(String btime) {
        this.btime = btime;
    }
    
    /**
     * 获取结束时间
     * 
     * @return 结束时间
     */
    public String getEtime() {
        return this.etime;
    }
     
    /**
     * 设置结束时间
     * 
     * @param etime
     *          结束时间
     */
    public void setEtime(String etime) {
        this.etime = etime;
    }
    
    /**
     * 获取自行车编号
     * 
     * @return 自行车编号
     */
    public String getBicycleid() {
        return this.bicycleid;
    }
     
    /**
     * 设置自行车编号
     * 
     * @param bicycleid
     *          自行车编号
     */
    public void setBicycleid(String bicycleid) {
        this.bicycleid = bicycleid;
    }
    
    /**
     * 获取用户编号/用户名
     * 
     * @return 用户编号/用户名
     */
    public String getUserid() {
        return this.userid;
    }
     
    /**
     * 设置用户编号/用户名
     * 
     * @param userid
     *          用户编号/用户名
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    /**
     * 获取费用
     * 
     * @return 费用
     */
    public BigDecimal getCost() {
        return this.cost;
    }
     
    /**
     * 设置费用
     * 
     * @param cost
     *          费用
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    
    /**
     * 获取状态（0.待结账 1.已结账）
     * 
     * @return 状态（0
     */
    public Integer getStatus() {
        return this.status;
    }
     
    /**
     * 设置状态（0.待结账 1.已结账）
     * 
     * @param status
     *          状态（0.待结账 1.已结账）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}