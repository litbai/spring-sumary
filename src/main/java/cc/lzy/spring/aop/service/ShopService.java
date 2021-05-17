/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.service;

/**
 * 店铺服务
 *
 * @author taigai
 * @version ShopService.java, v 0.1 2021年04月18日 10:16 taigai Exp $
 */
public interface ShopService {

    String getShopNameById(String shopId);

    String saveShop(String shopName);

    String delete(DeleteParam deleteParam);
}