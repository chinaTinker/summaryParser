package com.kcf.entity;

/**
 * Author: 老牛 -- TK
 * Date:   14-4-22
 *
 * sentence entity
 */
public class Sentence {
    /** the sentence content */
    private String content;

    /**
     * the sentence distance from first sentence
     */
    private int distance;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
