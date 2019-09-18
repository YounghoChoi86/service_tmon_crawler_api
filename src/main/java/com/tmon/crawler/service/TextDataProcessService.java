package com.tmon.crawler.service;

import com.tmon.crawler.util.SortUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TextDataProcessService extends DataProcessTemplateService {
    @Override
    public StringBuilder processData(String data) {
        //Remove to Html Tag 텍스트만 추출
        Document document = Jsoup.parse(data);
        //A-Z, a-z, 0-9 이외의 문자 제거
        String text = document.text();
        //log.info("html removed text : [{}]", text);
        //immutable String에서 허용 문자만 제거하고 return
        StringBuilder textStrinbBuilder = SortUtil.sortWithNewOrdering(text);
        String resultString = textStrinbBuilder.toString();
        //log.info("a-z A-Z 0-9만 허용 : [{}] length=[{}]", resultString, resultString.length());
        return textStrinbBuilder;
    }
}
