/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.annotation;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 监控日志打印切面
 *
 * @author taigai
 * @version AnnotationMonitorLogAspect.java, v 0.1 2021年04月10日 12:11 taigai Exp $
 */
@Component
@Aspect
public class AnnotationMonitorLogAspect {

    @AfterReturning("execution(public int cc.lzy.spring.aop.service.CalculatorImpl.*(int, int)))")
    public void afterReturning() {
        System.out.println("...AfterReturning...");
    }

    @AfterThrowing("execution(public int cc.lzy.spring.aop.service.CalculatorImpl.*(int, int))")
    public void afterThrowing() {
        System.out.println("...AfterThrowing...");
    }

    @AfterReturning("execution(public int cc.lzy.spring.aop.service.CalculatorImpl.*(int, int))")
    public void afterReturning2() {
        System.out.println("...AfterReturning...");
    }

    @After("execution(public int cc.lzy.spring.aop.service.CalculatorImpl.*(int, int))")
    public void after() {
        System.out.println("...After...");
    }

    @Before("execution(public int cc.lzy.spring.aop.service.CalculatorImpl.*(int, int))")
    public void before() {
        System.out.println("...Before...");
    }
}