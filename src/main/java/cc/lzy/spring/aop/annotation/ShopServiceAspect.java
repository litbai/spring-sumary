/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author taigai
 * @version ShopServiceAspect.java, v 0.1 2021年04月18日 10:31 taigai Exp $
 */
@Component
@Aspect
public class ShopServiceAspect {

    // @args 参数类型标注了指定注解
    //@Pointcut("@args(cc.lzy.spring.aop.annotation.ShopLogInterceptor)")
    //public static void pintCut() {
    //
    //}

    // annotation, 注解必须标注在实现类上，标注在接口无效。ShopLogInterceptor需 RetentionPolicy.RUNTIME
    //@Pointcut("@target(cc.lzy.spring.aop.annotation.ShopLogInterceptor)")
    //public static void pintCut() {
    //
    //}

    // annotation, 注解必须标注在实现类上，标注在接口无效。
    //@Pointcut("@within(cc.lzy.spring.aop.annotation.ShopLogInterceptor)")
    //public static void pintCut() {
    //
    //}

    // annotation, 注解必须标注在实现类上的方法上，标注在接口、接口方法、实现类上均无效。
    //@Pointcut("@annotation(cc.lzy.spring.aop.annotation.ShopLogInterceptor)")
    //public static void pintCut() {
    //
    //}

    // bean , service包下的实现类
    //@Pointcut("within(cc.lzy.spring.aop.service.*) && bean(*Impl)")
    //public static void pintCut() {
    //
    //}

    // within
    //    @Pointcut("within(cc.lzy.spring.aop.service.ShopServiceImpl)")
    //    public static void pintCut() {
    //
    //    }

    // execution
    @Pointcut("execution(public * cc.lzy.spring.aop.service.ShopService.*(..))")
    public static void pintCut() {

    }

    /**
     * 可以通过 this(xxx) 和 target(yyy) 将代理对象和目标对象 绑定到参数上； 也可以通过 pjp 的getThis和getTarget方法直接获取
     */
    @Around("pintCut() && this(proxy) && target(target)")
    public Object intercept(ProceedingJoinPoint pjp, Object proxy, Object target) {

        // 获取被代理对象
        Object target1 = pjp.getTarget();
        System.out.println(target1 == target);

        // 获取代理对象
        Object proxy1 = pjp.getThis();
        System.out.println(proxy1 == proxy);

        // 当前Bean，即代理对象
        System.out.println("proxy=" + proxy.getClass());

        // 目标Bean，即被代理对象
        System.out.println("target=" + target.getClass());

        Object[] args = pjp.getArgs();
        Object ret = null;
        try {
            // before
            System.out.println("before, args: " + Arrays.toString(args));
            // invoke
            ret = pjp.proceed();

            // invoke, 可以改写入参
            // args = new Object[] {"xxx-yyy-zzz"};
            // Object ret = pjp.proceed(args);

        } catch (Throwable t) {
            // after throwing
            System.out.println("after throwing, t: " + t);
        } finally {
            System.out.println("after, args: " + Arrays.toString(args));
        }

        // after returning
        System.out.println("after returning, result: " + ret);

        return ret;
    }
}