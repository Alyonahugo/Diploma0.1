package com.ravi.spring.model;

/**
 * Created by User on 19.05.2015.
 */
public class Forum {
    private String sectionName;
    private String author;
    private Integer count;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Forum(String sectionName, String author, Integer count) {
        this.sectionName = sectionName;
        this.author = author;
        this.count = count;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
