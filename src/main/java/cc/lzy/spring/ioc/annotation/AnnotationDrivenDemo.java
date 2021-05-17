/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.annotation;

import cc.lzy.spring.ioc.annotation.controller.AddressController;
import cc.lzy.spring.ioc.annotation.domain.Item;
import cc.lzy.spring.ioc.annotation.domain.User;
import cc.lzy.spring.ioc.annotation.service.BaseService;
import cc.lzy.spring.ioc.annotation.service.ItemService;
import cc.lzy.spring.ioc.annotation.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author taigai
 * @version AnnotationDrivenDemo.java, v 0.1 2021年03月28日 16:58 taigai Exp $
 */
public class AnnotationDrivenDemo {
    public static void main(String[] args) {
        generic();
    }

    /**
     * Autowired注入时，会考虑到泛型，可以根据泛型信息正确的注入对应的具体Bean，及时声明时使用的父类有多个实现子类
     */
    public static void generic() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-annotation-driven.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.save(new User());

        ItemService itemService = context.getBean("itemService", ItemService.class);
        itemService.save(new Item());
    }

    public static void annotation() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-annotation-driven.xml");
        AddressController addressController = context.getBean("addressController", AddressController.class);
        addressController.saveAddr();
    }
}