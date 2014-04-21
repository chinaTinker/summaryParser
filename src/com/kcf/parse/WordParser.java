package com.kcf.parse;

import com.kcf.entity.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Author: 老牛 -- TK
 * Date:   14-4-18
 *
 * key word parser class
 */
public class WordParser {

    /**
     * use ansj_seg, parse the given text
     *
     * @param text text content
     * @return collection of terms which keep the
     *  word and the frequency
     */
    public static List<Term> parse(String text) {
        List<Term> terms = new ArrayList<Term>();

        List<org.ansj.domain.Term> ansjTerms = ToAnalysis.parse(text);

        if(ansjTerms != null && !ansjTerms.isEmpty()) {
            for(org.ansj.domain.Term term : ansjTerms) {
                String word = term.getName().trim();
                if(!isStopWord(word)) {
                    int frequency = countFrequency(term.getName(), text);

                    Term myTerm = new Term();
                    myTerm.setFrequency(frequency);
                    myTerm.setWord(word);

                    if (!terms.contains(myTerm)) {
                        terms.add(myTerm);
                    }
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

    //FIXME
    private static boolean isStopWord(String word) {
        Pattern p = Pattern.compile("[,|，|.|。]");
        return p.matcher(word).matches();
    }
}
