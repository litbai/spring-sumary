/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.service;

import cc.lzy.spring.aop.annotation.ShopLogInterceptor;
import cc.lzy.spring.aop.ex.BizException;
import cc.lzy.spring.aop.ex.ShopResultCodeEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author taigai
 * @version ShopServiceImpl.java, v 0.1 2021年04月18日 10:17 taigai Exp $
 */
@Service
@ShopLogInterceptor
public class ShopServiceImpl implements ShopService {

    ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    @Override
    public String getShopNameById(String shopId) {
        System.out.println("doing getShopNameById, shopId: " + shopId);
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "00" +
                alignRight(threadLocalRandom.nextInt(100) + "", 3, "0");
    }

    @Override
    public String saveShop(String shopName) {
        System.out.println("doing saveShop, shopName: " + shopName);
        if (shopName == null || "".equals(shopName)) {
            throw new BizException(ShopResultCodeEnum.INVALID_SHOP_NAME, "店铺名不能为空");
        }
        System.out.println("save success, shopName: " + shopName);
        return UUID.randomUUID().toString();
    }

    @Override
    public String delete(DeleteParam deleteParam) {
        System.out.println("deleting... ");
        return "DELETE SUCCESS";
    }

    public static String alignRight(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }

        if ((padStr == null) || (padStr.length() == 0)) {
            padStr = " ";
        }

        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;

        if (pads <= 0) {
            return str;
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();

            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }

            return new String(padding).concat(str);
        }
    }
}