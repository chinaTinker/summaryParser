package com.kcf.parse;

import com.kcf.entity.Term;
import love.cq.util.IOUtil;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.FilterModifWord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.IOUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/**
 * Author: 老牛 -- TK
 * Date:   14-4-18
 *
 * key word parser class
 */
public class WordParser {
    private static final Logger logger = LoggerFactory.getLogger(WordParser.class);

    private static final AtomicBoolean isLoaded = new AtomicBoolean(false);

    /**
     * use ansj_seg, parse the given text
     *
     * @param text text content
     * @return collection of terms which keep the
     *  word and the frequency
     */
    public static List<Term> parse(String text) {
        if(!isLoaded.get()){
            loadStopWord();
            isLoaded.set(true);
        }

        List<Term> terms = new ArrayList<Term>();

        List<org.ansj.domain.Term> ansjTerms = ToAnalysis.parse(text);
        ansjTerms = FilterModifWord.modifResult(ansjTerms);

        if(ansjTerms != null && !ansjTerms.isEmpty()) {
            for(org.ansj.domain.Term term : ansjTerms) {
                String word = term.getName().trim();
                int frequency = countFrequency(term.getName(), text);

                Term myTerm = new Term();
                myTerm.setFrequency(frequency);
                myTerm.setWord(word);

                if (!terms.contains(myTerm)) {
                    terms.add(myTerm);
                }
            }
        }

        sort(terms);

        return terms.size() > 3? terms.subList(0, 3) : terms;
    }

    private static int countFrequency(String term, String text) {
        int frequency = 0;
        int off = 0;
        int index = text.indexOf(term, off);

        while(index > 0) {
            frequency++;

            off = index + 1;
            index = text.indexOf(term, off);
        }

        return frequency;
    }


    private static void sort(List<Term> terms) {
        Collections.sort(terms, new Comparator<Term>() {
            @Override
            public int compare(Term o1, Term o2) {
                return o2.getFrequency() - o1.getFrequency();
            }
        });
    }

    private static void loadStopWord() {
        BufferedReader reader = null;
        try {
            reader = IOUtil.getReader("library/stopWord.dic", "utf-8");
            String line = null;
            while( (line = reader.readLine()) != null ) {
                if(!"".equals(line)) {

                    logger.debug("stop word: " + line);

                    FilterModifWord.insertStopWord(line);
                }
            }

            logger.info("load stop word finished");

        } catch (FileNotFoundException e) {
            logger.error("file not find", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("unsupported encode", e);
        } catch (IOException e) {
            logger.error("read content failed", e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                logger.error("close reader failed", e);
            }
        }
    }
}
