/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.ex;

/**
 * 错误级别Enum
 *
 * @author taigai
 * @version ErrorLevelEnum.java, v 0.1 2021年04月11日 19:51 taigai Exp $
 */
public enum ErrorLevelEnum {

    /** 客户端异常 如入参异常 */
    LEVEL_400("4", "LEVEL_400"),

    /** 系统级异常 如网络异常、数据库异常、程序bug导致的运行时异常等 */
    LEVEL_500("5", "LEVEL_500");

    /** code，尽量短一点 */
    private String code;

    /** 描述 */
    private String desc;

    ErrorLevelEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}