package com.tmon.crawler.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;

import javax.validation.ValidationException;

@Slf4j
public class ValidationCheckUtil {

    private static UrlValidator urlValidator = new UrlValidator();

    private ValidationCheckUtil() {
        throw new AssertionError();
    }
    public static void checkUrl(String url) throws ValidationException {
        log.info("checkUrl={}", url);
        if (!urlValidator.isValid(url)) {
            throw new ValidationException(String.format("url(%s)는 유효하지 않은 값입니다."
                    , url));
        }
    }

    public static void checkDivisionFactor(int divisionFactor) throws ValidationException {
        log.info("checkDivisionFactor={}", divisionFactor);
        if (divisionFactor <= 0) {
            throw new ValidationException(String.format("divisionFactor(%d)는 유효하지 않은 값입니다."
                    , divisionFactor));
        }
    }
}
