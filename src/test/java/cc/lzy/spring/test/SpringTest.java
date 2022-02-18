/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.test;

import cc.lzy.spring.ioc.xml.service.car.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test
 *
 * @author taigai
 * @version SpringTest.java, v 0.1 2021年03月28日 18:07 taigai Exp $
 */
@ContextConfiguration(locations = "classpath:beans.xml")
@RunWith(SpringRunner.class)
public class SpringTest {
    @Autowired
    private CarService audiServiceImpl;

    @Test
    public void test() {
        System.out.println(audiServiceImpl.logo());
    }
}