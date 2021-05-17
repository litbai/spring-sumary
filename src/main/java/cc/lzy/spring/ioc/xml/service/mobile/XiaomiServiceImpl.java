/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.xml.service.mobile;

/**
 * 小米手机
 *
 * @author taigai
 * @version XiaomiServiceImpl.java, v 0.1 2021年03月27日 17:39 taigai Exp $
 */
public class XiaomiServiceImpl implements MobileService {
    public void takePicture() {
        System.out.println("Xiaomi take picture.");
    }

    public String targetType() {
        return "xiaomi";
    }
}