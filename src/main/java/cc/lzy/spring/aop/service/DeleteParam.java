/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.service;

import cc.lzy.spring.aop.annotation.ShopLogInterceptor;

/**
 *
 * @author taigai
 * @version : DeleteParam.java, v 0.1 2021年04月18日 18:30 taigai Exp $
 */
@ShopLogInterceptor
public class DeleteParam {

    /** shopId */
    private String shopId;

    /**
     * Getter method for property <tt>shopId</tt>.
     *
     * @return property value of shopId
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * Setter method for property <tt>shopId</tt>.
     *
     * @param shopId value to be assigned to property shopId
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}