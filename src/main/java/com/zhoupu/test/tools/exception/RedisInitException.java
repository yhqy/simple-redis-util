package com.zhoupu.test.tools.exception;

/**
 * @author xchen
 * @date 2019-04-26
 **/
public class RedisInitException extends RuntimeException {

    public RedisInitException() {
    }

    public RedisInitException(String message) {
        super(message);
    }

    public RedisInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisInitException(Throwable cause) {
        super(cause);
    }

    public RedisInitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
