package com.tmon.crawler.error.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class ErrorInfo {
    private String errorCode;
    private String message;
}
