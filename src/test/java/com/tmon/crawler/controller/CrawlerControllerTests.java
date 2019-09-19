package com.tmon.crawler.controller;

import com.tmon.crawler.domain.StringDivisionResult;
import com.tmon.crawler.service.DataProcessTemplateService;
import com.tmon.crawler.service.StringDivisionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class CrawlerControllerTests {
    @InjectMocks
    private CrawlerController crawlerController = new CrawlerController();
    @Mock
    private DataProcessTemplateService textDataProcessService;
    @Mock
    private DataProcessTemplateService htmlDataProcessService;
    @Spy
    private StringDivisionService stringDivisionService;

    /* mock exception 삽입 기본 테스트 */
    @Test(expected = Exception.class)
    public void mock기본테스트() throws Exception {
        doThrow(new Exception("exception")).
                when(stringDivisionService).divisionString(any(StringBuilder.class), anyInt());

        crawlerController.processHtmlData("http://www.naver.com", 5);
    }

    /* mock null 삽입 기본 테스트 */
    @Test
    public void htmlDataProcessService에서_null을_리턴하는_경우() throws Exception{
        when(htmlDataProcessService.processData(anyString()))
                .thenReturn(null);
        crawlerController.processHtmlData("http://www.naver.com", 5);
    }

    @Test
    public void 성공케이스_테스트() throws Exception {
        String testData = "A1a2B3b4";
        StringBuilder resultStringBuildfer = new StringBuilder();
        resultStringBuildfer.append(testData);

        when(textDataProcessService.executeWithUrl(anyString()))
                .thenReturn(resultStringBuildfer);

        StringDivisionResult stringDivisionResult
                = crawlerController.processTextData("http://www.naver.com", 4);
        //나누어 떨어지는 경우 8 % 4 = 0
        //"A1a2B3b4" & "" case
        Assert.assertEquals(testData, stringDivisionResult.getQuotientStr());
        Assert.assertEquals("", stringDivisionResult.getRemainStr());

        stringDivisionResult
                = crawlerController.processTextData("http://www.naver.com", 6);

        //나머지가 있는 경우 8 % 6 = 2
        //"A1a2B3" & "b4" case
        Assert.assertEquals(testData.substring(0, 6), stringDivisionResult.getQuotientStr());
        Assert.assertEquals(testData.substring(6, 8), stringDivisionResult.getRemainStr());
        log.info("{}", stringDivisionResult.getQuotientStr());
        log.info("{}", stringDivisionResult.getRemainStr());

        //스트링크기보다 큰 숫자인 경우8 > 9
        //"" & "A1a2B3" case 분리 케이스
        stringDivisionResult
                = crawlerController.processTextData("http://www.naver.com", 9);
        log.info("{}", stringDivisionResult.getQuotientStr());
        log.info("{}", stringDivisionResult.getRemainStr());
        Assert.assertEquals("", stringDivisionResult.getQuotientStr());
        Assert.assertEquals(testData, stringDivisionResult.getRemainStr());
    }

    @Test
    public void 성공케이스_테스트_빈스트링인_경우() throws Exception {
        String testData = "";
        StringBuilder resultStringBuildfer = new StringBuilder();
        resultStringBuildfer.append(testData);

        when(textDataProcessService.executeWithUrl(anyString()))
                .thenReturn(resultStringBuildfer);

        StringDivisionResult stringDivisionResult
                = crawlerController.processTextData("http://www.naver.com", 100);
        //"" & "" case
        Assert.assertEquals("", stringDivisionResult.getQuotientStr());
        Assert.assertEquals("", stringDivisionResult.getRemainStr());
    }

    @Test(expected=NullPointerException.class)
    public void 성공케이스_테스트_NullPointerException_케이스() throws Exception {

        doThrow(new NullPointerException()).
                when(textDataProcessService).executeWithUrl(anyString());
        crawlerController.processTextData("http://www.naver.com", 100);
    }

    @Test(expected=RestClientException.class)
    public void 성공케이스_테스트_RestClientExceptio_케이스() throws Exception {

        doThrow(new RestClientException("네트워크에러 발생")).
                when(textDataProcessService).executeWithUrl(anyString());
        crawlerController.processTextData("http://www.naver.com", 100);
    }
}
