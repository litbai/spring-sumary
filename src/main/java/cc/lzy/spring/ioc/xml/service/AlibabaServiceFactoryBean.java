/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.xml.service;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author taigai
 * @version AlibabaServiceFactoryBean.java, v 0.1 2021年03月27日 18:46 taigai Exp $
 */

public class AlibabaServiceFactoryBean implements FactoryBean<AlibabaService> {

    public AlibabaServiceFactoryBean() {
        System.out.println("AlibabaServiceFactoryBean constructor invoked");
    }

    @Override
    public AlibabaService getObject() throws Exception {
        AlibabaService alibabaService = new AlibabaService("taobao-A1", "tmall-A2", "antGroup-A1", "aliExpress-A3", "kaola-A3", "cainiao-A2", "youku-B1", "gaode-A2");
        alibabaService.init();
        return alibabaService;
    }

    @Override
    public Class<?> getObjectType() {
        return AlibabaService.class;
    }

    @Override
    public String toString() {
        return "this is AlibabaServiceFactoryBean: " + hashCode();
    }
}