/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 商品日志打印切面
 *
 * @author taigai
 * @version ItemServiceLogAspect.java, v 0.1 2021年04月10日 12:11 taigai Exp $
 */
@Component
@Aspect
public class ItemServiceLogAspect {

    /**
     * 抽取可重用的PointCut
     */
    @Pointcut("execution(public * cc.lzy.spring.aop.service.ItemService.*(..))")
    public void myPointCut() {
    }

    /**
     * 引用PointCut
     */
    @Before("myPointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "...Before Item..." + ", args:" + ", args:" + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 引用PointCut，获取结果对象
     */
    @AfterReturning(value = "myPointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature().getName() + "...AfterReturning Item..." + ", result=" + result);
    }

    /**
     * 引用PointCut，获取异常对象
     */
    @AfterThrowing(value = "myPointCut()", throwing = "th")
    public void afterThrowing(JoinPoint joinPoint, Throwable th) {
        System.out.println(joinPoint.getSignature().getName() + "...AfterThrowing Item..." + ", th" + th);
    }

    /**
     * 引用PointCut
     */
    @After("myPointCut()")
    private static int after(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "...After Item...");
        return 10;
    }

//    @Before("execution(public * cc.lzy.spring.aop.service.ItemService.*(..))")
//    public void before(JoinPoint joinPoint) {
//        System.out.println(joinPoint.getSignature().getName() + "...Before Item..." + ", args:" + ", args:" + Arrays.toString(joinPoint.getArgs()));
//    }

//    @AfterReturning(value = "execution(* cc.lzy.spring.aop.service.ItemService.*(..))", returning = "result")
//    public void afterReturning(JoinPoint joinPoint, Object result) {
//        System.out.println(joinPoint.getSignature().getName() + "...AfterReturning Item..." + ", result=" + result);
//    }

//    @AfterThrowing(value = "execution(* cc.lzy.spring.aop.service.ItemService.*(..))", throwing = "th")
//    public void afterThrowing(JoinPoint joinPoint, Throwable th) {
//        System.out.println(joinPoint.getSignature().getName() + "...AfterThrowing Item..." + ", th" + th);
//    }

    /**
     * 对访问修饰符、返回值和静态属性都没有任务要求，不过一般设置为 public void 即可。
     * 但是参数列表是有严格要求的，如下三种格式都可以：
     * 1. 没有参数
     * 2. 如果有参数，第一个参数必须为JoinPoint类型
     * 3. 如果有自定义参数，必须在注解中进行指定，比如returning和throwing
     */
//    @After(value = "execution(* cc.lzy.spring.aop.service.ItemService.*(..))")
//    private static int after(JoinPoint joinPoint) {
//        System.out.println(joinPoint.getSignature().getName() + "...After Item...");
//        return 10;
//    }
}