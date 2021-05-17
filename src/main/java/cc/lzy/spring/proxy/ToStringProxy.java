/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.proxy;

/**
 * @author taigai
 * @version ToStringProxy.java, v 0.1 2021年04月11日 11:02 taigai Exp $
 */
public interface ToStringProxy {

    String toString();

    boolean equals(Object obj);

    String hello(String hi);
}