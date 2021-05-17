/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.ex;

import java.util.HashMap;
import java.util.Map;

/**
 * Item结果枚举
 *
 * @author taigai
 * @version ItemResultCodeEnum.java, v 0.1 2021年04月11日 19:53 taigai Exp $
 */
public enum ItemResultCodeEnum implements ResultCode {

    /** 首页业务的错误码配置 */
    INVALID_ITEM_ID("INVALID_ITEM_ID", "无效的商品ID", ErrorLevelEnum.LEVEL_400),
    ITEM_QUERY_EX("ITEM_QUERY_EX", "商品查询失败", ErrorLevelEnum.LEVEL_500),
    ITEM_SAVE_EX("ITEM_SAVE_EX", "商品保存失败", ErrorLevelEnum.LEVEL_500),

    /** 其他 */
    ;

    private static final Map<String, ItemResultCodeEnum> CODE_MAP = new HashMap<>();

    static {
        for (ItemResultCodeEnum code : ItemResultCodeEnum.values()) {
            CODE_MAP.put(code.getCode(), code);
        }
    }

    /** code，结果码 */
    private String         code;
    /** 描述 */
    private String         desc;
    /** 错误等级 */
    private ErrorLevelEnum errorLevel;

    ItemResultCodeEnum(String code, String desc, ErrorLevelEnum errorLevelEnum) {
        this.code = code;
        this.desc = desc;
        this.errorLevel = errorLevelEnum;
    }

    /**
     * getByCode code不存在时返回空
     *
     * @param code 错误码
     * @return 结果码
     */
    public static ItemResultCodeEnum getByCode(String code) {
        return code == null ? null : CODE_MAP.get(code);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public ErrorLevelEnum getErrorLevel() {
        return errorLevel;
    }
}