/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.annotation.dao;

import cc.lzy.spring.ioc.annotation.domain.Item;
import org.springframework.stereotype.Repository;

/**
 * @author taigai
 * @version ItemDAO.java, v 0.1 2021年03月28日 18:32 taigai Exp $
 */
@Repository
public class ItemDAO implements BaseDAO<Item> {
    @Override
    public void save(Item item) {
        System.out.println("ItemDAO#save");
    }
}