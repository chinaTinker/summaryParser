package com.kcf.entity;

/**
 * Author: 老牛 -- TK
 * Date:   14-4-18
 */
public class Term {

    private String word;

    private int frequency;


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("word: ").append(this.word);
        sb.append(" | frequency: ").append(this.frequency);

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return this.word.hashCode() + this.frequency * 100000;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Term) {
            return this.hashCode() == obj.hashCode();
        }
        return false;
    }
}
