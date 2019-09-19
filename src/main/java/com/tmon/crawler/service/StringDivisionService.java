package com.tmon.crawler.service;

import com.tmon.crawler.domain.StringDivisionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StringDivisionService {
    public StringDivisionResult divisionString(StringBuilder dataStringBuilder, int divisionFactor) {
        log.debug("call!");
        int dataLength = dataStringBuilder.length();
        int sperationIndex = getSperateIndex(divisionFactor, dataLength);

        String quotientStr = dataStringBuilder.substring(0, sperationIndex);
        String remainStr = dataStringBuilder.substring(sperationIndex);
        log.debug("result : {}[{}] {}[{}]", quotientStr, quotientStr.length(),
                remainStr, remainStr.length());
        if (dataLength < divisionFactor) {
            String tmpStr = quotientStr;
            quotientStr = remainStr;
            remainStr = tmpStr;
        }
        return StringDivisionResult.builder()
                .quotientStr(quotientStr)
                .remainStr(remainStr)
                .build();
    }

    private int getSperateIndex(int divisionFactor, int dataLength) {
        int sperationIndex = 0;

        if (dataLength <= divisionFactor) {
            sperationIndex = dataLength;
        }
        else {
            sperationIndex = (dataLength / divisionFactor) * divisionFactor;
        }
        return sperationIndex;
    }
}
