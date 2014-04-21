package com.kcf.parse;

import com.kcf.entity.Term;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Author: 老牛 -- TK
 * Date:   14-4-18
 *
 * Auto summary generate class
 */
public class SummaryParser {

    /**
     * parse the give text, and generate
     * this text`s summary
     *
     * @param text the origin text content
     * @return text content summary
     */
    public static String parse(String text) {
        Collection<Term> terms = WordParser.parse(text);
        Collection<String> sentences = seperateSentence(text);

        //TODO
        System.out.println(terms);
        System.out.println(sentences);

        return buildString(filter(sentences, terms));
    }

    public static Collection<String> seperateSentence(String text) {
        Collection<String> sentences = new ArrayList<String>();

        String[] strs = text.split("[.|。|!|！|\\?|？|......]");
        if(strs != null && strs.length > 0){
            for(String str : strs) {
                sentences.add(str);
            }
        }

        return sentences;
    }

    private static Collection<String> filter(Collection<String> sentences, Collection<Term> terms) {
        Collection<String> summarySentences = new ArrayList<String>();

        if(!sentences.isEmpty()){
            if(!terms.isEmpty()) {
                for(Term term : terms) {
                    for(String crrSt : sentences) {
                        if(crrSt.contains(term.getWord())) {
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

    private static String buildString(Collection<String> sentences) {
        StringBuilder sb = new StringBuilder();

        for(String sentence : sentences) {
            sb.append(sentence).append("...");
        }

        return sb.toString();
    }
}
