/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.service;

/**
 *
 * @author taigai
 * @version : MobileServiceImpl.java, v 0.1 2021年04月24日 10:42 taigai Exp $
 */
public class MobileServiceImpl implements MobileService {

    /**
     * @see MobileService#sms(String, String)
     */
    @Override
    public String sms(String phone, String msg) {
        System.out.println(String.format("sending msg %s to %s", msg, phone));
        return "SUCCESS";
    }
}