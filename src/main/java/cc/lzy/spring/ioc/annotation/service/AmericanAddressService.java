/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.annotation.service;

import cc.lzy.spring.ioc.annotation.dao.AddressDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AmericanAddressService
 *
 * @author taigai
 * @version AmericanAddressService.java, v 0.1 2021年03月28日 16:52 taigai Exp $
 */
@Service
public class AmericanAddressService implements AddressService {

    @Autowired
    private AddressDAO addressDAO;

    @Override
    public void saveAddr() {
        System.out.println("AmericanAddressService");
        addressDAO.save();
    }
}