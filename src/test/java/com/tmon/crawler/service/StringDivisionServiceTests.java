package com.tmon.crawler.service;

import com.tmon.crawler.domain.StringDivisionResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringDivisionServiceTests {
    @Autowired
    private StringDivisionService stringDivisionService;

    @Test(expected = ArithmeticException.class)
    public void 스트링_0으로_나누기_비정상케이스_테스트() {
        StringDivisionResult stringDivisionResult = stringDivisionService.
                divisionString(new StringBuilder("1234"), 0);
        log.info("몫 [{}]", stringDivisionResult.getQuotientStr());
        log.info("나머지 [{}]", stringDivisionResult.getQuotientStr());
    }

    @Test
    public void 스트링_나누기_정상케이스_테스트() {
        String testData = "1234";
        int divisionFactor = 1;
        StringDivisionResult stringDivisionResult = stringDivisionService.
                divisionString(new StringBuilder(testData), divisionFactor);
        Assert.assertEquals(stringDivisionResult.getQuotientStr(), testData);
        Assert.assertEquals(stringDivisionResult.getRemainStr(), "");
        log.info("몫 [{}]", stringDivisionResult.getQuotientStr());
        log.info("나머지 [{}]", stringDivisionResult.getRemainStr());

        divisionFactor = 2;
        stringDivisionResult = stringDivisionService.
                divisionString(new StringBuilder(testData), divisionFactor);
        Assert.assertEquals(stringDivisionResult.getQuotientStr(), testData);
        Assert.assertEquals(stringDivisionResult.getRemainStr(), "");
        log.info("몫 [{}]", stringDivisionResult.getQuotientStr());
        log.info("나머지 [{}]", stringDivisionResult.getRemainStr());

        divisionFactor = 3;
        stringDivisionResult = stringDivisionService.
                divisionString(new StringBuilder(testData), divisionFactor);
        Assert.assertEquals(stringDivisionResult.getQuotientStr(), testData.substring(0, divisionFactor));
        Assert.assertEquals(stringDivisionResult.getRemainStr(), testData.substring(divisionFactor));
        log.info("몫 [{}]", stringDivisionResult.getQuotientStr());
        log.info("나머지 [{}]", stringDivisionResult.getRemainStr());

        divisionFactor = 5;
        stringDivisionResult = stringDivisionService.
                divisionString(new StringBuilder(testData), divisionFactor);
        Assert.assertEquals(stringDivisionResult.getQuotientStr(), "");
        Assert.assertEquals(stringDivisionResult.getRemainStr(), testData);
        log.info("몫 [{}]", stringDivisionResult.getQuotientStr());
        log.info("나머지 [{}]", stringDivisionResult.getRemainStr());
    }


}
