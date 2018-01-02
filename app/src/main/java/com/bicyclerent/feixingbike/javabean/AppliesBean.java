package com.bicyclerent.feixingbike.javabean;

import java.util.Date;

/**
 * (APPLIES)
 * 
 * @author bianj
 * @version 1.0.0 2017-01-21
 */
public class AppliesBean implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -2903070324553974300L;
    
    /** 用户编号 */
    private String userid;
    
    /** 创建时间 */
    
    private Date createdate;
    
    /** 申请描述 */
    private String descs;
    
    /** 客服反馈 */
    private String returndescs;
    
    /** 状态（0.未处理，1.已处理） */
    private Integer status;
    
    /**
     * 获取用户编号
     * 
     * @return 用户编号
     */
    public String getUserid() {
        return this.userid;
    }
     
    /**
     * 设置用户编号
     * 
     * @param userid
     *          用户编号
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public Date getCreatedate() {
        return this.createdate;
    }
     
    /**
     * 设置创建时间
     * 
     * @param createdate
     *          创建时间
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    
    /**
     * 获取申请描述
     * 
     * @return 申请描述
     */
    public String getDescs() {
        return this.descs;
    }
     
    /**
     * 设置申请描述
     * 
     * @param descs
     *          申请描述
     */
    public void setDescs(String descs) {
        this.descs = descs;
    }
    
    /**
     * 获取客服反馈
     * 
     * @return 客服反馈
     */
    public String getReturndescs() {
        return this.returndescs;
    }
     
    /**
     * 设置客服反馈
     * 
     * @param returndescs
     *          客服反馈
     */
    public void setReturndescs(String returndescs) {
        this.returndescs = returndescs;
    }
    
    /**
     * 获取状态（0.未处理，1.已处理）
     * 
     * @return 状态（0.未处理
     */
    public Integer getStatus() {
        return this.status;
    }
     
    /**
     * 设置状态（0.未处理，1.已处理）
     * 
     * @param status
     *          状态（0.未处理，1.已处理）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}