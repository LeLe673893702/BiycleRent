package com.bicyclerent.feixingbike.javabean;

/**
 * (USERS)
 * 
 * @author bianj
 * @version 1.0.0 2017-01-21
 */
public class UsersBean implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -7384606265656154786L;
    
    /** 用户编号/用户名 */
    private String userid;
    
    /** 密码 */
    private String password;
    
    /** 姓名 */
    private String name;
    
    /** 学校 */
    private String school;
    
    /** 身份证 */
    private String cid;
    
    /** 专业 */
    private String major;
    
    /** 租借点编号 */
    private String pointid;
    
    /** 用户类型（0.黑名单用户 1.普通用户 2.租借点客服 3.系统管理员） */
    private Integer usertype;
    
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
     * 获取密码
     * 
     * @return 密码
     */
    public String getPassword() {
        return this.password;
    }
     
    /**
     * 设置密码
     * 
     * @param password
     *          密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * 获取姓名
     * 
     * @return 姓名
     */
    public String getName() {
        return this.name;
    }
     
    /**
     * 设置姓名
     * 
     * @param name
     *          姓名
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 获取学校
     * 
     * @return 学校
     */
    public String getSchool() {
        return this.school;
    }
     
    /**
     * 设置学校
     * 
     * @param school
     *          学校
     */
    public void setSchool(String school) {
        this.school = school;
    }
    
    /**
     * 获取身份证
     * 
     * @return 身份证
     */
    public String getCid() {
        return this.cid;
    }
     
    /**
     * 设置身份证
     * 
     * @param cid
     *          身份证
     */
    public void setCid(String cid) {
        this.cid = cid;
    }
    
    /**
     * 获取专业
     * 
     * @return 专业
     */
    public String getMajor() {
        return this.major;
    }
     
    /**
     * 设置专业
     * 
     * @param major
     *          专业
     */
    public void setMajor(String major) {
        this.major = major;
    }
    
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
     * 获取用户类型（0.黑名单用户 1.普通用户 2.租借点客服 3.系统管理员）
     * 
     * @return 用户类型（0
     */
    public Integer getUsertype() {
        return this.usertype;
    }
     
    /**
     * 设置用户类型（0.黑名单用户 1.普通用户 2.租借点客服 3.系统管理员）
     * 
     * @param usertype
     *          用户类型（0.黑名单用户 1.普通用户 2.租借点客服 3.系统管理员）
     */
    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }
}