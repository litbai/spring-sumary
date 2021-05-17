/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.xml.service;

import cc.lzy.spring.ioc.xml.service.car.CarService;

/**
 * @author taigai
 * @version BookService.java, v 0.1 2021年03月27日 12:24 taigai Exp $
 */
public class BookService {
    private String isbn;
    private String name;
    private int price;

    public BookService() {
        System.out.println(Thread.currentThread().getName() + "无参构造函数->BookService: " + this.hashCode());
    }

    public BookService(String isbn, String name, int price) {
        this.isbn = isbn;
        this.name = name;
        this.price = price;
        System.out.println(Thread.currentThread().getName() + "全参构造函数->BookService: " + this.hashCode());
    }

    public String brow(String memberId) {
        String info = name + "被" + memberId + "借出";
        System.out.println(info);
        return info;
    }

    /**
     * Getter method for property <tt>isbn</tt>.
     *
     * @return property value of isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Setter method for property <tt>isbn</tt>.
     *
     * @param isbn value to be assigned to property isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>price</tt>.
     *
     * @return property value of price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Setter method for property <tt>price</tt>.
     *
     * @param price value to be assigned to property price
     */
    public void setPrice(int price) {
        this.price = price;
    }
}