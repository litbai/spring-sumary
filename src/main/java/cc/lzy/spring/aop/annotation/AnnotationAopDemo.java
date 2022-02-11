/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.annotation;

import cc.lzy.spring.aop.service.DeleteParam;
import cc.lzy.spring.aop.service.ICalculator;
import cc.lzy.spring.aop.service.ItemService;
import cc.lzy.spring.aop.service.ShopService;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 注解驱动的Aop
 *
 * @author taigai
 * @version AnnotationAopDemo.java, v 0.1 2021年04月10日 12:07 taigai Exp $
 */
public class AnnotationAopDemo {
    public static void main(String[] args) {
        shopAspect();
    }

    private static void shopAspect() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, ".//");
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-aop-annotation-driven.xml");
        ShopService shopService = context.getBean("shopServiceImpl", ShopService.class);
        System.out.println(shopService.getShopNameById("001"));
        shopService.saveShop("123");
        shopService.delete(new DeleteParam());
    }

    private static void itemAspect() {
        // cglib动态生成的类，会写入到文件中
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, ".//");
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-aop-annotation-driven.xml");
        ItemService itemService = (ItemService) context.getBean("itemService");
        itemService.getItem("123");
        itemService.addItem();

        // cc.lzy.spring.aop.service.ItemService@50caa560
        // System.out.println(itemService);

        // class cc.lzy.spring.aop.service.ItemService$$EnhancerBySpringCGLIB$$c8722c62 ，CGLIB动态生成的类，继承了外部类ItemService，子类覆盖父类的方法，从而可以执行代理逻辑
        // System.out.println(itemService.getClass());，是外部类的子类，

        // class cc.lzy.spring.aop.service.ItemService
        // System.out.println(itemService.getClass().getSuperclass());
    }

    private static void calcultorAspect() {

        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        ApplicationContext context = new ClassPathXmlApplicationContext("beans-aop-annotation-driven.xml");
        // 获取被代理过的对象，一定要用接口类型获取
        ICalculator calculator = context.getBean("calculator", ICalculator.class);
        // cc.lzy.spring.aop.service.CalculatorImpl@5ab9e72c  toString会代理给原始目标对象执行，所以输出CalculatorImpl
        System.out.println(calculator);
        // 实际类型为Proxy：class com.sun.proxy.$Proxy14
        System.out.println(calculator.getClass());

        // 如果使用具体实现类，会报错：Exception in thread "main" org.springframework.beans.factory.BeanNotOfRequiredTypeException: Bean named 'calculator' is expected to be of type 'cc.lzy.spring.aop.service.CalculatorImpl' but was actually of type 'com.sun.proxy.$Proxy11'
        // ICalculator calculatorImpl = context.getBean("calculator", CalculatorImpl.class);

        // 如果强转为具体实现类，也是会报错，Exception in thread "main" java.lang.ClassCastException: com.sun.proxy.$Proxy11 cannot be cast to cc.lzy.spring.aop.service.CalculatorImpl
        // 因为获取到的已经是被代理过的对象，其类型跟 CalculatorImpl 这个具体类没有半毛钱关系
        // 存放在Spring容器中的是代理对象A，其和calculatorImpl唯一的关系就是组合，代理对象A实现了ICalculator接口，继承了java.lang.reflect.Proxy类
        // A继承了Proxy类的成员变量h，其类型为InvocationHandler，Spring框架实现了自己的InvocationHandler，具体类型为 org.springframework.aop.framework.JdkDynamicAopProxy
        // JdkDynamicAopProxy类中有类型为AdvisedSupport的成员变量advised，advised中有TargetSource(具体实现为SingletonTargetSource)类型的成员变量targetSource，targetSource里面有target（即为calculatorImpl对象）
        //CalculatorImpl calculatorImpl = (CalculatorImpl) context.getBean("calculator");

        System.out.println(calculator.add(Integer.MAX_VALUE, 1));

        System.out.println("--------------------");

        System.out.println(calculator.sub(Integer.MIN_VALUE, 1));

        System.out.println("--------------------");

        System.out.println(calculator.divide(Integer.MIN_VALUE, 0));

        // // [interface cc.lzy.spring.aop.service.ICalculator,
        // 下面3个接口是Spring默认加上的，可以作为判断一个对象是否为Spring代理对象的标志
        // interface org.springframework.aop.SpringProxy,
        // interface org.springframework.aop.framework.Advised,
        // interface org.springframework.core.DecoratingProxy]
        // System.out.println(Arrays.toString(calculator.getClass().getInterfaces()));
    }

    private static void propertySource() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-aop-annotation-driven.xml");
        JdbcConfig conf =  context.getBean("jdbcConfig", JdbcConfig.class);
        System.out.println(conf.getEnvironment().getProperty("jdbcUrl"));
        System.out.println(conf.getEnvironment().getProperty("passwd"));
    }
}