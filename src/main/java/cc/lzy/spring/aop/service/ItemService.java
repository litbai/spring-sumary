/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.service;

import cc.lzy.spring.aop.ex.BizException;
import cc.lzy.spring.aop.ex.ItemResultCodeEnum;
import cc.lzy.spring.aop.ex.MyException;
import org.springframework.stereotype.Service;

/**
 * 商品服务
 *
 * @author taigai
 * @version ItemService.java, v 0.1 2021年04月11日 11:47 taigai Exp $
 */
@Service
public class ItemService {
    public void addItem() {
        System.out.println("item adding...");
        throw new BizException(ItemResultCodeEnum.ITEM_SAVE_EX, "网络出小差了，请稍后重试");
    }

    public String getItem(String itemId) {
        System.out.println("getting item..., itemId=" + itemId);
        return "itemId="+ itemId;
    }
}