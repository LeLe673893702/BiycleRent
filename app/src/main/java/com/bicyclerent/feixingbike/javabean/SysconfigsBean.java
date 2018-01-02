package com.bicyclerent.feixingbike.javabean;

import java.math.BigDecimal;

/**
 * (SYSCONFIGS)
 * 
 * @author bianj
 * @version 1.0.0 2017-01-21
 */
public class SysconfigsBean implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -2884991042005905348L;
    
    /** 版本 */
    private Long version;
    
    /** 公司名 */
    private String companyname;
    
    /** 简介 */
    private String descs;
    
    /** 租金 */
    private BigDecimal rental;
    
    /** 违约金 */
    private BigDecimal penalty;
    
    /** 目前按元/小时做，其他单位不做 */
    private String rentalunit;
    
    /** 目前按元/小时做，其他单位不做 */
    private String penaltyunit;
    
    /**
     * 获取版本
     * 
     * @return 版本
     */
    public Long getVersion() {
        return this.version;
    }
     
    /**
     * 设置版本
     * 
     * @param version
     *          版本
     */
    public void setVersion(Long version) {
        this.version = version;
    }
    
    /**
     * 获取公司名
     * 
     * @return 公司名
     */
    public String getCompanyname() {
        return this.companyname;
    }
     
    /**
     * 设置公司名
     * 
     * @param companyname
     *          公司名
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
    
    /**
     * 获取简介
     * 
     * @return 简介
     */
    public String getDescs() {
        return this.descs;
    }
     
    /**
     * 设置简介
     * 
     * @param descs
     *          简介
     */
    public void setDescs(String descs) {
        this.descs = descs;
    }
    
    /**
     * 获取租金
     * 
     * @return 租金
     */
    public BigDecimal getRental() {
        return this.rental;
    }
     
    /**
     * 设置租金
     * 
     * @param rental
     *          租金
     */
    public void setRental(BigDecimal rental) {
        this.rental = rental;
    }
    
    /**
     * 获取违约金
     * 
     * @return 违约金
     */
    public BigDecimal getPenalty() {
        return this.penalty;
    }
     
    /**
     * 设置违约金
     * 
     * @param penalty
     *          违约金
     */
    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }
    
    /**
     * 获取目前按元/小时做，其他单位不做
     * 
     * @return 目前按元/小时做
     */
    public String getRentalunit() {
        return this.rentalunit;
    }
     
    /**
     * 设置目前按元/小时做，其他单位不做
     * 
     * @param rentalunit
     *          目前按元/小时做，其他单位不做
     */
    public void setRentalunit(String rentalunit) {
        this.rentalunit = rentalunit;
    }
    
    /**
     * 获取目前按元/小时做，其他单位不做
     * 
     * @return 目前按元/小时做
     */
    public String getPenaltyunit() {
        return this.penaltyunit;
    }
     
    /**
     * 设置目前按元/小时做，其他单位不做
     * 
     * @param penaltyunit
     *          目前按元/小时做，其他单位不做
     */
    public void setPenaltyunit(String penaltyunit) {
        this.penaltyunit = penaltyunit;
    }
}