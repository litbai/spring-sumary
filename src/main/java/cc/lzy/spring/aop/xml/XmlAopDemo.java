/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.xml;

import cc.lzy.spring.aop.service.ICalculator;
import cc.lzy.spring.aop.service.MobileService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @author taigai
 * @version AopDemo.java, v 0.1 2021年04月07日 20:03 taigai Exp $
 */
public class XmlAopDemo {
    public static void main(String[] args) {
        aopXmlConfig();
    }


    private static void beanNameAutoProxyCreator() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-aop.xml");
        ICalculator calculator = (ICalculator)applicationContext.getBean("calculator");
        calculator.add(1, 2);
        // [interface cc.lzy.spring.aop.service.ICalculator,,
        // 下面3个接口是Spring默认加上的，可以作为判断一个对象是否为Spring代理对象的标志
        // interface org.springframework.aop.SpringProxy,
        // interface org.springframework.aop.framework.Advised,
        // interface org.springframework.core.DecoratingProxy]
        // System.out.println(Arrays.toString(calculator.getClass().getInterfaces()));
    }


    private static void aopXmlConfig() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-aop.xml");
        MobileService mobileService = (MobileService) applicationContext.getBean("mobileService");

        // 被Proxy代理转给InvocationHandler>invoke执行
        System.out.println("mobileService.toString-->" + mobileService);
        // 没有被转给InvocationHandler，当前Proxy直接执行getClass，最终实现为Object.getClass
        System.out.println("mobileService.getClass-->" + mobileService.getClass());
        // 被Proxy代理转给InvocationHandler>invoke执行
        System.out.println("mobileService.hashCodee-->" + mobileService.hashCode());
        mobileService.sms("15869341111", "请9:30准时领取证书");
    }
}