/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.xml;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 计算器拦截器
 *
 * @author taigai
 * @version CalculatorAspect.java, v 0.1 2021年04月06日 16:54 taigai Exp $
 */
public class MonitorLogAspect implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        MonitorLog log = new MonitorLog();
        log.setClassName(invocation.getMethod().getDeclaringClass().getSimpleName());
        log.setMethod(invocation.getMethod().getName());
        log.setBizFlag("biz-demo");
        log.setResultCode("SUCCESS");
        log.setResultMsg("成功");
        log.setParams(invocation.getArguments());
        log.setStartTime(System.currentTimeMillis());

        Object ret;
        try {
            ret = invocation.proceed();
            log.setSuccess(true);
            log.setResult(ret);
        } finally {
            try {
                log.print();
            } catch (Exception e) {
                System.out.println("服务日志打印异常");
            }
        }
        return ret;
    }
}