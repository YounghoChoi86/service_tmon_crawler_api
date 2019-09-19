package com.tmon.crawler.controller;

import com.tmon.crawler.domain.StringDivisionResult;
import com.tmon.crawler.service.DataProcessTemplateService;

import com.tmon.crawler.service.StringDivisionService;
import com.tmon.crawler.util.ValidationCheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/crawlers")
@RestController
public class CrawlerController {
    @Autowired
    private DataProcessTemplateService htmlDataProcessService;
    @Autowired
    private DataProcessTemplateService textDataProcessService;
    @Autowired
    private StringDivisionService stringDivisionService;

    @GetMapping("/html")
    public StringDivisionResult processHtmlData(@RequestParam String url,
                                      @RequestParam int divisionFactor) throws Exception {

        ValidationCheckUtil.checkUrl(url);
        ValidationCheckUtil.checkDivisionFactor(divisionFactor);

        StringBuilder dataStringBuilder = htmlDataProcessService.executeWithUrl(url);

        StringDivisionResult stringDivisionResult =
                stringDivisionService.divisionString(dataStringBuilder, divisionFactor);

        return stringDivisionResult;
    }

    @GetMapping("/text")
    public StringDivisionResult processTextData(@RequestParam String url,
                                      @RequestParam int divisionFactor) throws Exception {
        ValidationCheckUtil.checkUrl(url);
        ValidationCheckUtil.checkDivisionFactor(divisionFactor);

        StringBuilder dataStringBuilder = textDataProcessService.executeWithUrl(url);

        StringDivisionResult stringDivisionResult =
                stringDivisionService.divisionString(dataStringBuilder, divisionFactor);
        return stringDivisionResult;
    }
}
