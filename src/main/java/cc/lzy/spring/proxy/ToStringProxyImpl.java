/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author taigai
 * @version ToStringProxyImpl.java, v 0.1 2021年04月11日 11:03 taigai Exp $
 */
public class ToStringProxyImpl implements ToStringProxy {

    private int age;

    @Override
    public String toString() {
        return "ToStringProxyImpl";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ToStringProxyImpl)) {
            return false;
        }

        ToStringProxyImpl proxy = (ToStringProxyImpl) obj;
        return this.age == proxy.age;
    }

    @Override
    public String hello(String hi) {
        return hi;
    }

    public static void main(String[] args) {
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ToStringProxyImpl proxied = new ToStringProxyImpl();
        ToStringProxy proxy = (ToStringProxy) Proxy.newProxyInstance(ToStringProxyImpl.class.getClassLoader(), ToStringProxyImpl.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(proxied, args);
            }
        });

        System.out.println(proxy);
        System.out.println(proxy.equals(proxied));
    }

    /**
     * Getter method for property <tt>age</tt>.
     *
     * @return property value of age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter method for property <tt>age</tt>.
     *
     * @param age value to be assigned to property age
     */
    public void setAge(int age) {
        this.age = age;
    }
}