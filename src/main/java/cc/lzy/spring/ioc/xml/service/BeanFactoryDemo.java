/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.xml.service;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * BeanFactory演示
 *
 * @author taigai
 * @version BeanFactoryDemo.java, v 0.1 2021年03月27日 12:16 taigai Exp $
 */
public class BeanFactoryDemo {

    public static void main(String[] args) {
        spel();
    }

    public static void spel() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        CompositeService compositeService = context.getBean("compositeService", CompositeService.class);
        System.out.println(compositeService.getBookService());
    }

    public static void autowire() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        PersonService personService = context.getBean("personService", PersonService.class);
        personService.brow();
    }

    public static void propertyPlaceholder() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        BookService bookService = context.getBean("bookServiceProperties", BookService.class);
        bookService.brow("001");
    }

    public static void lifeCircle() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Object lifeCircleService = context.getBean("lifeCircleService");
        System.out.println(lifeCircleService);
        context.close();
    }

    public static void getByFactoryBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // 获取工厂Bean创建的Bean，用于创建复杂的Bean
        // 调用getBean时，才触发底层Bean的创建，具有懒加载特性
        AlibabaService alibabaService1 = context.getBean("alibabaService", AlibabaService.class);
        AlibabaService alibabaService2 = (AlibabaService) context.getBean("alibabaService");
        System.out.println(alibabaService1 == alibabaService2); // true

        // 获取工厂Bean本身，工厂Bean本身是在容器启动时就创建好的
        AlibabaServiceFactoryBean factoryBean1 = context.getBean(BeanFactory.FACTORY_BEAN_PREFIX + "alibabaService", AlibabaServiceFactoryBean.class);
        AlibabaServiceFactoryBean factoryBean2 = context.getBean(BeanFactory.FACTORY_BEAN_PREFIX + "alibabaService", AlibabaServiceFactoryBean.class);
        System.out.println(factoryBean1==factoryBean2); // true
        System.out.println(factoryBean1);
    }

    /**
     * Error creating bean with name 'abstractBookService': Bean definition is abstract
     */
    public static void getAbstract() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        BookService bookService = (BookService) context.getBean("abstractBookService");
        bookService.brow("001");
    }

    public static void getParent() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        BookService bookService = (BookService) context.getBean("bookService3");
        bookService.brow("001");
    }

    public static void compositeObject() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        CompositeService compositeService = context.getBean("compositeService", CompositeService.class);
        compositeService.takePictures();
        compositeService.takeCars();
    }

    /**
     * 同时根据name和type获取Bean，不用强制类型转换
     */
    public static void getBeanByTypeAndName() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        BookService bookService = context.getBean("bookService2", BookService.class);
        bookService.brow("002");
    }

    /**
     * 根据类型获取Bean，如果同一类型有多个Bean，直接抛异常：
     *      No qualifying bean of type 'cc.lzy.spring.ioc.xml.service.BookService' available: expected single matching bean but found 2: bookService,bookService2
     * 	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveNamedBean
     */
    public static void getBeanByType() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        BookService bookService = context.getBean(BookService.class);
        bookService.brow("001");
    }

    /**
     * 从ClassPath加载配置文件，开发中一般使用这种方式。当然使用SpringBoot之后，会完全由注解驱动
     */
    public static void getFromClassPath() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        BookService bookService = (BookService) context.getBean("bookService");
        bookService.brow("001");
    }


    /**
     *  FileSystemXmlApplicationContext，路径的几种写法
     *      1. 默认为项目工作路径：src/main/resources/beans.xml
     *      2. 前缀classpath表示类路径：classpath:beans.xml
     *      3. 前缀file表示绝对路径：file:/Users/taigai/spring-summary/src/main/resources/beans.xml
     *      4. 使用通配符加载所有符合要求的文件: classpath*.beans.xml
     */
    public static void getFromFileSystem() {
        // 获取资源加载根路径
        String path = BeanFactoryDemo.class.getClassLoader().getResource("").getPath();
        System.out.println(path);

        ApplicationContext context = new FileSystemXmlApplicationContext("file:" + path + "beans.xml");
        BookService bookService = (BookService) context.getBean("bookService");
        bookService.brow("001");
    }

}