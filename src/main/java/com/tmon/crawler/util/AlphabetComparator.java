package com.tmon.crawler.util;

import com.tmon.crawler.error.exception.NotAlphabeticException;
import lombok.extern.slf4j.Slf4j;
import java.util.Comparator;

/*
 * 불 필요하게 중복되게 AlphabetComparator가 생성되는 것을 방지하기 위해 싱글톤으로 수정
 */
@Slf4j
public final class AlphabetComparator implements Comparator<Character> {
    private final static AlphabetComparator instance = new AlphabetComparator();

    private AlphabetComparator() {
        //생성자 허용
    }

    public static AlphabetComparator getInstance() {
        return instance;
    }

    @Override
    public int compare(Character o1, Character o2) {
        int asciiChar1 = (int)o1.charValue();
        int asciiChar2 = (int)o2.charValue();
        if (!Character.isAlphabetic(asciiChar1)) {
            throw new NotAlphabeticException("비교 대상이 알파벳이 아닙니다.");
        }
        if (!Character.isAlphabetic(asciiChar2)) {
            throw new NotAlphabeticException("비교 대상은 알파벳이 아닙니다.");
        }
        asciiChar1 = convertNewAsciiCode(asciiChar1);
        asciiChar2 = convertNewAsciiCode(asciiChar2);

        return asciiChar1 - asciiChar2;
    }

    private int convertNewAsciiCode(int asciiCode) {
        if (Character.isLowerCase(asciiCode)) {
            return (asciiCode - 0x60) * 2;
        }
        if (Character.isUpperCase(asciiCode)) {
            return (asciiCode - 0x40) * 2 - 1;
        }
        throw new NotAlphabeticException(asciiCode + " 대상은 알파벳이 아닙니다.");
    }
}
