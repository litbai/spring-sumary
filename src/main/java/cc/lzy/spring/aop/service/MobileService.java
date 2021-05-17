/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.service;

/**
 * 移动服务
 *
 * @author taigai
 * @version : MobileService.java, v 0.1 2021年04月24日 10:41 taigai Exp $
 */
public interface MobileService {

    /**
     * 发送短信
     *
     * @param phone 手机号
     * @param msg   信息
     * @return 结果
     */
    String sms(String phone, String msg);
}