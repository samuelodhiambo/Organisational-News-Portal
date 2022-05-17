package com.moringaschool.Models;

public class News {
    private int id;
    private String headline;
    private String content;

    public News(String headline, String content) {
        this.headline = headline;
        this.content = content;
    }

    public String getHeadline() {
        return headline;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
