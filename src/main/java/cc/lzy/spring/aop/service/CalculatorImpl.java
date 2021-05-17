/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 计算器实现
 *
 * @author taigai
 * @version CalculatorImpl.java, v 0.1 2021年04月06日 16:53 taigai Exp $
 */
@Service("calculator")
public class CalculatorImpl implements ICalculator {

    @Override
    public int add(int i, int j) {
        try {
            Thread.sleep(158);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("adding...");
        return i + j;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("subing...");
        return i -j ;
    }

    @Override
    public int divide(int i, int j) {
        System.out.println("dividing...");
        return i / j;
    }
}