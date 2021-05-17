/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.annotation.controller;

import cc.lzy.spring.ioc.annotation.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * @author taigai
 * @version AddressController.java, v 0.1 2021年03月28日 16:53 taigai Exp $
 */
@Controller
public class AddressController {
    @Autowired
    @Qualifier("chinaAddressService")
    private AddressService addressService;

    public void saveAddr() {
        addressService.saveAddr();
    }
}