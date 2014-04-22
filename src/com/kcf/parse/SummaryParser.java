package com.kcf.parse;

import com.kcf.entity.Sentence;
import com.kcf.entity.Term;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Author: 老牛 -- TK
 * Date:   14-4-18
 *
 * Auto summary generate class
 */
public class SummaryParser {
    private static final Logger logger = LoggerFactory.getLogger(SummaryParser.class);

    /** default summary content length */
    private static final int CONTENT_LEN = 200;

    /**
     * parse the give text, and generate
     * this text`s summary
     *
     * @param text the origin text content
     * @return text content summary
     */
    public static String parse(String text) {
        return parse(text, CONTENT_LEN);
    }

    public static String parse(String text, int length) {
        Collection<Term> terms = WordParser.parse(text);
        Collection<Sentence> sentences = splitText(text);

        logger.debug("terms: {}", terms);

        return buildString(filter(sentences, terms), length);
    }

    /**
     * @param text origin text content
     * @return a collection of all the sentences.
     *
     * split the text into sentences by the sign
     */
    public static Collection<Sentence> splitText(String text) {
        Collection<Sentence> sentences = new ArrayList<Sentence>();

        String[] strs = text.split("[.|。|!|！|\\?|？|......]");
        if(strs != null && strs.length > 0){
            for(int i = 0, len = strs.length; i < len; i++) {
                String crrSentence = strs[i];
                if(!"".equals(crrSentence)) {
                    Sentence sent = new Sentence();
                    sent.setContent(crrSentence);
                    sent.setDistance(i);

                    sentences.add(sent);
                }
            }
        }

        return sentences;
    }

    /**
     * @param sentences the sentences that to be filtered
     * @param terms     the terms used to parse the sentences
     * @return          the key sentences
     *
     * use the given terms to filter the given sentences.
     * and keep the sentences that has the terms.
     */
    private static Collection<Sentence> filter(Collection<Sentence> sentences, Collection<Term> terms) {
        Collection<Sentence> summarySentences = new ArrayList<Sentence>();

        if(!sentences.isEmpty()){
            if(!terms.isEmpty()) {
                for(Term term : terms) {
                    for(Sentence crrSt : sentences) {
                        if(crrSt.getContent().contains(term.getWord())) {
                            summarySentences.add(crrSt);
                        }
                    }
                }
            } else {
                summarySentences.addAll(sentences);
            }
        }

        return summarySentences;
    }

    private static String buildString(Collection<Sentence> sentences, int length) {
        if(length < 1) length = CONTENT_LEN;

        StringBuilder sb = new StringBuilder();

        int lastDist = 0;

        for(Sentence sentence : sentences) {
            sb.append(sentence.getContent());

            int crrDist = sentence.getDistance();

            if(crrDist - lastDist > 1){
                sb.append("...");
            }else {
                sb.append("。");
            }

            lastDist = crrDist;

            if(sb.length() >= length) {
                break;
            }
        }

        return sb.toString();
    }
}
