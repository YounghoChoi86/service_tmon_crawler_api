package com.tmon.crawler.controller;

import com.tmon.crawler.service.HtmlDataProcessService;
import com.tmon.crawler.service.StringDivisionService;
import com.tmon.crawler.service.TextDataProcessService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CrawlerControllerTests {
    @InjectMocks
    private CrawlerController crawlerController = new CrawlerController();

    @Mock
    private TextDataProcessService textDataProcessService;

    @Mock
    private HtmlDataProcessService htmlDataProcessService;

    @Mock
    private StringDivisionService stringDivisionService;

    /* mock exception 삽입 기본 테스트 */
    @Test(expected = Exception.class)
    public void mock기본테스트() throws Exception {
        doThrow(new Exception("exception")).when(stringDivisionService).divisionString(any(StringBuilder.class), anyInt());

        crawlerController.processHtmlData("http://www.naver.com", 5);
    }

    /* mock null 삽입 기본 테스트 */
    @Test
    public void textDataProcessSerice에서_null을_리턴하는_경우() throws Exception{
        when(htmlDataProcessService.processData(anyString()))
                .thenReturn(null);
        crawlerController.processHtmlData("http://www.naver.com", 5);
    }
}
