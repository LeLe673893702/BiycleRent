package com.bicyclerent.feixingbike.javabean;

/**
 * (TRACE_XE_EVENT_MAP)
 * 
 * @author bianj
 * @version 1.0.0 2017-01-21
 */
public class TraceXeEventMapBean implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -6724280486233094690L;
    
    /**  */
    private Integer traceEventId;
    
    /**  */
    private String packageName;
    
    /**  */
    private String xeEventName;
    
    /**
     * 获取traceEventId
     * 
     * @return traceEventId
     */
    public Integer getTraceEventId() {
        return this.traceEventId;
    }
     
    /**
     * 设置traceEventId
     * 
     * @param traceEventId
     *          traceEventId
     */
    public void setTraceEventId(Integer traceEventId) {
        this.traceEventId = traceEventId;
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
     * 获取xeEventName
     * 
     * @return xeEventName
     */
    public String getXeEventName() {
        return this.xeEventName;
    }
     
    /**
     * 设置xeEventName
     * 
     * @param xeEventName
     *          xeEventName
     */
    public void setXeEventName(String xeEventName) {
        this.xeEventName = xeEventName;
    }
}