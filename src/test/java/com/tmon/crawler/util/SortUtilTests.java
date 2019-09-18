package com.tmon.crawler.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SortUtilTests {
    @Test
    public void 시험_정렬() {
        StringBuilder result = SortUtil.sortWithNewOrdering("ABddbbDCc3111||||tz    ");

        Assert.assertEquals("A1B1b1b3CcDddtz", result.toString());
    }
}
