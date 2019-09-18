package com.tmon.crawler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/*
 * TemplateMethod 패턴 사용
 */
public abstract class DataProcessTemplateService {
    @Autowired
    private RestTemplate restTemplate;

    public StringBuilder executeWithUrl(String url) {
        String result = getResultFromUrl(url);
        return processData(result);
    }
    public abstract StringBuilder processData(String data);

    private String getResultFromUrl(String url) {
        return restTemplate.getForObject(url, String.class);
    }
}
