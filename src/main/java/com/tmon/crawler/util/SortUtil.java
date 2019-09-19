package com.tmon.crawler.util;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.helper.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SortUtil {
    //클래스에서 호출할 경우를 대비하여 Exception 처리
    private SortUtil() {
        throw new AssertionError();
    }
    /*
     * a~z, A-Z, 0-9를 제외한 문자를 제거하고 새로운 정렬 방법으로 data를 정렬한다.
     */
    public static StringBuilder sortWithNewOrdering(String data) {
        StringBuilder alphabeticStingBulder = new StringBuilder();
        StringBuilder numericStingBulder = new StringBuilder();
        StringBuilder resultBuilder = new StringBuilder();

        int dataLength = data.length();
        for (int i = 0; i < dataLength; i++) {
            char c = data.charAt(i);
            if (AlphabetComparator.isAlphabetic(c)) {
                alphabeticStingBulder.append(c);
                log.info("append={} {}", c, (int)c);
            } else if (Character.isDigit(c)) {
                numericStingBulder.append(c);
            } else {
                //SKIP othrer character
            }
        }
        log.debug("alphabeticStingBulder={}", alphabeticStingBulder.toString());

        //알바벳 정렬
        //Stream 및 lamda 사용을 위한 코드.. 성능 테스트 필요
        List<Character> sortedCharList = alphabeticStingBulder.chars()
                .mapToObj(c -> Character.valueOf((char)c))
                .sorted(AlphabetComparator.getInstance())
                .collect(Collectors.toList());
        //숫자 정렬
        char[] sortedNumericArr = numericStingBulder.toString().toCharArray();
        Arrays.sort(sortedNumericArr);
        String sortedNumericStr = new String(sortedNumericArr);
        int sortedAlphabeticLength = sortedCharList.size();
        int numericLength = numericStingBulder.length();
        int k = 0;

        for (int i = 0; i < sortedAlphabeticLength; i++) {
            resultBuilder.append(sortedCharList.get(i));
            if (k < numericLength) {
                resultBuilder.append(sortedNumericStr.charAt(k));
                k++;
            }
        }
        return resultBuilder;
    }
}
