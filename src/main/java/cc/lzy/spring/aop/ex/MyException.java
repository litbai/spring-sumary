/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.aop.ex;

/**
 * @author taigai
 * @version MyException.java, v 0.1 2021年04月11日 19:42 taigai Exp $
 */
public class MyException extends RuntimeException{

    public MyException() {
        super();
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
}