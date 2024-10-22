package org.sopt.diary.service;

import java.time.LocalDateTime;

public class Diary {
    private final long id;
    private final String title;
    private String body;
    private final LocalDateTime date;

    public Diary(long id, String title, String body, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
    }
    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
