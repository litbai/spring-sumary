/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.xml.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * BeanPostProcessor Demo
 *
 * @author taigai
 * @version MyBeanPostProcessor.java, v 0.1 2021年03月27日 19:50 taigai Exp $
 */
public class MyBeanPostProcessor2 implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        if (bean instanceof LifeCircleService) {
//            System.out.println("MyBeanPostProcessor2->" + beanName + " postProcessBeforeInitialization...");
//        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        if (bean instanceof LifeCircleService) {
//            System.out.println("MyBeanPostProcessor2->" + beanName + " postProcessAfterInitialization...");
//        }
        return bean;
    }
}