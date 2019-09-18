package com.tmon.crawler.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AlphabetComparatorTests {
    @Test
    public void sortTest_기본() {
        List<Character> characterList = new ArrayList<>();
        characterList.add('B');
        characterList.add('b');
        characterList.add('A');
        characterList.add('a');
        characterList.add('C');
        characterList.add('c');

        StringBuilder sb = new StringBuilder();
        characterList.stream().sorted(AlphabetComparator.getInstance())
                .forEach(c -> sb.append(c));

        log.info("result={}", sb.toString());
        Assert.assertEquals("AaBbCc",
                sb.toString());
    }
}
