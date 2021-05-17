/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author taigai
 * @version JdbcConfig.java, v 0.1 2021年04月15日 19:15 taigai Exp $
 */
@PropertySource(value = "classpath:config/jdbc.properties")
@Component
public class JdbcConfig {

    @Autowired
    private Environment environment;

    private String jdbcUrl;
    private String passwd;
    private String username;

    /**
     * Getter method for property <tt>environment</tt>.
     *
     * @return property value of environment
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * Setter method for property <tt>environment</tt>.
     *
     * @param environment value to be assigned to property environment
     */
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * Getter method for property <tt>jdbcUrl</tt>.
     *
     * @return property value of jdbcUrl
     */
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    /**
     * Setter method for property <tt>jdbcUrl</tt>.
     *
     * @param jdbcUrl value to be assigned to property jdbcUrl
     */
    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    /**
     * Getter method for property <tt>passwd</tt>.
     *
     * @return property value of passwd
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * Setter method for property <tt>passwd</tt>.
     *
     * @param passwd value to be assigned to property passwd
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * Getter method for property <tt>username</tt>.
     *
     * @return property value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method for property <tt>username</tt>.
     *
     * @param username value to be assigned to property username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}