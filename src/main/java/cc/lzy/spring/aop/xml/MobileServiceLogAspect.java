/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 移动服务切面
 *
 * @author taigai
 * @version : MobileServiceAspect.java, v 0.1 2021年04月24日 10:44 taigai Exp $
 */
public class MobileServiceLogAspect {

    public void before(JoinPoint joinPoint, Object proxy, Object target) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("before " + methodName + ", target:" + target.getClass() + ";" + target.hashCode());
        System.out.println("before " + methodName + ", proxy:" + proxy.getClass() + ";" + proxy.hashCode());
    }

    public void after(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("after " + methodName);
    }

    public void afterReturning(JoinPoint joinPoint, Object ret) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("after returning " + methodName + ", ret=" + ret);
    }

    public void afterThrowing(JoinPoint joinPoint, Throwable th) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("after throwing " + methodName + ", ret=" + th.getMessage());
    }

    public Object around(ProceedingJoinPoint pjp) {
        Object ret = null;
        try {
            System.out.println("around-before");
            ret = pjp.proceed();
        } catch (Throwable t) {
            System.out.println("around-catch: " + t.getMessage());
        } finally {
            System.out.println("around-finally");
        }

        return ret;
    }
}