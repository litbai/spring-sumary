/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.xml.service;

import cc.lzy.spring.ioc.xml.service.car.CarService;
import cc.lzy.spring.ioc.xml.service.mobile.MobileService;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 复合服务
 *
 * @author taigai
 * @version CompositeService.java, v 0.1 2021年03月27日 17:35 taigai Exp $
 */
public class CompositeService {
    private String                     serviceName = "DEFAULT";
    private BookService                bookService;
    private Map<String, String>        pencils;
    private List<CarService>           carServices;
    private Map<String, MobileService> mobileServiceMap;
    private Properties                 properties;

    public void takeCars() {
        carServices.forEach(car -> System.out.println(car.logo()));
    }

    public void takePictures() {
        mobileServiceMap.values().forEach(v -> v.takePicture());
    }

    /**
     * Getter method for property <tt>serviceName</tt>.
     *
     * @return property value of serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Setter method for property <tt>serviceName</tt>.
     *
     * @param serviceName value to be assigned to property serviceName
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
        System.out.println("---setBookService---");
        this.bookService = bookService;
    }

    /**
     * Getter method for property <tt>pencils</tt>.
     *
     * @return property value of pencils
     */
    public Map<String, String> getPencils() {
        return pencils;
    }

    /**
     * Setter method for property <tt>pencils</tt>.
     *
     * @param pencils value to be assigned to property pencils
     */
    public void setPencils(Map<String, String> pencils) {
        this.pencils = pencils;
    }

    /**
     * Getter method for property <tt>carServices</tt>.
     *
     * @return property value of carServices
     */
    public List<CarService> getCarServices() {
        return carServices;
    }

    /**
     * Setter method for property <tt>carServices</tt>.
     *
     * @param carServices value to be assigned to property carServices
     */
    public void setCarServices(List<CarService> carServices) {
        System.out.println("---setCarServices---");
        this.carServices = carServices;
    }

    /**
     * Getter method for property <tt>mobileServiceMap</tt>.
     *
     * @return property value of mobileServiceMap
     */
    public Map<String, MobileService> getMobileServiceMap() {
        return mobileServiceMap;
    }

    /**
     * Setter method for property <tt>mobileServiceMap</tt>.
     *
     * @param mobileServiceMap value to be assigned to property mobileServiceMap
     */
    public void setMobileServiceMap(Map<String, MobileService> mobileServiceMap) {
        this.mobileServiceMap = mobileServiceMap;
    }

    /**
     * Getter method for property <tt>properties</tt>.
     *
     * @return property value of properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Setter method for property <tt>properties</tt>.
     *
     * @param properties value to be assigned to property properties
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}