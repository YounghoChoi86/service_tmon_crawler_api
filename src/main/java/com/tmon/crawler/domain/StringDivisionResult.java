package com.tmon.crawler.domain;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
public class StringDivisionResult {
    private String quotientStr;
    private String remainStr;
}
