/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.xml.service;

/**
 * 生命周期
 *
 * @author taigai
 * @version LifeCircleService.java, v 0.1 2021年03月27日 19:35 taigai Exp $
 */
public class LifeCircleService {

    public LifeCircleService() {
        System.out.println(this.getClass() + " constructor...");
    }

    public void init() {
        System.out.println(this.getClass() + " init...");
    }

    public void destroy() {
        System.out.println(this.getClass() + " destroy...");
    }
}