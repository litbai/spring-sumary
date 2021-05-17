/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.xml.service;

import cc.lzy.spring.ioc.xml.service.car.CarService;

import java.util.List;

/**
 * @author taigai
 * @version PersonService.java, v 0.1 2021年03月27日 20:35 taigai Exp $
 */
public class PersonService {
    private String           memberId;
    private BookService      bookService;
    private BookService      bookService2;
    private List<CarService> carServiceList;

    public PersonService() {
        System.out.println("无参构造器被调用");
    }

    public PersonService(int age) {
        System.out.println("有参构造器被调用");
    }

    public void brow() {
        bookService.brow(memberId);
    }

    /**
     * Getter method for property <tt>memberId</tt>.
     *
     * @return property value of memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * Setter method for property <tt>memberId</tt>.
     *
     * @param memberId value to be assigned to property memberId
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * Getter method for property <tt>bookService</tt>.
     *
     * @return property value of bookService
     */
    public BookService getBookService() {
        return bookService;
    }

    /**
     * Setter method for property <tt>bookService</tt>.
     *
     * @param bookService value to be assigned to property bookService
     */
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Getter method for property <tt>bookService2</tt>.
     *
     * @return property value of bookService2
     */
    public BookService getBookService2() {
        return bookService2;
    }

    /**
     * Setter method for property <tt>bookService2</tt>.
     *
     * @param bookService2 value to be assigned to property bookService2
     */
    public void setBookService2(BookService bookService2) {
        this.bookService2 = bookService2;
    }

    /**
     * Getter method for property <tt>carServiceList</tt>.
     *
     * @return property value of carServiceList
     */
    public List<CarService> getCarServiceList() {
        return carServiceList;
    }

    /**
     * Setter method for property <tt>carServiceList</tt>.
     *
     * @param carServiceList value to be assigned to property carServiceList
     */
    public void setCarServiceList(List<CarService> carServiceList) {
        this.carServiceList = carServiceList;
    }
}