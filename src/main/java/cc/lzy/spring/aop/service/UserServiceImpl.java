/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.service;

import org.springframework.stereotype.Service;

/**
 * UserService 实现
 *
 * @author taigai
 * @version UserServiceImpl.java, v 0.1 2021年04月10日 20:12 taigai Exp $
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println(this.getClass().getSimpleName() + "#addUser()");
    }
}