package com.kcf.entity;

/**
 * Author: 老牛 -- TK
 * Date:   14-4-18
 */
public class Term {

    private String word;

    private int score;


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("word: ").append(this.word);
        sb.append(" | score: ").append(this.score);

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return this.word.hashCode() + this.score * 100000;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Term) {
            return this.hashCode() == obj.hashCode();
        }
        return false;
    }
}
