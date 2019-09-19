package com.tmon.crawler.util;

import com.tmon.crawler.error.exception.NotAlphabeticException;
import lombok.extern.slf4j.Slf4j;
import java.util.Comparator;

/*
 * 불 필요하게 중복되게 AlphabetComparator가 생성되는 것을 방지하기 위해 싱글톤으로 수정
 */
@Slf4j
public final class AlphabetComparator implements Comparator<Character> {
    private static final AlphabetComparator instance = new AlphabetComparator();
    private static final int LOWER_CASE_ASCII_MIN = 0x61;
    private static final int LOWER_CASE_ASCII_MAX = 0x7A;
    private static final int UPPER_CASE_ASCII_MIN = 0x41;
    private static final int UPPER_CASE_ASCII_MAX = 0x5A;

    private AlphabetComparator() {
        //생성자 허용
    }

    public static AlphabetComparator getInstance() {
        return instance;
    }
    //Character.isAlphabetic의 경우 영어가 포함된 특문도 true를 리턴하여 해당 메서드를 재정의 하여 사용
    public static boolean isAlphabetic(int c) {
        return isLowerCase(c) || isUpperCase(c);
    }
    public static boolean isLowerCase(int c) {
        return LOWER_CASE_ASCII_MIN <= c && c <= LOWER_CASE_ASCII_MAX;
    }
    public static boolean isUpperCase(int c) {
        return  UPPER_CASE_ASCII_MIN <= c && c <= UPPER_CASE_ASCII_MAX;
    }

    @Override
    public int compare(Character o1, Character o2) {
        int asciiChar1 = (int)o1.charValue();
        int asciiChar2 = (int)o2.charValue();
        if (!isAlphabetic(asciiChar1)) {
            throw new NotAlphabeticException(asciiChar1 + " 비교 대상이 알파벳이 아닙니다.");
        }
        if (!isAlphabetic(asciiChar2)) {
            throw new NotAlphabeticException(asciiChar2 + "비교 대상은 알파벳이 아닙니다.");
        }
        asciiChar1 = convertNewCharacterCode(asciiChar1);
        asciiChar2 = convertNewCharacterCode(asciiChar2);

        return asciiChar1 - asciiChar2;
    }

    private int convertNewCharacterCode(int asciiCode) {
        if (Character.isLowerCase(asciiCode)) {
            return (asciiCode - LOWER_CASE_ASCII_MIN - 1) * 2;
        }
        if (Character.isUpperCase(asciiCode)) {
            return (asciiCode - UPPER_CASE_ASCII_MIN - 1) * 2 - 1;
        }
        throw new NotAlphabeticException(asciiCode + " 대상은 알파벳이 아닙니다.");
    }
}
