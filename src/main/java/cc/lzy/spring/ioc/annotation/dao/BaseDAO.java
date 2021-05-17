/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.annotation.dao;

/**
 * @author taigai
 * @version BaseDAO.java, v 0.1 2021年03月28日 18:31 taigai Exp $
 */
public interface BaseDAO<T> {
    void save(T t);
}