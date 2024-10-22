package org.sopt.diary.service;

import org.sopt.diary.repository.Category;

import java.time.LocalDateTime;

public class Diary {
    private final long id;
    private final String title;
    private String body;
    private final LocalDateTime date;
    private Category category;

    public Diary(long id, String title, String body, LocalDateTime date, Category category) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.category = category;
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
    public Category getCategory() {
        return category;
    }
}
