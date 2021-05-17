/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.xml.service.mobile;

/**
 * Iphone服务
 *
 * @author taigai
 * @version IPhoneServiceImpl.java, v 0.1 2021年03月27日 17:38 taigai Exp $
 */
public class IPhoneServiceImpl implements MobileService {

    public void takePicture() {
        System.out.println("iphone take picture.");
    }

    public String targetType() {
        return "iphone";
    }
}