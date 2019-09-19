package com.tmon.crawler.util;


import org.junit.Test;

import javax.validation.ValidationException;

public class ValidationCheckUtilTests {

    @Test(expected = ValidationException.class)
    public void 유효하지않은_분모_테스트_값음수() throws ValidationException {
        ValidationCheckUtil.checkDivisionFactor(-3333);
    }
    @Test(expected = ValidationException.class)
    public void 유효하지않은_분모_테스트_값0() throws ValidationException {
        ValidationCheckUtil.checkDivisionFactor(0);
    }
}
