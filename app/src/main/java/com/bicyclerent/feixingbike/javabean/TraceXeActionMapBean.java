package com.bicyclerent.feixingbike.javabean;

/**
 * (TRACE_XE_ACTION_MAP)
 * 
 * @author bianj
 * @version 1.0.0 2017-01-21
 */
public class TraceXeActionMapBean implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -2534168677572755389L;
    
    /**  */
    private Integer traceColumnId;
    
    /**  */
    private String packageName;
    
    /**  */
    private String xeActionName;
    
    /**
     * 获取traceColumnId
     * 
     * @return traceColumnId
     */
    public Integer getTraceColumnId() {
        return this.traceColumnId;
    }
     
    /**
     * 设置traceColumnId
     * 
     * @param traceColumnId
     *          traceColumnId
     */
    public void setTraceColumnId(Integer traceColumnId) {
        this.traceColumnId = traceColumnId;
    }
    
    /**
     * 获取packageName
     * 
     * @return packageName
     */
    public String getPackageName() {
        return this.packageName;
    }
     
    /**
     * 设置packageName
     * 
     * @param packageName
     *          packageName
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    
    /**
     * 获取xeActionName
     * 
     * @return xeActionName
     */
    public String getXeActionName() {
        return this.xeActionName;
    }
     
    /**
     * 设置xeActionName
     * 
     * @param xeActionName
     *          xeActionName
     */
    public void setXeActionName(String xeActionName) {
        this.xeActionName = xeActionName;
    }
}