package com.tmon.crawler.service;

import com.tmon.crawler.util.SortUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HtmlDataProcessService extends DataProcessTemplateService {
    @Override
    public StringBuilder processData(String data) {
        log.debug("origin removed text : [{}]", data);
        String text = data;
        log.debug("html removed text : [{}]", text);
        StringBuilder textStrinbBuilder = SortUtil.sortWithNewOrdering(text);
        String resultString = textStrinbBuilder.toString();
        log.debug("a-z A-Z 0-9만 허용 : [{}] length=[{}]", resultString, resultString.length());
        return textStrinbBuilder;
    }
}
