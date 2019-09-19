package com.tmon.crawler.error.exception;

/*
 * RuntimeException을 상속하여 compile 타임 catch Exception을 스킵
 */
public class NotAlphabeticException extends RuntimeException {
    public NotAlphabeticException(String message) {
        super(message);
    }
}
