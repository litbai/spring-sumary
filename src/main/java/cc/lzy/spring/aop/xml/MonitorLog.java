/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.xml;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.regexp.internal.RE;

/**
 * @author taigai
 * @version MonitorLog.java, v 0.1 2021年04月10日 11:17 taigai Exp $
 */
public class MonitorLog {

    private static final String SUCCESS   = "Y";
    private static final String FAIL      = "N";
    private static final String SEPARATOR = ",";
    private static final int    MAX_LEN   = 1024;

    private String   className;
    private String   method;
    private boolean  success;
    private String   resultCode;
    private String   resultMsg;
    private Object[] params;
    private Object   result;
    private String   bizFlag;
    private long     startTime;

    public void print() {
        StringBuilder sb = new StringBuilder();
        // 方法
        sb.append(className).append("#").append(method).append(SEPARATOR);
        // 是否成功
        sb.append(success ? SUCCESS : FAIL).append(SEPARATOR);
        // 结果码
        sb.append(resultCode).append(SEPARATOR);
        // 结果描述
        sb.append(resultMsg).append(SEPARATOR);
        // 耗时
        sb.append(System.currentTimeMillis() - startTime).append("ms").append(SEPARATOR);
        // 业务标志
        sb.append(bizFlag).append(SEPARATOR);

        // 摘要日志，无需打印出入参
        // 入参
        sb.append("[").append(getLimitLengthString(params)).append("]").append(SEPARATOR);
        // 出参
        sb.append("[").append(getLimitLengthString(result)).append("]").append(SEPARATOR);

        // 预留
        sb.append("_,_");

        System.out.println(sb.toString());
    }

    private String getLimitLengthString(Object params) {
        if (params == null) {
            return "";
        }

        String jsonStr = JSON.toJSONString(params);
        if (jsonStr.length() <= MAX_LEN) {
            return jsonStr;
        }

        return jsonStr.substring(0, MAX_LEN);
    }

    /**
     * Getter method for property <tt>className</tt>.
     *
     * @return property value of className
     */
    public String getClassName() {
        return className;
    }

    /**
     * Setter method for property <tt>className</tt>.
     *
     * @param className value to be assigned to property className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Getter method for property <tt>method</tt>.
     *
     * @return property value of method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Setter method for property <tt>method</tt>.
     *
     * @param method value to be assigned to property method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Getter method for property <tt>success</tt>.
     *
     * @return property value of success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Setter method for property <tt>success</tt>.
     *
     * @param success value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Getter method for property <tt>resultCode</tt>.
     *
     * @return property value of resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Setter method for property <tt>resultCode</tt>.
     *
     * @param resultCode value to be assigned to property resultCode
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Getter method for property <tt>resultMsg</tt>.
     *
     * @return property value of resultMsg
     */
    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * Setter method for property <tt>resultMsg</tt>.
     *
     * @param resultMsg value to be assigned to property resultMsg
     */
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    /**
     * Getter method for property <tt>params</tt>.
     *
     * @return property value of params
     */
    public Object[] getParams() {
        return params;
    }

    /**
     * Setter method for property <tt>params</tt>.
     *
     * @param params value to be assigned to property params
     */
    public void setParams(Object[] params) {
        this.params = params;
    }

    /**
     * Getter method for property <tt>result</tt>.
     *
     * @return property value of result
     */
    public Object getResult() {
        return result;
    }

    /**
     * Setter method for property <tt>result</tt>.
     *
     * @param result value to be assigned to property result
     */
    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * Getter method for property <tt>bizFlag</tt>.
     *
     * @return property value of bizFlag
     */
    public String getBizFlag() {
        return bizFlag;
    }

    /**
     * Setter method for property <tt>bizFlag</tt>.
     *
     * @param bizFlag value to be assigned to property bizFlag
     */
    public void setBizFlag(String bizFlag) {
        this.bizFlag = bizFlag;
    }

    /**
     * Getter method for property <tt>startTime</tt>.
     *
     * @return property value of startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Setter method for property <tt>startTime</tt>.
     *
     * @param startTime value to be assigned to property startTime
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}