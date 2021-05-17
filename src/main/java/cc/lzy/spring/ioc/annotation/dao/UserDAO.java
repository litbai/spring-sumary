/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.annotation.dao;

import cc.lzy.spring.ioc.annotation.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author taigai
 * @version UserDAO.java, v 0.1 2021年03月28日 18:31 taigai Exp $
 */
@Repository
public class UserDAO implements BaseDAO<User> {
    @Override
    public void save(User user) {
        System.out.println("UserDAO#save");
    }
}