package com.kcf.dictionary;

import org.ansj.domain.Term;
import org.ansj.library.UserDefineLibrary;

/**
 * Author: 老牛 -- TK
 * Date:   14-4-23
 *
 * kcf dictionary class
 * init the kcf defined words
 * give the search, check and so on functions
 */
public class Dictionary {

    static {
        init();
    }

    /**
     * init dictionary, mainly load
     * the kcf defined words int ANSJ
     */
    private static void init() {
        UserDefineLibrary.loadLibrary(UserDefineLibrary.FOREST, "library/userLibrary/");
    }

    /**
     * if the term existed in kcf dictionary
     * give a extra weight
     */
    public static int getWeight(Term term) {
        String natureValue = term.getNatrue().natureStr;

        for(KcfNatures nature : KcfNatures.values()) {
            if(nature.getValue().equals(natureValue)) {
                return nature.getWeight();
            }
        }

        return 0;
    }
}

/**
 * kcf defined natures
 */
enum KcfNatures {
    CONDITION("kcf_defined_condition", 10),
    DRUG("kcf_defined_drug", 8),
    TEST("kcf_defined_test", 9),
    LAB("kcf_defined_lab", 9),
    TREATMENT("kcf_defined_treatment", 9),
    HOSPITAL("kcf_defined_hospital", 7);

    private String value;
    private int weight;

    private KcfNatures(String value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    public String getValue() {
        return this.value;
    }

    public int getWeight() {
        return this.weight;
    }
}
