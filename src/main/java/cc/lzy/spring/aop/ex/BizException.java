/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.ex;

/**
 * 业务异常
 *
 * @author taigai
 * @version BizException.java, v 0.1 2021年04月11日 19:49 taigai Exp $
 */
public class BizException extends RuntimeException {

    /** 错误码 */
    private ResultCode resultCode;

    /** 外部用户展示文案 */
    private String resultView;

    /**
     * 构造函数
     *
     * @param resultCode 错误码
     */
    public BizException(ResultCode resultCode) {
        super(resultCode.getDesc());
        this.resultCode = resultCode;
    }

    /**
     * 构造函数
     *
     * @param resultCode 错误码
     * @param resultView 外部提示信息
     */
    public BizException(ResultCode resultCode, String resultView) {
        super(resultCode.getDesc());
        this.resultCode = resultCode;
        this.resultView = resultView;
    }

    /**
     * 构造函数
     *
     * @param resultCode    错误码
     * @param cause         异常堆栈
     */
    public BizException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getDesc(), cause);
        this.resultCode = resultCode;
    }

    /**
     * 构造函数
     *
     * @param resultCode    错误码
     * @param resultView    对外文案
     * @param cause         异常堆栈
     */
    public BizException(ResultCode resultCode, String resultView, Throwable cause) {
        super(resultCode.getDesc(), cause);
        this.resultCode = resultCode;
        this.resultView = resultView;
    }

    /**
     * Getter method for property <tt>resultCode</tt>.
     *
     * @return property value of resultCode
     */
    public ResultCode getResultCode() {
        return resultCode;
    }

    /**
     * Setter method for property <tt>resultCode</tt>.
     *
     * @param resultCode value to be assigned to property resultCode
     */
    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Getter method for property <tt>resultView</tt>.
     *
     * @return property value of resultView
     */
    public String getResultView() {
        return resultView;
    }

    /**
     * Setter method for property <tt>resultView</tt>.
     *
     * @param resultView value to be assigned to property resultView
     */
    public void setResultView(String resultView) {
        this.resultView = resultView;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "(" + resultCode.getCode() + "," + resultCode.getDesc() + "," + resultView + ")";
    }
}