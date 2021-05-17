/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.annotation.dao;

import org.springframework.stereotype.Repository;

/**
 * @author taigai
 * @version AddressDAO.java, v 0.1 2021年03月28日 16:54 taigai Exp $
 */
@Repository
public class AddressDAO {
    public void save() {
        System.out.println("AddressDAO#save()");
    }
}