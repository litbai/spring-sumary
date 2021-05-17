/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.annotation.service;

import cc.lzy.spring.ioc.annotation.dao.BaseDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author taigai
 * @version BaseService.java, v 0.1 2021年03月28日 18:34 taigai Exp $
 */
public class BaseService<T> {
    @Autowired
    private BaseDAO<T> baseDAO;

    public void save(T t) {
        baseDAO.save(t);
    }
}