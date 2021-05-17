/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.ex;

/**
 * 结果码
 *
 * @author taigai
 * @version ResultCode.java, v 0.1 2021年04月11日 19:49 taigai Exp $
 */
public interface ResultCode {
    String getCode();

    String getDesc();

    ErrorLevelEnum getErrorLevel();
}