package com.kcf.test;

import com.kcf.parse.SummaryParser;
import love.cq.util.IOUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;
import java.util.Collection;

/**
 * Author: 老牛 -- TK
 * Date:   14-4-21
 */
@RunWith(JUnit4.class)
public class TestSummaryParser {
    private String text;
    private String summary;

    @Before
    public void prepare() throws IOException {
        BufferedReader reader = IOUtil.getReader("/Users/xuyifeng/test_files/article.txt", "utf-8");

        StringBuilder sb = new StringBuilder();

        String line = null;
        while((line = reader.readLine()) != null) {
            sb.append(line);
        }

        text = sb.toString();

        reader.close();
    }

    @Test
    public void test() {
        summary = SummaryParser.parse(text, 200);
        System.out.println(summary);

        assert  summary != null;
    }

    @After
    public void after() {

    }
}
