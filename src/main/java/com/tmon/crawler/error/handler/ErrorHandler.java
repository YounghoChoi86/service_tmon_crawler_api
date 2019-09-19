package com.tmon.crawler.error.handler;

import com.tmon.crawler.error.error.ErrorInfo;
import com.tmon.crawler.error.exception.NotAlphabeticException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo handleValidationException(ValidationException e) {
        return new ErrorInfo(HttpStatus.BAD_REQUEST.name(), e.getMessage());
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo handleNumberFormatException(NumberFormatException e) {
        return new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.name(), "잘못된 숫자입니다" + e.getMessage());
    }

    @ExceptionHandler(NotAlphabeticException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo handleNotAlphabeticException(NotAlphabeticException e) {
        return new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.name(),"알바벳이 아닌 문자가 포함되어 있습니다" + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo handleException(Exception e) {
        return new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage());
    }
}
